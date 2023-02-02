package com.orderup.pos.ipc;

/**
 * Created by Martin on 10.06.2017.
 */

public class HappyHourColumns implements POSBaseColumns {

    private HappyHourColumns() {}

    public static final String TABLE = "happyhour";
    public static final String NAME = "hh_name";
    public static final String START_TIME_1 = "hh_from";
    public static final String START_END_1 = "hh_to";
    public static final String START_TIME_2 = "hh_from_sec";
    public static final String START_END_2 = "hh_to_sec";
    public static final String MONDAY_ENABLED = "hh_day_mon";
    public static final String TUESDAY_ENABLED = "hh_day_tue";
    public static final String WEDNESDAY_ENABLED = "hh_day_wed";
    public static final String THURSDAY_ENABLED = "hh_day_thu";
    public static final String FRIDAY_ENABLED = "hh_day_fri";
    public static final String SATURDAY_ENABLED = "hh_day_sat";
    public static final String SUNDAY_ENABLED = "hh_day_sun";

    public static final String FULL_ID = TABLE + "." + _ID;
    public static final String FULL_PK_ID = TABLE + "." + PK_ID;
}
