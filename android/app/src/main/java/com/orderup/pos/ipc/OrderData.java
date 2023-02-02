package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class OrderData implements Parcelable {

	public final static int RET_OK = 0;
	public final static int RET_BUSY = 1;
	public final static int RET_WRONG_PARAMETER = 2;
	public final static int RET_WRONG_PERMISSION = 3;

	private long mTableId;
	private long mUserId;
	private ArrayList<OrderDataItems> mOrderDataItems;
	private String mInfo;

	public OrderData(long tableId, long userId, ArrayList<OrderDataItems> orderDataItems, String info) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
		mInfo = info;
	}

	public OrderData(long tableId, long userId, ArrayList<OrderDataItems> orderDataItems) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
	}

	private OrderData(Parcel in) {
		mTableId = in.readLong();
		mUserId = in.readLong();
		if (mOrderDataItems == null) {
			mOrderDataItems = new ArrayList<OrderDataItems>();
		}
		if (in != null) {
			in.readTypedList(mOrderDataItems, OrderDataItems.CREATOR);
		}
		mInfo = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeLong(mTableId);
		out.writeLong(mUserId);
		out.writeTypedList(mOrderDataItems);
		out.writeString(mInfo);
	}

    public static final Creator<OrderData> CREATOR = new Creator<OrderData>() {
		public OrderData createFromParcel(Parcel in) {
		    return new OrderData(in);
		}

		public OrderData[] newArray(int size) {
		    return new OrderData[size];
		}
    };

	public long getTableId() {
		return mTableId;
	}

	public void setTableId(long tableId) {
		this.mTableId = tableId;
	}

	public long getUserId() {
		return mUserId;
	}

	public void setUserId(long userId) {
		this.mUserId = userId;
	}

	public ArrayList<OrderDataItems> getOrderDataItems() {
		return mOrderDataItems;
	}

	public void setOrderDataItems(ArrayList<OrderDataItems> mOrderDataItems) {
		this.mOrderDataItems = mOrderDataItems;
	}

	public String getInfo() {
		return mInfo;
	}

	public void setInfo(String info) {
		this.mInfo = info;
	}
}
