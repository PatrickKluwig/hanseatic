package com.orderup.pos.ipc;

public class DeleteReasonColumns implements POSBaseColumns {

	private DeleteReasonColumns () {}
	
	public static final String TABLE = "stornogrund";
	public static final String NAME = "sg_name";
	public static final String WAREHOUSE = "sg_schwund";
	public static final String ORDER = "sg_order";

	public static final String FULL_ID = TABLE + "." + _ID;
	public static final String FULL_PK_ID = TABLE + "." + PK_ID;
	
	public static final int WAREHOUSE_NOREDUCTION = 0;
	public static final int WAREHOUSE_REDUCTION = 1;	

}
