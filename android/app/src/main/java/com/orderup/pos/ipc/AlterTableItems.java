package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Martin on 26.05.2017.
 */

public class AlterTableItems implements Parcelable {

    public static final int FIELD_TYPE_INTEGER = 0;
    public static final int FIELD_TYPE_LONG = 1;
    public static final int FIELD_TYPE_TEXT = 2;
    public static final int FIELD_TYPE_BLOB = 3;
    public static final int FIELD_TYPE_DATETIME = 4;
    public static final int FIELD_TYPE_FLOAT = 5;
    public static final int FIELD_TYPE_DOUBLE = 6;

    public static final int CONFLICT_MODE_NONE = 0;
    public static final int CONFLICT_MODE_FIRST_VALID = 1;
    public static final int CONFLICT_MODE_LAST_VALID = 2;

    public static final int TEMPORARY = 0;
    public static final int PERMANENT = 1;

    private String mField;
    private int mFieldType;
    private int mFieldConflictMode;
    private int mFieldSaveMode;

    public AlterTableItems(String field, int type, int conflictMode, int fieldSaveMode) {
        mField = field;
        mFieldType = type;
        mFieldConflictMode = conflictMode;
        mFieldSaveMode = fieldSaveMode;
    }

    public AlterTableItems() {
        mField = null;
        mFieldType = 0;
        mFieldConflictMode = 0;
        mFieldSaveMode = TEMPORARY;
    }

    private AlterTableItems(Parcel in) {
        mField = in.readString();
        mFieldType = in.readInt();
        mFieldConflictMode = in.readInt();
        mFieldSaveMode = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mField);
        out.writeInt(mFieldType);
        out.writeInt(mFieldConflictMode);
        out.writeInt(mFieldSaveMode);
    }

    public static final Parcelable.Creator<AlterTableItems> CREATOR = new Parcelable.Creator<AlterTableItems>() {
        public AlterTableItems createFromParcel(Parcel in) {
            return new AlterTableItems(in);
        }

        public AlterTableItems[] newArray(int size) {
            return new AlterTableItems[size];
        }
    };

    public String getField() {
        return mField;
    }

    public void setField(String field) {
        this.mField = field;
    }

    public int getFieldType() {
        return mFieldType;
    }

    public void setFieldType(int type) {
        this.mFieldType = type;
    }

    public int getFieldConflictMode() {
        return mFieldConflictMode;
    }

    public void setFieldConflictMode(int conflictMode) {
        this.mFieldConflictMode = conflictMode;
    }

    public int getFieldSaveMode() {
        return mFieldSaveMode;
    }

    public void setFieldSaveMode(int fieldSaveMode) {
        this.mFieldSaveMode = fieldSaveMode;
    }
}

