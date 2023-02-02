package com.orderup.pos.ipc;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.HashMap;

public class POSCursor implements Parcelable {

    /**
     * TAG.
     */
    private static final String TAG = POSCursor.class.getSimpleName();

    /**
     * How many columns we have
     */
    public int numColumns = 0;

    /**
     * Current row
     */
    private int curRow = -1;

    /**
     * Cursor Window.
     */
    private CursorWindow window;

    /**
     * passed projection
     */
    public Bundle mBundle = new Bundle();

    /**
     * String constant for bundle kay.
     */
    private static final String BUNDLE_KEY = "HashMap";

    /**
     * Hashmap for columns and index.
     */
    private HashMap<String, Integer> mProjectionMap = new HashMap<String, Integer>();

    /**
     * Constructor
     */
    public POSCursor() {
    }

    /**
     * Constructor
     *
     * @param cursorWindow
     */
    public POSCursor(CursorWindow cursorWindow) {
        window = cursorWindow;
    }

    /**
     * Constructor
     *
     * @param in
     */
    public POSCursor(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(window, 0);
        dest.writeInt(numColumns);
        dest.writeInt(curRow);
        dest.writeBundle(mBundle);
    }

    /**
     * Read Parcel.
     *
     * @param in
     */
    public void readFromParcel(Parcel in) {
        window = in.readParcelable(CursorWindow.class.getClassLoader());
        numColumns = in.readInt();
        curRow = in.readInt();
        mBundle = in.readBundle();
        if (mBundle != null) {
            mProjectionMap = (HashMap<String, Integer>) mBundle
                    .getSerializable(BUNDLE_KEY);
        }
    }

    /**
     * CREATOR for Parcelable
     */
    public static final Parcelable.Creator<POSCursor> CREATOR = new Parcelable.Creator<POSCursor>() {
        public POSCursor createFromParcel(Parcel in) {
            return new POSCursor(in);
        }

        public POSCursor[] newArray(int size) {
            return new POSCursor[size];
        }
    };


    /**
     * Returns String value in the column.
     *
     * @param columnName
     * @return
     */
    public String getString(String columnName) {
        if (mProjectionMap != null) {
            if (mProjectionMap.containsKey(columnName)) {
                int columnIndex = mProjectionMap.get(columnName);
                return window.getString(curRow, columnIndex);
            } else {
                Log.e(TAG, "Didn't match the column name.");
                return null;
            }
        } else {
            Log.e(TAG, "Projecion map is null.");
            return null;
        }
    }

    /**
     * Returns String value in the column.
     *
     * @param columnIndex
     * @return
     */
    public String getString(int columnIndex) {
        return window.getString(curRow, columnIndex);
    }

    /**
     * Moves to the first row of cursorWindow.
     *
     * @return
     */
    public boolean moveToFirst() {
        if (window.getNumRows() == 0) {
            return false;
        }
        curRow = 0;
        return true;
    }

    /**
     * Moves to the last row of cursor window.
     *
     * @return
     */
    public boolean moveToLast() {
        if (window.getNumRows() == 0) {
            return false;
        }
        curRow = window.getNumRows() - 1;
        return true;
    }

    /**
     * Checks if its not last row.
     *
     * @return
     */
    public boolean isAfterLast() {
        return (curRow == window.getNumRows());
    }

    /**
     * Moves to the next row of the cursor window.
     *
     * @return
     */
    public boolean moveToNext() {
        curRow++;
        if (isAfterLast()) {
            curRow = window.getNumRows();
            return false;
        }
        return true;
    }

    /**
     * Returns the long value.
     *
     * @param columnName
     * @return
     */
    public long getLong(String columnName) {
        if (mProjectionMap != null) {
            if (mProjectionMap.containsKey(columnName)) {
                int columnIndex = mProjectionMap.get(columnName);
                return window.getLong(curRow, columnIndex);
            } else {
                Log.e(TAG, "Didn't match the column name.");
                return 0;
            }
        } else {
            Log.e(TAG, "Projecion map is null.");
            return 0;
        }
    }

    /**
     * Returns the long value.
     *
     * @param columnIndex
     * @return
     */
    public long getLong(int columnIndex) {
        return window.getLong(curRow, columnIndex);
    }

    /**
     * Returns count of rows.
     *
     * @return
     */
    public int getCount() {
        return window.getNumRows();
    }

    /**
     * Returns the int value.
     *
     * @param columnName
     * @return
     */
    public int getInt(String columnName) {
        if (mProjectionMap != null) {
            if (mProjectionMap.containsKey(columnName)) {
                int columnIndex = mProjectionMap.get(columnName);
                return window.getInt(curRow, columnIndex);
            } else {
                Log.e(TAG, "Didn't match the column name.");
                return 0;
            }
        } else {
            Log.e(TAG, "Projecion map is null.");
            return 0;
        }
    }

    /**
     * Returns the int value.
     *
     * @param columnIndex
     * @return
     */
    public int getInt(int columnIndex) {
        return window.getInt(curRow, columnIndex);
    }

    /**
     * Close the window cursor.
     */
    public void close() {
        window.close();
    }

    /**
     * Returns the Blob type.
     *
     * @param columnName
     * @return
     */
    public byte[] getBlob(String columnName) {
        if (mProjectionMap != null) {
            if (mProjectionMap.containsKey(columnName)) {
                int columnIndex = mProjectionMap.get(columnName);
                return window.getBlob(curRow, columnIndex);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Returns the Blob type.
     *
     * @param columnIndex
     * @return
     */
    public byte[] getBlob(int columnIndex) {
        return window.getBlob(curRow, columnIndex);
    }

    /**
     * This method fill the projection elements in a list.
     *
     * @param projection
     */
    public void fillColumns(String[] projection) {
        int length = projection.length;
        HashMap projectionMap = new HashMap();
        for (int i = 0; i < length; i++) {
            projectionMap.put(projection[i], i);
        }
        mProjectionMap = projectionMap;
        mBundle.putSerializable(BUNDLE_KEY, projectionMap);

    }

    public HashMap<String, Integer> getProjection() {
        return mProjectionMap;
    }
}