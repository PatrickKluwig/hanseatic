package com.hanseatic.hanseaticneu;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;

import com.hanseatic.hanseaticneu.model.OrderItem;
import com.hanseatic.hanseaticneu.model.Receipt;
import com.hanseatic.hanseaticneu.model.OrderItemModifierItem;
import com.orderup.pos.ipc.AlterTableData;
import com.orderup.pos.ipc.BillColumns;
import com.orderup.pos.ipc.DeleteReasonColumns;
import com.orderup.pos.ipc.IPOSService;
import com.orderup.pos.ipc.IPOSServiceDBCallback;
import com.orderup.pos.ipc.IPOSServicePaymentCallback;
import com.orderup.pos.ipc.IPOSServicePrinterCallback;
import com.orderup.pos.ipc.OrderData;
import com.orderup.pos.ipc.OrderDataItems;
import com.orderup.pos.ipc.OrderItemsColumns;
import com.orderup.pos.ipc.POSCursor;
import com.orderup.pos.ipc.POSDataProviderTypes;
import com.orderup.pos.ipc.POSState;
import com.orderup.pos.ipc.PaidOrderData;
import com.orderup.pos.ipc.PaidOrderDataItems;
import com.orderup.pos.ipc.PaymentData;
import com.orderup.pos.ipc.PaymentItem;
import com.orderup.pos.ipc.PaymentItems;
import com.orderup.pos.ipc.PaymentTypeColumns;
import com.orderup.pos.ipc.PrinterColumns;
import com.orderup.pos.ipc.PrinterItem;
import com.orderup.pos.ipc.ProductColumns;
import com.orderup.pos.ipc.ProductGroupColumns;
import com.orderup.pos.ipc.ProductOptionColumns;
import com.orderup.pos.ipc.ProductOptionGroupsColumns;
import com.orderup.pos.ipc.ProductOptionProductOptionGroupsColumns;
import com.orderup.pos.ipc.RoomColumns;
import com.orderup.pos.ipc.SetProductOptionColumns;
import com.orderup.pos.ipc.TableColumns;
import com.orderup.pos.ipc.UpdateTableData;
import com.orderup.pos.ipc.UserColumns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

/**
 * Created by Martin on 07.03.2018.
 */

public class POSSyncService extends Service {

    private static final String TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE";
    public static final String ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE";
    public static final String ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE";

    public final static String GETSBY_RECEIPT_UUID = "uuid";
    public final static String POS_GETSBY_USER_NAME = "Getsby user";
    public final static String POS_GETSBY_PAYMENT_METHOD_NAME = "Getsby";
    public final static String POS_GETSBY_DELETE_METHOD_NAME = "Getsby";
    public final static String POS_GETSBY_PRINTER_NAME = "PDFGetsby";
    public final static String POS_GETSBY_PRINTER_ADDRESS = "getsby_invoices";
    public final static int POS_GETSBY_PAYMENT_METHOD_TYPE = 200;
    public final static String POS_GETSBY_DISCOUNT_NAME = "Getsby Discount";
    public final static String POS_GETSBY_DELIVERY_FEE_NAME = "Liefergebühr";
    public final static int MAX_ATTEMPTS = 30;

    public static final  String TAG = "POSSyncService";

    public static IPOSService mService;
    final String[] projectionProductOptionGroup = {ProductOptionGroupsColumns.FULL_PK_ID, ProductOptionGroupsColumns.TABLE + "." + ProductOptionGroupsColumns.NAME, ProductOptionGroupsColumns.TABLE + "." + ProductOptionGroupsColumns.MULTIPLE_SELECTION, ProductOptionGroupsColumns.TABLE + "." + ProductOptionGroupsColumns.OPTIONAL, SetProductOptionColumns.FULL_PK_ID};
     final String[] projectionProductOption = {ProductOptionColumns.FULL_PK_ID, ProductOptionColumns.TABLE + "." + ProductOptionColumns.NAME, ProductOptionColumns.TABLE + "." + ProductOptionColumns.PRICE, ProductOptionColumns.TABLE + "." + ProductOptionColumns.ORDER};
    final String[] projectionProductOptionProductOptionGroup = {ProductOptionProductOptionGroupsColumns.FULL_PK_ID, ProductOptionGroupsColumns.FULL_PK_ID, ProductOptionColumns.FULL_PK_ID};
    final String[] projectionProducts = {ProductColumns.FULL_PK_ID, ProductColumns.TABLE + "." + ProductColumns.NAME, ProductColumns.TABLE + "." + ProductColumns.PRODUCTGROUP_ID, ProductColumns.TABLE + "." + ProductColumns.PRICE,
            ProductColumns.TABLE + "." + ProductColumns.SHORTNAME, ProductColumns.TABLE + "." + ProductColumns.TAX, ProductColumns.TABLE + "." + ProductColumns.ORDER, ProductColumns.TABLE + "." + ProductColumns.AVAILABLE, ProductColumns.FULL_ID,
            ProductGroupColumns.FULL_PK_ID, SetProductOptionColumns.FULL_PK_ID};
    final String[] projectionProductGroups = {ProductGroupColumns.FULL_PK_ID, ProductGroupColumns.TABLE + "." + ProductGroupColumns.NAME, ProductGroupColumns.TABLE + "." + ProductGroupColumns.ORDER, ProductGroupColumns.TABLE + "." + ProductGroupColumns.COLOR, ProductGroupColumns.TABLE + "." + ProductGroupColumns.TAX, ProductGroupColumns.FULL_ID };
    final String[] projectionTable = {TableColumns.FULL_PK_ID, TableColumns.TABLE + "." + TableColumns.NAME, TableColumns.TABLE + "." + TableColumns.ORDER, RoomColumns.TABLE + "." + RoomColumns.NAME, TableColumns.TABLE + "." + TableColumns.SIZE, TableColumns.FULL_ID};
    final String[] projectionUser = {UserColumns.FULL_PK_ID, UserColumns.NAME};
    final String[] projectionPayment = {PaymentTypeColumns.FULL_PK_ID, PaymentTypeColumns.NAME, PaymentTypeColumns.TYPE};
    final String[] projectionDelete = {DeleteReasonColumns.FULL_PK_ID, DeleteReasonColumns.NAME};
    final String[] projectionDiscount = {ProductColumns.FULL_PK_ID, ProductColumns.NAME};
    final String[] projectionPrinter = {PrinterColumns.FULL_PK_ID, PrinterColumns.NAME, PrinterColumns.MODE};
    private Context mCtx;
    private static boolean mServiceConnected = false;
    private Handler uiHandler;
    private final IBinder binder = new HanseaticBinder();
    private ServiceConnection mServiceConnection;
    private int mReboundCounter;

