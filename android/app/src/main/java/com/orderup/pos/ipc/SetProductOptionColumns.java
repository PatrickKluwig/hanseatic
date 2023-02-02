package com.orderup.pos.ipc;

public class SetProductOptionColumns implements POSBaseColumns {

	private SetProductOptionColumns() {}
	
	public static final String TABLE = "master_produkt_optionen";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
