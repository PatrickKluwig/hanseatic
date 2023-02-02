package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class PaidOrderData implements Parcelable {

	public final static int RET_OK = 0;
	public final static int RET_BUSY = 1;
	public final static int RET_WRONG_PARAMETER = 2;
	public final static int RET_WRONG_PERMISSION = 3;
	public final static int RET_WRONG_ERROR_GENEREL = 4;

	private long mTableId;
	private long mUserId;
	private ArrayList<PaidOrderDataItems> mOrderDataItems;
	private Long mInvoicePrinterId;
	private float mTip;
	private float mChangeMoney;
	private String mInfo;
	private long mRefundId;

	public PaidOrderData(long tableId, long userId, ArrayList<PaidOrderDataItems> orderDataItems, Long invoicePrinterId, float tip, float changeMoney, String info, long refundId) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
		mInvoicePrinterId = invoicePrinterId;
		mTip = tip;
		mChangeMoney = changeMoney;
		mInfo = info;
		mRefundId = refundId;
	}

	public PaidOrderData(long tableId, long userId, ArrayList<PaidOrderDataItems> orderDataItems, Long invoicePrinterId, float tip, float changeMoney, String info) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
		mInvoicePrinterId = invoicePrinterId;
		mTip = tip;
		mChangeMoney = changeMoney;
		mInfo = info;
		mRefundId = 0;
	}

	public PaidOrderData(long tableId, long userId, ArrayList<PaidOrderDataItems> orderDataItems, Long invoicePrinterId, float tip, float changeMoney, long refundId) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
		mInvoicePrinterId = invoicePrinterId;
		mTip = tip;
		mChangeMoney = changeMoney;
		mInfo = null;
		mRefundId = refundId;
	}

	public PaidOrderData(long tableId, long userId, ArrayList<PaidOrderDataItems> orderDataItems, Long invoicePrinterId, float tip, float changeMoney) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
		mInvoicePrinterId = invoicePrinterId;
		mTip = tip;
		mChangeMoney = changeMoney;
		mInfo = null;
		mRefundId = 0;
	}

	private PaidOrderData(Parcel in) {
		mTableId = in.readLong();
		mUserId = in.readLong();
		if (mOrderDataItems == null) {
			mOrderDataItems = new ArrayList<PaidOrderDataItems>();
		}
		if (in != null) {
			in.readTypedList(mOrderDataItems, PaidOrderDataItems.CREATOR);
		}
		mInvoicePrinterId = in.readLong();
		mTip = in.readFloat();
		mChangeMoney = in.readFloat();
		mInfo = in.readString();
		mRefundId = in.readLong();
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
		out.writeLong(mInvoicePrinterId);
		out.writeFloat(mTip);
		out.writeFloat(mChangeMoney);
		out.writeString(mInfo);
		out.writeLong(mRefundId);
	}

    public static final Creator<PaidOrderData> CREATOR = new Creator<PaidOrderData>() {
		public PaidOrderData createFromParcel(Parcel in) {
		    return new PaidOrderData(in);
		}

		public PaidOrderData[] newArray(int size) {
		    return new PaidOrderData[size];
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

	public ArrayList<PaidOrderDataItems> getOrderDataItems() {
		return mOrderDataItems;
	}

	public void setOrderDataItems(ArrayList<PaidOrderDataItems> mOrderDataItems) {
		this.mOrderDataItems = mOrderDataItems;
	}

	public long getInvoicePrinterId() {
		return mInvoicePrinterId;
	}

	public void setInvoicePrinterId(long invoicePrinterId) {
		this.mInvoicePrinterId = invoicePrinterId;
	}

	public float getTip() {
		return mTip;
	}

	public void setTip(float tip) {
		this.mTip = tip;
	}

	public float getChangeMoney() {
		return mChangeMoney;
	}

	public void setChangeMoney(float changeMoney) {
		this.mChangeMoney = changeMoney;
	}

	public String getInfo() {
		return mInfo;
	}

	public void setInfo(String info) {
		this.mInfo = info;
	}

	public boolean isRefund() {
		return (mRefundId > 0) ? true : false;
	}

	public long getRefundId() {
		return mRefundId;
	}

	public void setRefundId(long refundId) {
		this.mRefundId = refundId;
	}
}
