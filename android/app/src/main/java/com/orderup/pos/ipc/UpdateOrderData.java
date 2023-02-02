package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;


public class UpdateOrderData implements Parcelable {

	public final static int RET_OK = 0;
	public final static int RET_BUSY = 1;
	public final static int RET_WRONG_PARAMETER = 2;
	public final static int RET_WRONG_PERMISSION = 3;
	public final static int RET_WRONG_ERROR_GENEREL = 4;

	private long mTableId;
	private long mUserId;
	private ArrayList<UpdateOrderDataItems> mOrderDataItems;
	private Long mInvoicePrinterId;
	private String mInfo;
	private long mRefundId;

	public UpdateOrderData(long tableId, long userId, ArrayList<UpdateOrderDataItems> orderDataItems, Long invoicePrinterId, String info, long refundId) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
		mInvoicePrinterId = invoicePrinterId;
		mInfo = info;
		mRefundId = refundId;
	}

	public UpdateOrderData(long tableId, long userId, ArrayList<UpdateOrderDataItems> orderDataItems, Long invoicePrinterId, String info) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
		mInvoicePrinterId = invoicePrinterId;
		mInfo = info;
		mRefundId = 0;
	}

	public UpdateOrderData(long tableId, long userId, ArrayList<UpdateOrderDataItems> orderDataItems, Long invoicePrinterId, long refundId) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
		mInvoicePrinterId = invoicePrinterId;
		mRefundId = refundId;
	}

	public UpdateOrderData(long tableId, long userId, ArrayList<UpdateOrderDataItems> orderDataItems, Long invoicePrinterId) {
		mTableId = tableId;
		mUserId = userId;
		mOrderDataItems = orderDataItems;
		mInvoicePrinterId = invoicePrinterId;
		mRefundId = 0;
	}

	private UpdateOrderData(Parcel in) {
		mTableId = in.readLong();
		mUserId = in.readLong();
		if (mOrderDataItems == null) {
			mOrderDataItems = new ArrayList<UpdateOrderDataItems>();
		}
		if (in != null) {
			in.readTypedList(mOrderDataItems, UpdateOrderDataItems.CREATOR);
		}
		mInvoicePrinterId = in.readLong();
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
		out.writeString(mInfo);
		out.writeLong(mRefundId);
	}

    public static final Creator<UpdateOrderData> CREATOR = new Creator<UpdateOrderData>() {
		public UpdateOrderData createFromParcel(Parcel in) {
		    return new UpdateOrderData(in);
		}

		public UpdateOrderData[] newArray(int size) {
		    return new UpdateOrderData[size];
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

	public ArrayList<UpdateOrderDataItems> getOrderDataItems() {
		return mOrderDataItems;
	}

	public void setOrderDataItems(ArrayList<UpdateOrderDataItems> mOrderDataItems) {
		this.mOrderDataItems = mOrderDataItems;
	}

	public long getInvoicePrinterId() {
		return mInvoicePrinterId;
	}

	public void setInvoicePrinterId(long invoicePrinterId) {
		this.mInvoicePrinterId = invoicePrinterId;
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
