package com.orderup.pos.ipc;

import com.orderup.pos.ipc.ClosePaymentDlgData;

interface IPOSServicePaymentDlgCallback {

	boolean closePaymentDlgCallback(in ClosePaymentDlgData data);
	
}