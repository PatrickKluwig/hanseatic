package com.orderup.pos.ipc;

public class IssuePlaceColumns implements POSBaseColumns {

	private IssuePlaceColumns() {}
	
	public static final String TABLE = "erzeugerstandort";
	public static final String NAME = "e_name";
	public static final String MODE = "e_mode";
	public static final String PRINTER_ID = "_e_printer_id";
	public static final String DEVICE_ID = "_e_geraete_id";
	public static final String SEPARATE_ORDER_MODE = "e_gang_mode";
	public static final String ORDER = "e_order";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;

	public static final int MODE_DEVICE = 0;
	public static final int MODE_PRINTER = 1;
}
