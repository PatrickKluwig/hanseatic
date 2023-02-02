package com.orderup.pos.ipc;

public class ProductOptionProductOptionGroupsColumns implements POSBaseColumns {

	private ProductOptionProductOptionGroupsColumns() {}
	
	public static final String TABLE = "produkt_optionen_produkt_optionen_gruppe";

	public static final String PRODUKT_OPTION_ID = "_popog_produkt_optionen_id";
	public static final String PRODUKT_OPTION_GROUP_ID = "_popog_produkt_optionen_gruppe_id";
	public static final String ORDER = "popog_order";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
