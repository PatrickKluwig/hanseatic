package com.orderup.pos.ipc;

/**
 * Created by Martin on 10.06.2017.
 */

public class CourseColumns implements POSBaseColumns {

    private CourseColumns() {}

    public static final String TABLE = "gang";
    public static final String NAME = "g_name";
    public static final String ORDER = "g_order";

    public static final String FULL_ID = TABLE + "." + _ID;
    public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
