package com.orderup.pos.ipc;

public class OrderColumns implements POSBaseColumns {

	private OrderColumns () {}	

	public static final String TABLE = "bestellungen";
	public static final String ORDERTIME = "b_zeit";
	public static final String ORDERTABLE = "_b_ziel_id";
	public static final String USER_ID = "_b_user_id";
	public static final String DEVICE_ID = "_b_geraete_id";
	public static final String TABLESSPLIT = "b_ziel_split";
	public static final String ORDERLINECOUNT = "b_produkt_count";
	public static final String TABLECHANGECOUNT = "b_tischwechsel_count";
	public static final String INFO = "b_info";
	public static final String TSE_DATA = "tse_data";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
	
	public static final int TABLESPLIT_A = 0;
	public static final int TABLESPLIT_B = 1;
	public static final int TABLESPLIT_C = 2;
}
