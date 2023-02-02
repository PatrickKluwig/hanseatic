package com.orderup.pos.ipc;

public class BillColumns implements POSBaseColumns {

	private BillColumns () {}	
	
	public static final String TABLE = "rechnungen";
	public static final String TIMESTAMP = "r_zeit";
	public static final String TOTAL = "r_sum";
	public static final String SIGNATURE = "r_hash";
	public static final String CUSTOMER_ID = "_r_kunde_id";
	public static final String IS_REFUND = "r_refund";
	public static final String HAS_REFUND = "r_has_refund";
	public static final String SEQUENCE_NUMBER = "rechnungen_sequence";
	public static final String SIGNATURE_TIMESTAMP = "r_fp_datetime";
	public static final String PAYMENT_PROVIDER_DATA = "r_paymentProviderData";
	public static final String TIP = "r_tip";
	public static final String MONEY_CHANGE = "r_change";
	public static final String TSE_DATA = "tse_data";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;

	public static final int IS_REFUND_NO = 0;
	public static final int IS_REFUND_YES = 1;	
	
	public static final int HAS_REFUND_NO = 0;
	public static final int HAS_REFUND_YES = 1;		
}
