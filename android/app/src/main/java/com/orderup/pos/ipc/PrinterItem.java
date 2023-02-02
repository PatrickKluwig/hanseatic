package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

public class PrinterItem implements Parcelable {

	public static final int TYPE_BON = 1;
	public static final int TYPE_RECHNUNG = 2;
	public static final int TYPE_KASSENSTAND = 3;
	public static final int TYPE_RKSV_START_BELEG = 4;
	public static final int TYPE_RKSV_SAMMEL_BELEG = 5;
	public static final int TYPE_RKSV_NULL_BELEG = 6;

	private String mFileName;
	private int mType;
	private long mToken;

	public PrinterItem() {
		mFileName = null;
		mType = 0;
		mToken = 0;
	}

	public PrinterItem(String fileName, int type, long token) {
		mFileName = fileName;
		mType = type;
		mToken = token;
	}

	private PrinterItem(Parcel in) {
		mFileName = in.readString();
		mType = in.readInt();
		mToken = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(mFileName);
		out.writeInt(mType);
		out.writeLong(mToken);
	}

    public static final Creator<PrinterItem> CREATOR = new Creator<PrinterItem>() {
		public PrinterItem createFromParcel(Parcel in) {
		    return new PrinterItem(in);
		}

		public PrinterItem[] newArray(int size) {
		    return new PrinterItem[size];
		}
    };
    

	public String getFilename() {
		return mFileName;
	}

	public void setFilename(String fileName) {
		mFileName = fileName;
	}
	
	public int getType() {
		return mType;
	}

	public void setType(int type) {
		this.mType = type;
	}

	public long getToken() {
		return mToken;
	}
}
