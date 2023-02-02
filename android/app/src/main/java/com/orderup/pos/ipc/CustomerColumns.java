package com.orderup.pos.ipc;

public class CustomerColumns implements POSBaseColumns {

	private CustomerColumns () {}

	public static final String TABLE = "kunden";
	public static final String NAME = "k_name";
	public static final String ADDRESS = "k_adresse";
	public static final String ZIP = "k_zip";
	public static final String CITY = "k_city";
	public static final String TEL1 = "k_tel";
	public static final String TEL2 = "k_tel_2";
	public static final String TEL3 = "k_tel_3";
	public static final String MAIL = "k_mail";
	public static final String INFO = "k_info";
	public static final String CARDNUMBER = "k_kartennummer";
	public static final String TAXNUMBER = "k_steuer_nr";
	public static final String ROOM = "k_room";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
