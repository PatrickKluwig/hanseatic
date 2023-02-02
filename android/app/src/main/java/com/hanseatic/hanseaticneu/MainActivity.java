package com.hanseatic.hanseaticneu;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugins.GeneratedPluginRegistrant;

import io.flutter.plugin.common.MethodChannel;
import com.hanseatic.hanseaticneu.model.OrderItem;
import com.hanseatic.hanseaticneu.model.OrderItemModifierItem;
import com.hanseatic.hanseaticneu.model.Receipt;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "samples.flutter.dev/battery";

    POSServiceReceiverCall myReceiver;
    POSSyncService mService;
    boolean mBound = false;
    boolean serviceConnected = false;
    MethodChannel.Result keepResult = null;
    @Override
    protected void onStart() {
        myReceiver = new POSServiceReceiverCall();
        IntentFilter intentFilter = new IntentFilter();
        Log.i("POSSyncService", "TESTMANN");
        intentFilter.addAction("ACTION_START_FOREGROUND_SERVICE");
        registerReceiver(myReceiver, intentFilter);

        super.onStart();
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler((call, result) -> {
                    Log.i("POSSyncService", call.method);
                    if (call.method.equals("connect")) {
                        Intent intent = new Intent(this, POSSyncService.class);
                        intent.setAction(POSSyncService.ACTION_START_FOREGROUND_SERVICE);
                        Log.i("POSSyncService", POSSyncService.ACTION_START_FOREGROUND_SERVICE);
                        startService(intent);
                        boolean bind = this.bindService(intent, connection, Context.BIND_AUTO_CREATE);

                        if (bind) {
                            serviceConnected = true;
                            keepResult = result;
                            result.success("CONNECT");
                        } else {
                            result.success("ERROR CONNECTION");
                        }


                    } else if (serviceConnected) {
                        if (call.method.equals("makeorder")) {
                            try {

                                ArrayList<HashMap> receiptData = (ArrayList<HashMap>) call.arguments;
                                for(int i = 0; i < receiptData.size(); i++)
                                {

                                    Map<String, Object> order = receiptData.get(i);

                                    Log.i("TESTMANN ", "TESTFRAU");
                                    Receipt receipt = buildReceipt(order);
                                    mService.makeOrder(receipt, 0);
                                    /*for (Map.Entry<String, Object> set :
                                            order.entrySet()) {

                                        // Printing all elements of a Map
                                        Log.i("POSSyncService", set.getKey());

                                        //Log.i("POSSyncServiceVAL", set.getValue());
                                        System.out.println(set.getKey() + " = " + set.getValue());
                                    } */
                                }
                                // Receipt receipt = buildReceipt(receiptData);
                                //mService.makeOrder(receipt, 0);
                                result.success("ORDER SUCCESS");
                            } catch (Exception e) {
                                result.error(null, "ORDER FAILED", null);
                            }
                        }

                    } else {
                        result.error(null, "App not connected to service", null);
                    }


                    // String _data = POSSyncService.helloFromService();
                    // result.success(_data);
                    //result.success("ERFOLGREICH");

                });
    }

    private class POSServiceReceiverCall extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("POSRECEIVER", "POS Service Start at Machine startup!");
            Intent posSyncService = new Intent(context, POSSyncService.class);
            posSyncService.setAction(POSSyncService.ACTION_START_FOREGROUND_SERVICE);
            context.startService(posSyncService);
        }
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Log.i("SERVICE CONNECTION", "Service connected");
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            POSSyncService.HanseaticBinder binder = (POSSyncService.HanseaticBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            //connecting = false;
            mBound = false;
        }
    };


    private Receipt buildReceipt(Map receiptData){
        Log.i("RECEIPT", "receipt from js " + receiptData);
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        // ArrayList jsOrderItems = receiptData.getArray("orderItems");

        // for (int i = 0; i < receiptData.size(); i++) {
        // HashMap jsOrderItem = jsOrderItems.getMap(i);

        OrderItem.Builder orderItemBuilder = new OrderItem.Builder()
                .uuid((String) receiptData.get("uuid"))
                .tax((Double) receiptData.get("product_tax"))
                .discount((Integer) receiptData.get("discount"))
                .product_price((Integer) receiptData.get("product_price"))
                .product_pos_key((String) receiptData.get("product_pos_key"))
                .product_title((String) receiptData.get("product_title"));

        try {
            String comment = (String) receiptData.get("comment");
            orderItemBuilder.comment(comment);
        } catch (NullPointerException e) {
            // no comment exists, ignoring
        }

            /*try {
                ReadableArray jsModifierItems = jsOrderItem.getArray("modifierItems");
                ArrayList<OrderItemModifierItem> modifierItems = new ArrayList<>();
                for (int ii = 0; ii < jsModifierItems.size(); ii++) {
                    ReadableMap jsModifierItem = jsModifierItems.getMap(ii);
                    OrderItemModifierItem modifierItem = new OrderItemModifierItem
                            .Builder()
                            .modifier_item_pos_key(jsModifierItem.getString("modifier_item_pos_key"))
                            .price_modifier(jsModifierItem.getInt("price_modifier"))
                            .build();

                    modifierItems.add(modifierItem);

                }
                orderItemBuilder.modifier_items(modifierItems);

            } catch (NullPointerException e) {
                // no modifier items exist, ignoring
            } */


        OrderItem orderItem = orderItemBuilder.build();

        Log.i("ORDER ITEM IS", "Order Item: " + orderItem);
        orderItems.add(orderItem);

        // }


        Receipt.Builder receiptBuilder = new Receipt
                .Builder()
                .table_pos_key("102")
                .orderItems(orderItems)
                .uuid("122")
                .paymentType("CASH");

        try {
            //receiptBuilder.tip(receiptData.getInt("tip"));
        } catch (NullPointerException e) {
            // no tip given, ignoring
        }


        try {
            //receiptBuilder.printText(receiptData.getString("printText"));
        } catch (NullPointerException e) {
            // no printText available, ignoring
        }

        Receipt receipt = receiptBuilder.build();
        Log.i("RECEIPT IS ", "Receipt " + receipt);
        return receipt;

    }

}

