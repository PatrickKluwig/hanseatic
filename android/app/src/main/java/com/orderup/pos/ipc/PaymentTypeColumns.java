package com.orderup.pos.ipc;

public class PaymentTypeColumns implements POSBaseColumns {

	private PaymentTypeColumns () {}
	
	public static final String TABLE = "bezahlart";
	public static final String NAME = "ba_name";
	public static final String TYPE = "ba_fp_paymentTypeIndex";
	public static final String EXTERNALCONTROL = "ba_external_control";
	public static final String ORDER = "ba_order";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
	
	public static final int TYPE_CASH = 0;
	public static final int TYPE_BANKOMAT = 20;
	public static final int TYPE_CREDITCARD = 21;
	public static final int TYPE_DEBITOR = 30;
	public static final int TYPE_VOUCHER = 40;
	public static final int TYPE_BANK = 50;
	public static final int TYPE_CASHLESS = 60;
	public static final int TYPE_GENERAL = 100;
	
	public static final int EXTERNALCONTROL_DISABLED = 0;
	public static final int EXTERNALCONTROL_ENABLED = 1;	
}
