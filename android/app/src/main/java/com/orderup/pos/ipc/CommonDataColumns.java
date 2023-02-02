package com.orderup.pos.ipc;

public class CommonDataColumns implements POSBaseColumns {

	private CommonDataColumns() {}

	public static final String TABLE = "konfiguration";
	public static final String KEY = " k_konfigurations_typ";
	public static final String VALUE1 = "k_konfigurations_wert";
	public static final String VALUE2 = "k_konfigurations_wert_opt";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;

	public static final String CURRENCY = "Waehrung";
	public static final String PRINT_HEADER = "Lokal";
	public static final String PRINT_FOOTER = "Footer";
	public static final String COMPANY_TAX_IDENTIFIER = "UID";
	public static final String COMPANY_ADDRESS = "Adresse";
	public static final String COMPANY_TEL = "Tel";
	public static final String COMPANY_MAIL = "Mail";
	public static final String COMPANY = "Firma";
}
