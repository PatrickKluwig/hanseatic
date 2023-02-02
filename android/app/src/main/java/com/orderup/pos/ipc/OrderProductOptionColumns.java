package com.orderup.pos.ipc;

public class OrderProductOptionColumns implements POSBaseColumns {

	private OrderProductOptionColumns() {}
	
	public static final String TABLE = "bestellungen_produkte_optionen";
	public static final String PROCESSEDSTATUS = "bpo_bearbeitet";

	public static final String PRODUCT_OPTION_ID = "_bpo_produkte_optionen";
	public static final String SET_PRODUKT_OPTION_ID = "_bpo_bestellungen_produkte_produkte_optionen";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
