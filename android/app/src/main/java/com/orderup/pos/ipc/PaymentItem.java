package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PaymentItem implements Parcelable {

	public final static int NOT_PAID = 0;
	public final static int PAID = 1;
	public final static int DELETED = 2;

	private long mOrderItemId;
	private int mPaidStatus;

	public PaymentItem() {
		mOrderItemId = 0;
		mPaidStatus = NOT_PAID;
	}

	public PaymentItem(long orderItemId, int status) {
		mOrderItemId = orderItemId;
		mPaidStatus = status;
	}

	private PaymentItem(Parcel in) {
		mOrderItemId = in.readLong();
		mPaidStatus = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeLong(mOrderItemId);
		out.writeInt(mPaidStatus);
	}

    public static final Creator<PaymentItem> CREATOR = new Creator<PaymentItem>() {
		public PaymentItem createFromParcel(Parcel in) {
		    return new PaymentItem(in);
		}

		public PaymentItem[] newArray(int size) {
		    return new PaymentItem[size];
		}
    };
    

	public long getOrderItemId() {
		return mOrderItemId;
	}

	public void setOrderItemId(long orderItemId) {
		mOrderItemId = orderItemId;
	}
	
	public int getPaidStatus() {
		return mPaidStatus;
	}

	public void setPaidStatus(int paidStatus) {
		this.mPaidStatus = paidStatus;
	}
}
