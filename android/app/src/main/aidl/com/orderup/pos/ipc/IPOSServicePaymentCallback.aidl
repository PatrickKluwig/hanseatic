package com.orderup.pos.ipc;

import com.orderup.pos.ipc.PaymentData;
import com.orderup.pos.ipc.PaymentItems;

interface IPOSServicePaymentCallback {

	void openPaymentCallback(in PaymentData data);
	
	void closePaymentCallback(in PaymentItems items);
	
}