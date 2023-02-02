package com.orderup.pos.ipc;

public class UserColumns implements POSBaseColumns {

	private UserColumns () {}	

	public static final String TABLE = "user";
	public static final String NAME = "u_name";
	public static final String PWD = "u_pwd";
	public static final String PWD_ADDIMAT = "u_addimat_pwd";
	public static final String PERMISSION_TABLE_CHANGE = "u_per_tischwechsel";
	public static final String PERMISSION_CANCEL = "u_per_stornieren";
	public static final String PERMISSION_PAY = "u_per_bezahlen";
	public static final String PERMISSION_ORDER = "u_per_bestellen";
	public static final String PERMISSION_EXPORT = "u_per_export";
	public static final String PERMISSION_CASH_CHECK = "u_per_zwischenstand";
	public static final String PERMISSION_USER_CHANGE = "u_per_teilnehmerverwaltung";
	public static final String PERMISSION_ORDER_FROM_OTHERS = "u_per_allebestellungen";
	public static final String PERMISSION_DISCOUNT = "u_per_rabatte";
	public static final String PERMISSION_MASTER_DATA_CHANGE = "u_per_konfigaendern";
	public static final String PERMISSION_CASH_CLOSE = "u_per_kassaschluss";
	public static final String PERMISSION_ADD_DEBITOR = "u_per_debitor_add";
	public static final String PERMISSION_TABLE_CHANGE_RESTRICTED = "u_per_ext_tischwechsel";
	public static final String PERMISSION_FREE_PRICE = "u_per_freier_preis";
	public static final String PERMISSION_CREDIT_NOTE = "u_per_gutschrift";
	public static final String PERMISSION_CASH_CLOSE_WITH_SALDO = "u_per_kassaschluss_saldo";
	public static final String PERMISSION_CASH_DRAWER = "u_per_lade";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
