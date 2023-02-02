package com.orderup.pos.ipc;

public class ProductOptionGroupsColumns implements POSBaseColumns {

	private ProductOptionGroupsColumns() {}
	
	public static final String TABLE = "produkt_optionen_gruppe";
	public static final String NAME = "pog_name";
	public static final String MULTIPLE_SELECTION = "pog_auswahltyp";
	public static final String OPTIONAL = "pog_optional";

	public static final String SET_PRODUKT_OPTION_ID = "_pog_master_produkt_optionen_id";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
