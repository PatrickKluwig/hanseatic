package com.orderup.pos.ipc;

public class SetOrderProductOptionsColumns implements POSBaseColumns {

	private SetOrderProductOptionsColumns() {}

	public static final String TABLE = "bestellungen_produkte_produkte_optionen";
	public static final String PRODUCTOPTION_LINECOUNT = "produkt_option_count";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
