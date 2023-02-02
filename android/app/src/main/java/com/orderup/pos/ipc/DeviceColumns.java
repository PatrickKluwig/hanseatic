package com.orderup.pos.ipc;

public class DeviceColumns implements POSBaseColumns {

	private DeviceColumns () {}

	public static final String TABLE = "geraete";
	public static final String NAME = "g_name";
	public static final String IDENTIFIER = "g_unique_id";
	public static final String LOGIN_MODE = "g_mode";
	public static final String LOGIN_MODE_TIMEOUT = "g_timeout";
	public static final String TYPE = "g_type";
	public static final String STOCK_LOCATION = "g_lagerort";
	public static final String TYPE_RETAIL_TABLE_ID = "_g_retail_ziel_id";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;

	public static final int LOGIN_MODE_PERMANENT = 0;
	public static final int LOGIN_MODE_TEMPORARY = 1;

	public static final int TYPE_GASTRO = 0;
	public static final int TYPE_EVENT = 1;
	public static final int TYPE_RETAIL = 2;
}
