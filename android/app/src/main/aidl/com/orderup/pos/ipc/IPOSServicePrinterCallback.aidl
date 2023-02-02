package com.orderup.pos.ipc;

import com.orderup.pos.ipc.PrinterItem;

interface IPOSServicePrinterCallback {

	void printerCallback(in PrinterItem data);

}