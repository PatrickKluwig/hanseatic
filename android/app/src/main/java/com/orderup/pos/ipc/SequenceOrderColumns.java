package com.orderup.pos.ipc;

public class SequenceOrderColumns implements POSBaseColumns {

	private SequenceOrderColumns() {}
		
	public static final String TABLE = "gang";
	public static final String NAME = "g_name";
	public static final String ORDER = "g_order";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
