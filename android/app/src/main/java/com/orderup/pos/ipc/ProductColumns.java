package com.orderup.pos.ipc;


public class ProductColumns implements POSBaseColumns {

	private ProductColumns () {}	
	
	public static final String TABLE = "produkte";
	public static final String NAME = "p_name";
	public static final String SHORTNAME = "p_kurzname";
	public static final String PRICE = "p_preis";
	public static final String FREE_PRICE_LOW = "p_preis_low";
	public static final String FREE_PRICE_HIGH= "p_preis_high";
	public static final String PRICE_HAPPYHOUR = "p_preis_hh";
	public static final String AVAILABLE = "p_verfuegbarkeit";
	public static final String TAX = "p_steuer";
	public static final String FAVORITE = "p_favorit";
	public static final String ISSUE_PLACE_ROOM_BASED = "p_erzeugerstandort_zg_based";
	public static final String BARCODE = "p_ean_code";
	public static final String ORDER = "p_order";
	public static final String PRICE_SCALE_FACTOR = "p_preis_scale_fac";
	public static final String TYPE = "p_type";
	public static final String TAX_SEC = "p_steuer_sec";

	public static final String PRODUCTGROUP_ID = "_p_produktgruppe_id";
	public static final String ISSUEPLACE_ID = "_p_erzeugerstandort_id";
	public static final String HAPPYHOUR_ID = "_p_happyhour_id";
	public static final String SET_PRODUCT_OPTION_ID = "_p_master_produkt_optionen_id";
	public static final String DEFAULT_SEQUENCE_ID = "_p_default_gang";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;

	public static final int AVAILABLE_NOT = 0;
	public static final int AVAILABLE_OK = 1;

	public static final int TYPE_NORMAL = 0;
	public static final int TYPE_TIP = 1;
	public static final int TYPE_DISCOUNT = 2;
}