    private static boolean syncInProgress = false;

    @Override
    public void onCreate() {
        Log.i(TAG, "POSSyncService create");
        super.onCreate();
        mCtx = this.getApplicationContext();

        // initilize a handler to display toast messages on the ui thread
        uiHandler = new Handler(Looper.getMainLooper());

    }

    public static String helloFromService() {
        return "Hello from Service";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "POSSyncService onStartCommand");

        if(intent != null)
        {
            String action = intent.getAction();
            Log.i(TAG, "Start intent action: " + action);

            switch (action) {
                case ACTION_START_FOREGROUND_SERVICE:
                    startForegroundService();
                    return Service.START_STICKY;
                case ACTION_STOP_FOREGROUND_SERVICE:
                    stopForegroundService();
                    return Service.START_NOT_STICKY;
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }


    private void startForegroundService() {
        Log.i(TAG_FOREGROUND_SERVICE, "Start foreground service.");

        // Create notification default intent.
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create( getApplicationContext() );
        //stackBuilder.addParentStack( MainActivity.class );
        stackBuilder.addNextIntent( intent );
        final int flag =  Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE : PendingIntent.FLAG_UPDATE_CURRENT;
        PendingIntent pendingIntent = stackBuilder.getPendingIntent( 0, flag );


        NotificationCompat.Builder builder;
        /*builder = new Builder(this, "HanseaticPos")
                .setSound(null)
                .setOngoing(true)
                .setContentIntent(pendingIntent); */

        // Add Pause button intent in notification.
        Log.i(TAG_FOREGROUND_SERVICE, "Start foreground service 2222222222222");

        Intent stopIntent = new Intent(this, POSSyncService.class);
        stopIntent.setAction(POSSyncService.ACTION_STOP_FOREGROUND_SERVICE);
        PendingIntent pendingStopIntent = PendingIntent.getService(this, 0, stopIntent, flag);
        NotificationCompat.Action stopAction = new NotificationCompat.Action(android.R.drawable.ic_menu_delete, "Stop", pendingStopIntent);
        //builder.addAction(stopAction);
        Log.i(TAG_FOREGROUND_SERVICE, "Start foreground service 343333333333");

        //Notification notification=builder.build();

        /*if(Build.VERSION.SDK_INT>=26) {
            NotificationChannel channel = new NotificationChannel(getResources().getString(R.string.app_name), getResources().getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
            channel.setSound(null, null);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }*/
        //startForeground(1, notification);

        connectPOSService();
    }

    private void stopForegroundService() {
        Log.i(TAG_FOREGROUND_SERVICE, "Stop foreground service.");

        disconnectPOSService();

        // Stop foreground service and remove the notification.
        stopForeground(true);

        // Stop the foreground service.
        stopSelf();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "OnBind");
        return binder;
    }

