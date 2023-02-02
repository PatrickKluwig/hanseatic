package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Martin on 05.06.2017.
 */

public class AlterTableData implements Parcelable {

    private String mTableName;
    public ArrayList<AlterTableItems> mAlterTableItems;

    public AlterTableData(String tableName, ArrayList<AlterTableItems> alterTableItems) {
        mTableName = tableName;
        mAlterTableItems = alterTableItems;
    }

    public AlterTableData() {
        mTableName = null;
        mAlterTableItems = null;
    }

    private AlterTableData(Parcel in) {
        mTableName = in.readString();
        if (mAlterTableItems == null) {
            mAlterTableItems = new ArrayList<AlterTableItems>();
        }
        if (in != null) {
            in.readTypedList(mAlterTableItems, AlterTableItems.CREATOR);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mTableName);
        out.writeTypedList(mAlterTableItems);
    }

    public static final Parcelable.Creator<AlterTableData> CREATOR = new Parcelable.Creator<AlterTableData>() {
        public AlterTableData createFromParcel(Parcel in) {
            return new AlterTableData(in);
        }

        public AlterTableData[] newArray(int size) {
            return new AlterTableData[size];
        }
    };

    public String getTableName() {
        return mTableName;
    }

    public void setTableName(String tableName) {
        this.mTableName = tableName;
    }

    public ArrayList<AlterTableItems> getAlterTableItems() {
        return mAlterTableItems;
    }

    public void setAlterTableItems(ArrayList<AlterTableItems> alterTableItems) {
        this.mAlterTableItems = alterTableItems;
    }
}
