package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PrinterInvoiceItem implements Parcelable, Serializable {

    private static final long serialVersionUID = 15646468486l;
    public static final int TYPE_NORMAL_TEXT = 1;
    public static final int TYPE_NORMAL_TEXT_CENTER = 2;
    public static final int TYPE_BOLD_TEXT = 3;
    public static final int TYPE_BOLD_TEXT_CENTER  = 4;
    public static final int TYPE_QR_CODE = 5;
    public static final int TYPE_QR_CODE_CENTER = 6;

    private String mData;
    private int mType;

    public PrinterInvoiceItem(int type, String data) {
        mData = data;
        mType = type;
    }

    private PrinterInvoiceItem(Parcel in) {
        mData = in.readString();
        mType = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(mData);
        out.writeInt(mType);
    }

    public static final Creator<PrinterInvoiceItem> CREATOR = new Creator<PrinterInvoiceItem>() {
        public PrinterInvoiceItem createFromParcel(Parcel in) {
            return new PrinterInvoiceItem(in);
        }

        public PrinterInvoiceItem[] newArray(int size) {
            return new PrinterInvoiceItem[size];
        }
    };

    public String getData() {
        return mData;
    }

    public void setData(String data) {
        this.mData = data;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        this.mType = type;
    }
}
