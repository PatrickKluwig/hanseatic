package com.orderup.pos.ipc;

public class ProductOptionColumns implements POSBaseColumns {

	private ProductOptionColumns() {}
	
	public static final String TABLE = "produkt_optionen";
	public static final String NAME = "po_name";
	public static final String PRICE = "po_preis";
	public static final String TAX = "po_steuer";
	public static final String AVAILABLE = "po_verfuegbarkeit";
	public static final String ISSUE_PLACE_ROOM_BASED = "po_erzeugerstandort_zg_based";
	public static final String ORDER = "po_order";
	public static final String TAX_SEC = "po_steuer_sec";

	public static final String ISSUEPLACE_ID = "_po_erzeugerstandort_id";

	public static final String MASTER_PRODUKT_INHERIT_ID = "_po_hauptprodukt_id";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
