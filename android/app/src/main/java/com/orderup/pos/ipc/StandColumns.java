package com.orderup.pos.ipc;

public class StandColumns implements POSBaseColumns {

	private StandColumns() {}
	
	public static final String TABLE = "zwischenstand";
	public static final String TIMESTAMP = "zs_zeit";
	public static final String SIGNATURE = "zs_hash";
	public static final String TYPE = "zs_type";
	public static final String SEQUENCE_NUMBER = "zwischenstand_sequence";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;

	public static final int TYPE_START = 1;
	public static final int TYPE_NULL = 2;
	public static final int TYPE_STORNO = 3;
	public static final int TYPE_TRAINING = 4;
}
