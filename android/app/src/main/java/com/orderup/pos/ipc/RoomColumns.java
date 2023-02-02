package com.orderup.pos.ipc;

public class RoomColumns implements POSBaseColumns {

	private RoomColumns() {}

	public static final String TABLE = "ziel_gruppen";
	public static final String NAME = "zg_name";
	public static final String ORDER = "zg_order";

	public static final String ALTERNATIVE_ISSUE_PLACE_ID = "_zg_erzeugerstandort_id";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
