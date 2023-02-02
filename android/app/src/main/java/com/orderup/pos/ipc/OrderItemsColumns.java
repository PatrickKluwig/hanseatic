package com.orderup.pos.ipc;

public class OrderItemsColumns implements POSBaseColumns {

	private OrderItemsColumns () {}	

	public static final String TABLE = "bestellungen_produkte";
	public static final String PRODUCT_ID = "_bp_produkt";
	public static final String ORDER_ID = "_bp_bestellung";
	public static final String PAYSTATUS = "bp_bezahlt";
	public static final String PROCESSEDSTATUS = "bp_bearbeitet";
	public static final String PAYTIME = "bp_bezahlt_zeit";
	public static final String COMMENT = "bp_kommentar";
	public static final String PAYMENTTYPE_ID = "_ba_bezahlart";
	public static final String PAYMENT2TYPE_ID = "_ba_bezahlart_2";
	public static final String PAYMENT2VALUE = "_ba_bezahlart_2_value";	
	public static final String DISCOUNT_ID = "_ba_rabatte";
	public static final String DELETEREASON_ID = "_ba_stornogrund";
	public static final String BILL_ID = "_r_nummer";
	public static final String RECEIPT_ID = "_b_nummer";
	public static final String USER_ID = "_bp_bezahlt_user_id";
	public static final String TYPE = "type";
	public static final String SEQUENCEORDER_ID = "_ba_gang";
	public static final String SET_PRODUKT_OPTION_ID = "_ba_bestellungen_produkte_produkte_optionen";
	public static final String TAX_SELECTION = "bp_steuer_selection";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
	
	public static final int PAYSTATUS_NOTPAID = 0;
	public static final int PAYSTATUS_PAID = 1;
	public static final int PAYSTATUS_DELETED = 2;	
	
	public static final int TYPE_PRODUCT = 0;	
	public static final int TYPE_PRODUCTOPTION = 1;
}
