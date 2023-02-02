package com.orderup.pos.ipc;

public class DiscountColumns implements POSBaseColumns {

	private DiscountColumns () {}
		
	public static final String TABLE = "rabatte";
	public static final String NAME = "ra_name";
	public static final String VALUE = "ra_wert";
	public static final String ORDER = "ra_order";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
