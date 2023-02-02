package com.orderup.pos.ipc;

public class ReceiptColumns implements POSBaseColumns {

	private ReceiptColumns () {}	

	public static final String TABLE = "bons";
	public static final String TIMESTAMP = "b_zeit";
	public static final String TOTAL = "b_sum";
	public static final String SIGNATURE = "b_hash";
	public static final String CUSTOMER_ID = "_b_kunde_id";
	public static final String PRINTED = "b_printed";
	public static final String IS_REFUND = "b_refund";
	public static final String HAS_REFUND = "b_has_refund";
	public static final String SEQUENCE_NUMBER = "bons_sequence";
	public static final String SIGNATURE_TIMESTAMP = "b_fp_datetime";
	public static final String PAYMENT_PROVIDER_DATA = "b_paymentProviderData";
	public static final String TIP = "b_tip";
	public static final String MONEY_CHANGE = "b_change";
	public static final String TSE_DATA = "tse_data";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
	
	public static final int PRINTED_NO = 0;
	public static final int PRINTED_YES = 1;	

	public static final int IS_REFUND_NO = 0;
	public static final int IS_REFUND_YES = 1;	
	
	public static final int HAS_REFUND_NO = 0;
	public static final int HAS_REFUND_YES = 1;
}
