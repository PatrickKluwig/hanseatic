package com.orderup.pos.ipc;

public class TableColumns implements POSBaseColumns {

	private TableColumns() {}

	public static final String TABLE = "ziel";
	public static final String NAME = "z_name";
	public static final String SIZE = "z_size";
	public static final String SPLIT = "z_split";
	public static final String ORDER = "z_order";

	public static final String ROOM_ID = "_z_ziel_gruppe";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
	
	public static final int SIZE_SMALL = 0;
	public static final int SIZE_NORMAL = 1;
	public static final int SIZE_BIG = 2;
	
	public static final int TABLESPLIT_NONE = 0;
	public static final int TABLESPLIT_AB = 1;
	public static final int TABLESPLIT_ABC = 2;	
}