    private boolean addListener() {
        boolean ret = true;
        try {
            mService.closePayment(true, mClosePaymentListener, null);
            mService.setDBListener(mPOSDBCallback);
            mService.setPrinterListener(mPrinterCallback);
        } catch (Exception e) {
            e.printStackTrace();
            ret = false;
        }
        if (ret == true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        Log.i(TAG, "addListener(), ret: " + ret);
        return ret;
    }


    private synchronized void connectPOSService() {
        Log.i(TAG, "connectPOSService(), mServiceConnected: " + mServiceConnected);
        boolean ret = true;
        try {
            if (!mServiceConnected) {
                mServiceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder boundService) {
                        try {
                            mService = IPOSService.Stub.asInterface((IBinder) boundService);
                            Log.i(TAG, "POS, onServiceConnected()");
                            addListener();
                            mServiceConnected = true;
                        }catch(Exception e){
                            Log.e(TAG, "onServiceConnected failed", e);
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.i(TAG, "POS, onServiceDisconnected()");
                        mServiceConnected = false;
                        try {
                            Thread.sleep(1000);
                            connectPOSService();
                        } catch (Exception e) {
                            Log.e(TAG, "failed to reconnect", e);
                        }
                    }

                    @Override
                    public void onBindingDied(ComponentName name) {
                        Log.i(TAG, "POS, onBindingDied()");
                        mServiceConnected = false;
                        try {
                            Thread.sleep(1000);
                            connectPOSService();
                        } catch (Exception e) {
                            Log.e(TAG, "failed to reconnect", e);
                        }
                    }

                    @Override
                    public void onNullBinding(ComponentName name) {
                        Log.i(TAG, "POS, onNullBinding()");
                    }
                };

                Intent i = new Intent("com.orderup.pos.ipc.POSService");
                i.setPackage("com.hanseatic.pos");
                ret = bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
                Log.d(TAG, "initService() (com.orderup.pos) bound with " + ret);
                if (!ret) {
                    i.setPackage("com.hts.pos");
                    ret = bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
                    Log.d(TAG, "initService() WL1 bound with " + ret);
                    if (!ret) {
                        i.setPackage("com.kassenvermietung24.pos");
                        ret = bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
                        Log.d(TAG, "initService() WL2 bound with " + ret);
                        if (!ret) {
                            i.setPackage("com.cashon.pos");
                            ret = bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
                            Log.d(TAG, "initService() WL3 bound with " + ret);
							if (!ret) {
								i.setPackage("com.hanseatic.pos");
								ret = bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
								Log.d(TAG, "initService() WL4 bound with " + ret);
								if (!ret) {
									i.setPackage("com.pos10.pos");
									ret = bindService(i, mServiceConnection, Context.BIND_AUTO_CREATE);
									Log.d(TAG, "initService() WL5 bound with " + ret);
                        		}
                        	}
                        }
                    }
                }
                mServiceConnected = ret;
            } else {
                boolean retListener = addListener();
                if (!retListener) {
                    try {
                        unbindService(mServiceConnection);
                    } catch (Exception e) {
                        Log.e(TAG, "unbind service failed", e);
                    }
                    mServiceConnected = false;
                    ret = false;
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "connect pos service", e);
            ret = false;
        }

        if (!ret) {
            Log.i(TAG, "POS, try to rebind to Service... Cnt: " + mReboundCounter++);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    connectPOSService();
                }
            }, 1000);
        } else {
            mReboundCounter = 0;
        }
    }

    private synchronized void disconnectPOSService(){
        unbindService(mServiceConnection);

        mServiceConnected = false;
        try {
            mService.releaseDatabaseLock();
        } catch (RemoteException e) {
            Log.e(TAG, "cannot release database lock", e);
        }

    }

    public void makeOrder(Receipt receipt, int retry) throws Exception {
        Log.i(TAG, "makeOrder");

        if (!receipt.getOrderItems().isEmpty()) {
            ArrayList itemsOrder = transformOrderItems(receipt, false);
            String printText = receipt.getPrintText();
            String table_pos_key = receipt.getTable_pos_key();
            try {
                long userId = getInsertUserOnPOS();
                long printerId = getInsertPrinterOnPOS();
                long tableId = 151;
                int attempts = 0;
                Log.i(TAG, "new Order...");
                int POSSTatus;
                Log.i(TAG, "making order on table " + tableId + " " + userId);
                if(receipt.getPaymentType().equals("CASH")){
                    while ((POSSTatus = mService.newOrder(new OrderData(tableId, userId, itemsOrder, printText))) == OrderData.RET_BUSY && attempts < MAX_ATTEMPTS) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Log.e(TAG, e.getMessage(), e);
                        }
                        attempts++;
                        Log.i(TAG, "POS is busy, attempts: " + attempts);
                        // if all attempts failed, we send feedback to JS
                        if (attempts == MAX_ATTEMPTS) {
                        /*EventBus.getDefault().post(new ReceiptUpdate.Builder()
                                .uuid(receipt.getUuid())
                                .errorCode(ReceiptUpdate.POS_BUSY)
                                .errorMessage("POS did not accept our order")
                                .build()
                        );

                         */
                            throw new Exception("POS did not accept our order");
                        }
                    }
                    if (POSSTatus != OrderData.RET_OK) {
                        throw new Exception("Octobox did not accept our order");
                    }

                } else {
                    while ((POSSTatus = mService.newPaidOrder(new PaidOrderData(tableId, userId, itemsOrder, printerId, (float) receipt.getTip() / 100, 0.0f, printText))) == OrderData.RET_BUSY && attempts < MAX_ATTEMPTS) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Log.e(TAG, e.getMessage(), e);
                        }
                        attempts++;
                        Log.i(TAG, "POS is busy, attempts: " + attempts);
                        // if all attempts failed, we send feedback to JS
                        if (attempts == MAX_ATTEMPTS) {
                        /*EventBus.getDefault().post(new ReceiptUpdate.Builder()
                                .uuid(receipt.getUuid())
                                .errorCode(ReceiptUpdate.POS_BUSY)
                                .errorMessage("POS did not accept our order")
                                .build()
                        );

                         */
                            throw new Exception("POS did not accept our order");
                        }
                    }
                    if (POSSTatus != OrderData.RET_OK) {
                        throw new Exception("Octobox did not accept our order");
                    }
                }
            }catch (DeadObjectException e){
                Log.e(TAG, "Order Failed", e);
                throw new Exception("Octobox is not available, please restart");
                /*
                disconnectPOSService();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Log.e(TAG, e.getMessage(), ie);
                }
                connectPOSService();
                if(retry < 2){
                    makeOrder(receipt, retry++);
                } else {
                    throw new Exception("Could not connect to Octobox");
                }
                 */
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
                // TODO Bestellung verwerfen
                throw new Exception("Error from Octobox: " + e.getMessage());
                /*EventBus.getDefault().post(new ReceiptUpdate.Builder()
                        .uuid(receipt.getUuid())
                        .errorCode(ReceiptUpdate.ORDER_ERROR)
                        .errorMessage(e.getMessage())
                        .build()


                );

                 */
            }
        } else {
            throw new Exception("no order items!");
        }
    }

    public void cancelOrder(Receipt receipt) throws  Exception{
        Log.i(TAG, "cancelOrder");

        if (!receipt.getOrderItems().isEmpty()) {
            ArrayList itemsOrder = transformOrderItems(receipt, false);
            String printText = receipt.getPrintText();
            String table_pos_key = receipt.getTable_pos_key();
            try {
                long userId = getInsertUserOnPOS();
                long printerId = getInsertPrinterOnPOS();
                long deleteId = getInsertDeleteMethodOnPOS();
                long tableId = Long.valueOf(table_pos_key);
                int attempts = 0;
                Log.i(TAG, "cancel Order...");
                int POSSTatus;
                Log.i(TAG, "cancel order on table " + tableId + " " + userId + " with refundId: " + deleteId);
                while ((POSSTatus = mService.newPaidOrder(new PaidOrderData(tableId, userId, itemsOrder, printerId, (float) receipt.getTip() / 100, 0.0f, printText, deleteId))) == OrderData.RET_BUSY && attempts < MAX_ATTEMPTS) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.e(TAG, e.getMessage(), e);
                    }
                    attempts++;
                    Log.i(TAG, "POS is busy, attempts: " + attempts);
                    // if all attempts failed, we send feedback to JS
                    if(attempts == MAX_ATTEMPTS){
                        /*EventBus.getDefault().post(new ReceiptUpdate.Builder()
                                .uuid(receipt.getUuid())
                                .errorCode(ReceiptUpdate.POS_BUSY)
                                .errorMessage("POS did not accept our order")
                                .build()
                        );

                         */
                        throw new Exception("POS did not accept our order");
                    }
                }
                if(POSSTatus != OrderData.RET_OK){
                    throw new Exception("Octobox could not cancel order!");
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage(), e);
                // TODO Bestellung verwerfen
                throw new Exception("Error from Octobox: " + e.getMessage());
                /*EventBus.getDefault().post(new ReceiptUpdate.Builder()
                        .uuid(receipt.getUuid())
                        .errorCode(ReceiptUpdate.ORDER_ERROR)
                        .errorMessage(e.getMessage())
                        .build()


                );

                 */
            }

        } else {
            throw new Exception("No order items!");
        }
    }

    private ArrayList transformOrderItems(Receipt receipt, boolean withPayment) throws Exception{
        ContentResolver resolver = getContentResolver();

        Map<Double, Integer> totalDiscount = new HashMap<>();
        Log.i(TAG, "Response items (incomingOrderHandler): " + receipt.getOrderItems().toString());
        long paymentId = getInsertPaymentMethodOnPOS();
        ArrayList itemsOrder;
        if (withPayment) {
            itemsOrder = new ArrayList<PaidOrderDataItems>();
        } else {
            itemsOrder = new ArrayList<OrderDataItems>();
        }
        Uri.Builder builderProducts = POSDataProviderTypes.CONTENT_PRODUCT_TABLE.buildUpon();
        final String[] projectionTmp = {ProductColumns.TABLE + "." + ProductColumns.PRICE };
        for (OrderItem orderItem : receipt.getOrderItems()) {
            Log.i(TAG, "order Item " + orderItem);
            String product_pos_key = orderItem.getProduct_pos_key();

            List<OrderItemModifierItem> itemsModifier = orderItem.getModifierItems();
            long[] itemsModifierArray = null;

            float optionPrice = 0.0f;
           /* if (itemsModifier != null) { NOT USED
                itemsModifierArray = new long[itemsModifier.size()];
                int i = 0;
                Uri.Builder builderProductOption = POSDataProviderTypes.CONTENT_PRODUCTOPTION_TABLE.buildUpon();
                for (OrderItemModifierItem entry : itemsModifier) {
                    String tmp = entry.getModifier_item_pos_key();
                    itemsModifierArray[i++] = Long.valueOf(tmp);
                    Cursor c = resolver.query(builderProductOption.build(), projectionProductOption, ProductOptionColumns.FULL_PK_ID + "=?", new String[] {tmp}, null);
                    if (c.moveToFirst()) {
                        optionPrice += c.getFloat(2);
                    }
                    c.close();
                }
            } */


            // orderItem.uuid() und orderCollection.uuid() mitspeichern
            ContentValues additionalCv = new ContentValues();
            additionalCv.put(GETSBY_RECEIPT_UUID, receipt.getUuid()); // ID mitgeben von der Bestellung oder Rechnung

            Double tax = orderItem.getProduct_tax();
            int discount = orderItem.getDiscount();
            int price = orderItem.getProduct_price() - discount;
            if(product_pos_key != null) {
                Cursor cProduct = resolver.query(builderProducts.build(), projectionTmp, ProductColumns.FULL_PK_ID + "=?", new String[]{product_pos_key}, null);
                if (cProduct.moveToFirst()) {
                    float posPrice = cProduct.getFloat(0) + optionPrice;
                    int posPriceInt = Math.round(posPrice * 100);
                    Log.i(TAG, "price comparison: octobox " + posPriceInt + " getsby " + price);
                    if (posPriceInt == price) {
                        if (totalDiscount.containsKey(tax)) {
                            int tmp = totalDiscount.get(tax) + discount;
                            totalDiscount.put(tax, tmp);
                        } else {
                            totalDiscount.put(tax, discount);
                        }
                        Log.i(TAG, "OrderItem " + product_pos_key);
                        if (withPayment) {
                            itemsOrder.add(new PaidOrderDataItems(Long.valueOf(product_pos_key), orderItem.getComment(), paymentId, 0, additionalCv, itemsModifierArray, null));
                        } else {
                            itemsOrder.add(new OrderDataItems(Long.valueOf(product_pos_key), orderItem.getComment(), additionalCv, null, null));
                        }
                        //itemsOrder.add(new PaidOrderDataItems(Long.valueOf(product_pos_key), orderItem.getComment(), paymentId, 0, additionalCv, itemsModifierArray, null));
                    } else {
                        // send update to JS
                        /*EventBus.getDefault().post(new ReceiptUpdate.Builder()
                                .uuid(receipt.getUuid())
                                .errorCode(ReceiptUpdate.PRICE_MISMATCH)
                                .errorMessage("Price does not match POS price")
                                .build()
                        );*/
                        //receipt.
                        cProduct.close();
                        throw new Exception("Price does not match POS price");
                    }
                }
                cProduct.close();
            } else if(orderItem.getProduct_title() != null && orderItem.getProduct_title().equals("Liefergebühr")) {
                float deliveryTax = orderItem.getProduct_tax().floatValue();
                long deliveryFeeId = getInsertDeliveryFeeProductOnPOS(deliveryTax, orderItem.getProduct_price());
                itemsOrder.add(new PaidOrderDataItems(deliveryFeeId, "", paymentId, 0, null, null, (float) orderItem.getProduct_price() / 100));
            } else {
                throw new Exception("Missing pos key!");
            }
        }

        // Trinkgeld als Produkt mit freiem Preis hinzufügen
            /*int tip = receipt.getTip();
            if (tip > 0) {
                long tipId = getTipProductOnPOS();
                itemsOrder.add(new PaidOrderDataItems(tipId, "", paymentId, 0, null, null, (float) tip / 100));
            }*/

        // Getsby discounts ale eigene produkte mit freiem preis. mehrere je nach Steuersatz
        // 1. bei stammdaten alle verschiedenen steuersätze ermitteln und dementsprechend produkte mit freiem preis anlegen z.b. Getsby Discount 20%
        // 2. hier alle getsby discounts mit gleichem steuersatz gruppieren und als neues orderitem mitbestellen
        for (Map.Entry<Double, Integer> entry : totalDiscount.entrySet()) {
            int price = entry.getValue();
            if (price != 0) {
                float tax = entry.getKey().floatValue();
                long discountId = getInsertDiscountProductOnPOS(tax);
                itemsOrder.add(new PaidOrderDataItems(discountId, "", paymentId, 0, null, null, (float) price / 100));
            }
        }
        return itemsOrder;
    }


    private final IPOSServiceDBCallback.Stub mPOSDBCallback = new IPOSServiceDBCallback.Stub() {

        @Override
        public AlterTableData[] onDBAlterTableCallback() throws RemoteException {
            return null;
        }

        @Override
        public ContentValues onDBLoadData(String table, POSCursor cursor) throws RemoteException {
            return null;
        }

        @Override
        public void onDBStartCallback(POSState state) throws RemoteException {
            try {
                Log.i(TAG, "onDBStartCallback()");
                long userId = getInsertUserOnPOS();
                long paymentId = getInsertPaymentMethodOnPOS();
                long deleteId = getInsertDeleteMethodOnPOS();
                long printerId = getInsertPrinterOnPOS();
                Log.i(TAG, "User ID: " + userId + ", Payment Method ID: " + paymentId + ", Printer Method ID: " + printerId + ", Delete Id: " + deleteId);
                //EventBus.getDefault().post(new PosStatus(PosStatus.CONNECTED));
            }catch(Exception e){
                Log.i(TAG, "Error in DB Start Callback", e);
                throw e;
            }
        }

        @Override
        public void onBlockingDBLoadedCallback(POSState state) throws RemoteException {}

        @Override
        public void onDBEndCallback(POSState state) throws RemoteException {
            // @todo: notify JS
            // setPOSStatus(false);
            //EventBus.getDefault().post(new PosStatus(PosStatus.DISCONNECTED));
        }

        @Override
        public void onBlockingDBEndCallback(POSState state) throws RemoteException {}

        @Override
        public void onDBUpdateCallback(UpdateTableData[] data) throws RemoteException {
            // Datenbank würde geändert
            boolean someTodo = updateOnServer(data);
        }
    };

    private final IPOSServicePrinterCallback mPrinterCallback = new IPOSServicePrinterCallback.Stub() {
        @Override
        public void printerCallback(PrinterItem data) {
            Log.i(TAG, "printerCallback");
            if (data != null) {
                int type = data.getType();
                if (type == PrinterItem.TYPE_RECHNUNG) {
                    String fileName = data.getFilename();
                    final long token = data.getToken();
                    Log.i(TAG, "printer Callback token: " + token);

                    boolean isRefund = isRefund(token);
                    // send the update to teh OctoboxModule to send to JS
                    /*EventBus.getDefault().post(new ReceiptUpdate.Builder()
                            .pos_key(token + "")
                            .filename(fileName)
                            .isRefund(isRefund)
                            .build());*/
                }
            }
        }
    };

    private final IPOSServicePaymentCallback mClosePaymentListener = new IPOSServicePaymentCallback.Stub() {

        @Override
        public void openPaymentCallback(PaymentData data) {

        }

        @Override
        public void closePaymentCallback(PaymentItems items) {
            if (items != null) {
                Log.i(TAG, "closePaymentCallback items size: " + items.size());
                ArrayList<PaymentItem> orderitems = items.getOrderItemsId();
                if (orderitems != null) {
                    final ContentResolver resolver = getContentResolver();
                    long orderItemInvoiceNr = 0;
                    String receiptUUID = null;
                    for (PaymentItem orderId : orderitems) {
                        long pkId = orderId.getOrderItemId();
                        String[] pkIdStr = new String[]{String.valueOf(pkId)};
                        Cursor c = resolver.query(POSDataProviderTypes.CONTENT_ORDERITEMS_TABLE, new String[]{OrderItemsColumns.TABLE + "." + GETSBY_RECEIPT_UUID, BillColumns.TABLE + "." + BillColumns.SEQUENCE_NUMBER}, OrderItemsColumns.FULL_ID + "=?", pkIdStr, null);
                        if (c.moveToFirst()) {
                            if(orderId.getPaidStatus() == PaymentItem.PAID){
                                receiptUUID = c.getString(0);
                                orderItemInvoiceNr = c.getLong(1);
                                // if we have the invoice number and uuid we send it to JS and return
                                if (orderItemInvoiceNr > 0 && receiptUUID != null) {
                                    // send the update to teh OctoboxModule to send to JS
                                    boolean isRefund = isRefund(orderItemInvoiceNr);
                                    //EventBus.getDefault().post(new ReceiptUpdate.Builder().uuid(receiptUUID).pos_key(orderItemInvoiceNr + "").isRefund(isRefund).build());
                                    break;
                                }
                            }
                        }
                        c.close();
                    }


                }
            }
        }
    };

    public long getInsertUserOnPOS() {
        long id = 0;
        // Getsby User anlegen
        ContentResolver resolver = getContentResolver();
        Uri.Builder builderUser = POSDataProviderTypes.CONTENT_USER_TABLE.buildUpon();
        Cursor cUser = resolver.query(builderUser.build(), projectionUser, UserColumns.NAME + "='" + POS_GETSBY_USER_NAME + "' AND u_aktiv=2", null, null);
        if (cUser.getCount() == 0) {
            // Freien Pincode suchen
            Random randomGenerator = new Random();
            boolean goForward = false;
            int randomPWD = 1234;
            do {
                randomPWD = randomGenerator.nextInt(9999);
                Cursor c = resolver.query(builderUser.build(), projectionUser, UserColumns.PWD + "=?", new String[]{String.valueOf(randomPWD)}, null);
                if (c.moveToFirst()) {
                    goForward = true;
                } else {
                    goForward = false;
                }
                c.close();
            } while (goForward);
            // Neuen Getsby User anlegen
            ContentValues cv = new ContentValues();
            cv.put(UserColumns.NAME, POS_GETSBY_USER_NAME);
            cv.put(UserColumns.PWD, randomPWD);
            cv.put(UserColumns.PERMISSION_TABLE_CHANGE, false);
            cv.put(UserColumns.PERMISSION_CANCEL, false);
            cv.put(UserColumns.PERMISSION_PAY, true);
            cv.put(UserColumns.PERMISSION_ORDER, true);
            cv.put(UserColumns.PERMISSION_EXPORT, false);
            cv.put(UserColumns.PERMISSION_CASH_CHECK, false);
            cv.put(UserColumns.PERMISSION_USER_CHANGE, false);
            cv.put(UserColumns.PERMISSION_ORDER_FROM_OTHERS, false);
            cv.put(UserColumns.PERMISSION_DISCOUNT, true);
            cv.put(UserColumns.PERMISSION_MASTER_DATA_CHANGE, false);
            cv.put(UserColumns.PERMISSION_CASH_CLOSE, false);
            cv.put(UserColumns.PERMISSION_ADD_DEBITOR, false);
            cv.put(UserColumns.PERMISSION_TABLE_CHANGE_RESTRICTED, false);
            cv.put(UserColumns.PERMISSION_FREE_PRICE, false);
            cv.put(UserColumns.PERMISSION_CREDIT_NOTE, true);
            cv.put(UserColumns.PERMISSION_CASH_CLOSE_WITH_SALDO, false);
            cv.put("u_aktiv", 2);
            Uri ret = resolver.insert(builderUser.build(), cv);
            id = Long.valueOf(ret.getLastPathSegment());
        } else if (cUser.moveToFirst()) {
            id = cUser.getLong(0);
        }
        cUser.close();
        return id;
    }

    public long getInsertPrinterOnPOS() {
        long id = 0;
        // Getsby Printer anlegen
        ContentResolver resolver = getContentResolver();
        Uri.Builder builderPrinter = POSDataProviderTypes.CONTENT_PRINTER_TABLE.buildUpon();
        Cursor cPrinter = resolver.query(builderPrinter.build(), projectionPrinter, PrinterColumns.NAME + "='" + POS_GETSBY_PRINTER_NAME + "' AND " + PrinterColumns.MODE  + "=" + String.valueOf(PrinterColumns.MODE_PDF) + " AND pr_aktiv=2", null, null);
        if (cPrinter.getCount() == 0) {
            // Neuen Getsby Printer anlegen
            ContentValues cv = new ContentValues();
            cv.put(PrinterColumns.NAME, POS_GETSBY_PRINTER_NAME);
            cv.put(PrinterColumns.MODE, PrinterColumns.MODE_PDF);
            cv.put(PrinterColumns.ADDRESS, POS_GETSBY_PRINTER_ADDRESS);
            cv.put(PrinterColumns.SOUND, 0);
            cv.put(PrinterColumns.CLIENT_BYPASS, 0);
            cv.put("pr_aktiv", 2);
            Uri ret = resolver.insert(builderPrinter.build(), cv);
            id = Long.valueOf(ret.getLastPathSegment());
        } else if (cPrinter.moveToFirst()) {
            id = cPrinter.getLong(0);
        }
        cPrinter.close();
        return id;
    }

    public long getInsertPaymentMethodOnPOS() {
        long id = 0;
        // Getsby Payment Type anlegen
        ContentResolver resolver = getContentResolver();
        Uri.Builder builderPayment = POSDataProviderTypes.CONTENT_PAYMENTTYPE_TABLE.buildUpon();
        Cursor cPayment = resolver.query(builderPayment.build(), projectionPayment, PaymentTypeColumns.TYPE  + "=" + String.valueOf(POS_GETSBY_PAYMENT_METHOD_TYPE) + " AND ba_aktiv=2", null, null);
        if (cPayment.getCount() == 0) {
            // Neuen Getsby Payment Method anlegen
            ContentValues cv = new ContentValues();
            cv.put(PaymentTypeColumns.NAME, POS_GETSBY_PAYMENT_METHOD_NAME);
            cv.put(PaymentTypeColumns.TYPE, POS_GETSBY_PAYMENT_METHOD_TYPE);
            cv.put("ba_aktiv", 2);
            Uri ret = resolver.insert(builderPayment.build(), cv);
            id = Long.valueOf(ret.getLastPathSegment());
        } else if (cPayment.moveToFirst()) {
            id = cPayment.getLong(0);
        }
        cPayment.close();
        return id;
    }

    public long getInsertDeleteMethodOnPOS() {
        long id = 0;
        // Getsby Storno Type anlegen
        ContentResolver resolver = getContentResolver();
        Uri.Builder builderDelete = POSDataProviderTypes.CONTENT_DELETEREASON_TABLE.buildUpon();
        Cursor cDelete = resolver.query(builderDelete.build(), projectionDelete, DeleteReasonColumns.NAME  + "='" + POS_GETSBY_DELETE_METHOD_NAME + "' AND sg_aktiv=2", null, null);
        if (cDelete.getCount() == 0) {
            // Neuen Getsby Delete Method anlegen
            ContentValues cv = new ContentValues();
            cv.put(DeleteReasonColumns.NAME, POS_GETSBY_DELETE_METHOD_NAME);
            cv.put("sg_aktiv", 2);
            Uri ret = resolver.insert(builderDelete.build(), cv);
            id = Long.valueOf(ret.getLastPathSegment());
        } else if (cDelete.moveToFirst()) {
            id = cDelete.getLong(0);
        }
        cDelete.close();
        return id;
    }

    public long getInsertDiscountProductOnPOS(float tax) {
        long id = 0;
        // Getsby Discount anlegen
        ContentResolver resolver = getContentResolver();
        Uri.Builder builderDiscount = POSDataProviderTypes.CONTENT_PRODUCT_TABLE.buildUpon();
        String name = POS_GETSBY_DISCOUNT_NAME + " " + String.format("%.1f", Math.round(tax * 10) / 10.0f) + "%";
        Cursor cDiscount = resolver.query(builderDiscount.build(), projectionDiscount, ProductColumns.NAME  + "='" + name + "' AND " + ProductColumns.PRICE + " is null AND " + ProductColumns.TAX + "=" + String.valueOf(tax) + " AND " + ProductColumns.TYPE + "=" + ProductColumns.TYPE_DISCOUNT, null, null);
        if (cDiscount.getCount() == 0) {
            // Neuen Getsby Discount anlegen
            ContentValues cv = new ContentValues();
            cv.put(ProductColumns.TYPE, ProductColumns.TYPE_DISCOUNT);
            cv.put(ProductColumns.NAME, name);
            cv.put(ProductColumns.SHORTNAME, "");
            cv.put(ProductColumns.FAVORITE, 0);
            cv.put(ProductColumns.BARCODE, "");
            cv.put(ProductColumns.PRODUCTGROUP_ID, 0);
            cv.put(ProductColumns.ISSUEPLACE_ID, 0);
            cv.put(ProductColumns.TAX, tax);
            cv.put(ProductColumns.AVAILABLE, ProductColumns.AVAILABLE_OK);
            cv.putNull(ProductColumns.PRICE);
            Uri ret = resolver.insert(builderDiscount.build(), cv);
            id = Long.valueOf(ret.getLastPathSegment());
        } else if (cDiscount.moveToFirst()) {
            id = cDiscount.getLong(0);
        }
        cDiscount.close();
        return id;
    }

    public long getInsertDeliveryFeeProductOnPOS(float tax, int fee) {
        long id = 0;
        // Getsby Liefergebühr anlegen
        ContentResolver resolver = getContentResolver();
        Uri.Builder builderDiscount = POSDataProviderTypes.CONTENT_PRODUCT_TABLE.buildUpon();
        String name = POS_GETSBY_DELIVERY_FEE_NAME + " " + fee + "";
        Cursor cDeliveryFee = resolver.query(builderDiscount.build(), projectionDiscount, ProductColumns.NAME  + "='" + name + "' AND " + ProductColumns.PRICE + " is null AND " + ProductColumns.TAX + "=" + String.valueOf(tax) + " AND " + ProductColumns.TYPE + "=" + ProductColumns.TYPE_NORMAL, null, null);
        if (cDeliveryFee.getCount() == 0) {
            // Neue Getsby Liefergebühr anlegen
            ContentValues cv = new ContentValues();
            cv.put(ProductColumns.TYPE, ProductColumns.TYPE_NORMAL);
            cv.put(ProductColumns.NAME, name);
            cv.put(ProductColumns.SHORTNAME, "");
            cv.put(ProductColumns.FAVORITE, 0);
            cv.put(ProductColumns.BARCODE, "");
            cv.put(ProductColumns.PRODUCTGROUP_ID, 0);
            cv.put(ProductColumns.ISSUEPLACE_ID, 0);
            cv.put(ProductColumns.TAX, tax);
            cv.put(ProductColumns.AVAILABLE, ProductColumns.AVAILABLE_OK);
            cv.putNull(ProductColumns.PRICE);
            Uri ret = resolver.insert(builderDiscount.build(), cv);
            id = Long.valueOf(ret.getLastPathSegment());
        } else if (cDeliveryFee.moveToFirst()) {
            id = cDeliveryFee.getLong(0);
        }
        cDeliveryFee.close();
        return id;
    }


    public long getTipProductOnPOS() {
        long id = 0;
        // Trinkgeld holen
        ContentResolver resolver = getContentResolver();
        Uri.Builder builderDiscount = POSDataProviderTypes.CONTENT_PRODUCT_TABLE.buildUpon();
        Cursor cTip = resolver.query(builderDiscount.build(), projectionDiscount, ProductColumns.TYPE  + "=" + ProductColumns.TYPE_TIP, null, null);
        if (cTip.moveToFirst()) {
            id = cTip.getLong(0);
        }
        cTip.close();
        return id;
    }



    private boolean updateOnServer(UpdateTableData[] data) {
        if(syncInProgress){
            for(int i = 0; i< 30; i++){
                if(syncInProgress == false){
                    break;
                }
                Log.i(TAG, "Waiting for sync release " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i == 29){
                    //Sentry.captureMessage("Octobox sync timeout");
                }
            }
        }
        syncInProgress = true;
        boolean someTodo = false;
        boolean productChanged = false;
        boolean productGroupChanged = false;
        boolean tableChanged = false;
        if (data != null && data.length > 0) {
            for (UpdateTableData item : data) {
                // Auf Server pushen
                boolean noCategorySelected = false;
                ContentResolver resolver = getContentResolver();
                switch (item.getTableName()) {
                    case ProductColumns.TABLE:
                    case SetProductOptionColumns.TABLE:
                    case ProductOptionColumns.TABLE:
                    case ProductOptionGroupsColumns.TABLE:
                        productChanged = true;
                        break;

                    case ProductGroupColumns.TABLE:
                        productGroupChanged = true;
                        break;

                    case TableColumns.TABLE:
                        tableChanged = true;
                        break;
                }
            }
        }

        ContentResolver resolver = getContentResolver();
        if (productChanged) {
            Uri.Builder builderProductOptionGroups = POSDataProviderTypes.CONTENT_PRODUCTOPTIONGROUP_TABLE.buildUpon();
            Cursor cProductOptionGroups = resolver.query(builderProductOptionGroups.build(), projectionProductOptionGroup, null, null, null);
            if (cProductOptionGroups.moveToFirst()) {
               // sendProductOptionsGroups(cProductOptionGroups);
            }
            cProductOptionGroups.close();

            Uri.Builder builderProductOption = POSDataProviderTypes.CONTENT_PRODUCTOPTION_TABLE.buildUpon();
            Cursor cProductOption = resolver.query(builderProductOption.build(), projectionProductOption, null, null, null);
            if (cProductOption.moveToFirst()) {
                //sendProductOptions(cProductOption);
            }
            cProductOption.close();

            Uri.Builder builderProducts = POSDataProviderTypes.CONTENT_PRODUCT_TABLE.buildUpon();
            Cursor cProducts = resolver.query(builderProducts.build(), projectionProducts, ProductColumns.TABLE + "." + ProductColumns.PRICE + " IS NOT NULL", null, null);
            if (cProducts.moveToFirst()) {
                //sendProducts(cProducts);
            }
            cProducts.close();
        }

        if (productGroupChanged) {
            Uri.Builder builderProductGroups = POSDataProviderTypes.CONTENT_PRODUCTGROUP_TABLE.buildUpon();
            Cursor cProductGroups = resolver.query(builderProductGroups.build(), projectionProductGroups, null, null, null);
            if (cProductGroups.moveToFirst()) {
                //sendProductGroups(cProductGroups);
            }
            cProductGroups.close();
        }

        if (tableChanged) {
            Uri.Builder builderTables = POSDataProviderTypes.CONTENT_TABLE_TABLE.buildUpon();
            Cursor cTables = resolver.query(builderTables.build(), projectionTable, null, null, null);
            if (cTables.moveToFirst()) {
                //sendTables(cTables);
            }
            cTables.close();
        }
        syncInProgress = false;
        return someTodo;
    }

    private boolean isRefund(long invoiceToken){
        boolean isRefund = false;
        ContentResolver resolver = getContentResolver();
        Cursor c = resolver.query(POSDataProviderTypes.CONTENT_BILL_TABLE, new String[]{BillColumns.TABLE + "." + BillColumns.IS_REFUND}, BillColumns.SEQUENCE_NUMBER + "=?", new String[]{String.valueOf(invoiceToken)}, null);
        if (c.moveToFirst()) {
            isRefund = (c.getInt(0) > 0) ? true : false;
        } else {
            //Sentry.captureMessage("Could not find Invoice for ID " + invoiceToken);
        }
        c.close();
        return isRefund;
    }

    public ContentResolver getPOSContentResolver(){
        return getContentResolver();
    }

    public class HanseaticBinder extends Binder {
        public POSSyncService getService(){
            return POSSyncService.this;
        }
    }

    private class Builder {
        public Builder(POSSyncService posSyncService, String string) {
        }
    }
}
