package com.orderup.pos.ipc;

public class VoucherColumns implements POSBaseColumns {

	private VoucherColumns() {}

	public static final String TABLE = "gutschein_konto";
	public static final String VOUCHER_BALANCE = "gk_diff_sum";
	public static final String VOUCHER_VALUE = "gk_value";
	public static final String PAYMENTTYPE_ID = "_gk_bezahlart_id";
	public static final String DISCOUNT_ID = "_gk_rabatt_id";
	public static final String USER_ID = "_gk_user_id";
	public static final String BILL_ID = "_gk_rechnung_id";
	public static final String RECEIPT_ID = "_gk_bon_id";
	public static final String EXTRA_DATA = "gk_extradata";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
