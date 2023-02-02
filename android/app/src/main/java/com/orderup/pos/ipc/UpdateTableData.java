package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Martin on 07.06.2017.
 */

public class UpdateTableData implements Parcelable {

    private String mTableName;
    private ArrayList<Long> mUpdateData;

    public UpdateTableData(String tableName, ArrayList<Long> updateData) {
        mTableName = tableName;
        mUpdateData = updateData;
    }

    public UpdateTableData() {
        mTableName = null;
        mUpdateData = null;
    }

    private UpdateTableData(Parcel in) {
        mTableName = in.readString();
        if (mUpdateData == null) {
            mUpdateData = new ArrayList<Long>();
        }
        if (in != null) {
            in.readList(mUpdateData, null);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mTableName);
        out.writeList(mUpdateData);
    }

    public static final Parcelable.Creator<UpdateTableData> CREATOR = new Parcelable.Creator<UpdateTableData>() {
        public UpdateTableData createFromParcel(Parcel in) {
            return new UpdateTableData(in);
        }

        public UpdateTableData[] newArray(int size) {
            return new UpdateTableData[size];
        }
    };

    public String getTableName() {
        return mTableName;
    }

    public void setTableName(String tableName) {
        this.mTableName = tableName;
    }

    public ArrayList<Long> getUpdateData() {
        return mUpdateData;
    }

    public void setUpdateData(ArrayList<Long> updateData) {
        this.mUpdateData = updateData;
    }
}
