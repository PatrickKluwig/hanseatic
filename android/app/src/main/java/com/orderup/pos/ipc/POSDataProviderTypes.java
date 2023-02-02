package com.orderup.pos.ipc;

import android.net.Uri;

public class POSDataProviderTypes {

	public static final String PROVIDER_NAME = "com.orderup.pos.ipc.POSDataProvider";

	public static final String PRIMARYKEY_SEGMENT = "PK";

	public static final String FUNCTION_IS_DB_RUNNING = "FUNCisDBRunning";
	public static final String FUNCTION_IS_DB_RUNNING_RETURN_ARG = "isDBRunning";

	public static final Uri CONTENT_BASIC = Uri.parse("content://"+ PROVIDER_NAME);
	
	public static final Uri CONTENT_PRINTER_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + PrinterColumns.TABLE);	
	public static final Uri CONTENT_PAYMENTTYPE_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + PaymentTypeColumns.TABLE);
	public static final Uri CONTENT_DELETEREASON_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + DeleteReasonColumns.TABLE);	
	public static final Uri CONTENT_DISCOUNT_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + DiscountColumns.TABLE);		
	public static final Uri CONTENT_CUSTOMER_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + CustomerColumns.TABLE);	
	public static final Uri CONTENT_PRODUCT_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + ProductColumns.TABLE);
	public static final Uri CONTENT_PRODUCTGROUP_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + ProductGroupColumns.TABLE);
	public static final Uri CONTENT_PRODUCTOPTION_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + ProductOptionColumns.TABLE);
	public static final Uri CONTENT_PRODUCTOPTION_PRODUCTOPTIONGROUP_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + ProductOptionProductOptionGroupsColumns.TABLE);
	public static final Uri CONTENT_PRODUCTOPTIONGROUP_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + ProductOptionGroupsColumns.TABLE);
	public static final Uri CONTENT_SET_PRODUCTOPTIONGROUP_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + SetProductOptionColumns.TABLE);
	public static final Uri CONTENT_ORDER_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + OrderColumns.TABLE);
	public static final Uri CONTENT_USER_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + UserColumns.TABLE);			
	public static final Uri CONTENT_DEVICE_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + DeviceColumns.TABLE);		
	public static final Uri CONTENT_TABLE_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + TableColumns.TABLE);
	public static final Uri CONTENT_ROOM_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + RoomColumns.TABLE);
	public static final Uri CONTENT_ISSUEPLACE_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + IssuePlaceColumns.TABLE);
	public static final Uri CONTENT_HAPPYHOUR_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + HappyHourColumns.TABLE);
	public static final Uri CONTENT_SEQUENCEORDER_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + SequenceOrderColumns.TABLE);
	public static final Uri CONTENT_RECEIPT_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + ReceiptColumns.TABLE);
	public static final Uri ORDER_PRODUCT_OPTION_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + OrderProductOptionColumns.TABLE);
	public static final Uri ORDER_SET_ORDER_PRODUCT_OPTION_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + SetOrderProductOptionsColumns.TABLE);
	public static final Uri CONTENT_ORDERITEMS_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + OrderItemsColumns.TABLE);
	public static final Uri CONTENT_BILL_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + BillColumns.TABLE);
	public static final Uri CONTENT_STAND_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + StandColumns.TABLE);
	public static final Uri CONTENT_COMMONDATA_TABLE = Uri.parse("content://"+ PROVIDER_NAME + "/" + CommonDataColumns.TABLE);
}
