package com.orderup.pos.ipc;

public class PrinterColumns implements POSBaseColumns {

	private PrinterColumns () {}
	
	/**
     * The table name of printers
     */
	public static final String TABLE = "printer";
	
	/**
     * The name of the printer
     * <P>Type: TEXT</P>
     */
	public static final String NAME = "pr_name";
	public static final String ADDRESS = "pr_addr";
	public static final String TYPE = "pr_type";
	public static final String MODE = "pr_connect_mode";
	public static final String CLIENT_BYPASS = "pr_control";
	public static final String DISPLAY_NAME = "pr_display_name";
	public static final String DRAWER_TYPE = "pr_drawer";
	public static final String SOUND = "pr_sound";
	public static final String ORDER = "pr_order";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
	
	public static final String TYPE_VOID = "";
	public static final String TYPE_EPSON_TM_T20 = "TM-T20";
	public static final String TYPE_EPSON_TM_T88V = "TM-T88V"; 
	public static final String TYPE_EPSON_FP_90 = "FP-90";
	public static final String TYPE_EPSON_TM_T70II = "TM-T70II";
	public static final String TYPE_EPSON_TM_P80 = "TM-P80";
	public static final String TYPE_EPSON_TM_P20 = "TM-P20";
	public static final String TYPE_EPSON_TM_m10 = "TM-m10";
	public static final String TYPE_EPSON_TM_m30 = "TM-m30";
	public static final String TYPE_EPSON_TM_T20II = "TM-T20II";
	public static final String TYPE_EPSON_TM_U220 = "TM-U220";
	public static final String TYPE_BIXOLON_SPP_R200III = "SPP-R200III";
	
	public static final int MODE_TCPIP = 0;
	public static final int MODE_USB = 1;
	public static final int MODE_BLUETOOTH = 2;
	public static final int MODE_DUMMY = 3;
	public static final int MODE_PDF = 4;
}
