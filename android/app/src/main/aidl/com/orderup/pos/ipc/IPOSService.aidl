package com.orderup.pos.ipc;

import com.orderup.pos.ipc.IPOSServicePaymentCallback;
import com.orderup.pos.ipc.IPOSServicePaymentDlgCallback;
import com.orderup.pos.ipc.ClosePaymentData;
import com.orderup.pos.ipc.POSState;
import com.orderup.pos.ipc.IPOSServiceDBCallback;
import com.orderup.pos.ipc.OrderData;
import com.orderup.pos.ipc.PaidOrderData;
import com.orderup.pos.ipc.UpdateOrderData;
import com.orderup.pos.ipc.IPOSServicePrinterCallback;

interface IPOSService {

	void openPayment(IPOSServicePaymentCallback cb);
	
	void closePayment(boolean succesfull, IPOSServicePaymentCallback cb, in ClosePaymentData data);

	void closePaymentDlg(IPOSServicePaymentDlgCallback cb);

	boolean requestDatabaseLock();

	void releaseDatabaseLock();

	String getDeviceIdentifier();

	POSState getState();

	void setDBListener(in IPOSServiceDBCallback cb);

    int newOrder(in OrderData data);

    int newPaidOrder(in PaidOrderData data);

    int updateOrder(in UpdateOrderData data);

    void setPrinterListener(in IPOSServicePrinterCallback cb);
}