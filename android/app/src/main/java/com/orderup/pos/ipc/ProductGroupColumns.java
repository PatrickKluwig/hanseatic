package com.orderup.pos.ipc;

public class ProductGroupColumns implements POSBaseColumns {

	private ProductGroupColumns() {}
	
	public static final String TABLE = "produktgruppen";
	public static final String NAME = "pg_name";
	public static final String COLOR = "pg_color";
	public static final String TAX = "pg_steuer";
	public static final String ORDER = "pg_order";
	public static final String SYMBOL = "pg_symbol";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
