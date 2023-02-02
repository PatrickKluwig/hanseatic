package com.orderup.pos.ipc;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class PaymentItems implements Parcelable {

	private ArrayList<PaymentItem> mOrderItemsId;
	
	public PaymentItems() {
		mOrderItemsId = new ArrayList<PaymentItem>();
	}
	
	public PaymentItems(ArrayList<PaymentItem> orderItemsId) {
		mOrderItemsId = orderItemsId;
	}
	
	private PaymentItems(Parcel in) {
		if (mOrderItemsId == null) {
			mOrderItemsId = new ArrayList<PaymentItem>();
		}
		if (in != null) {
			in.readTypedList(mOrderItemsId, PaymentItem.CREATOR);
		}
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeTypedList(mOrderItemsId);
	}

    public static final Parcelable.Creator<PaymentItems> CREATOR = new Parcelable.Creator<PaymentItems>() {
		public PaymentItems createFromParcel(Parcel in) {
		    return new PaymentItems(in);
		}

		public PaymentItems[] newArray(int size) {
		    return new PaymentItems[size];
		}
    };
    
    /**
     * Get the IDs of the table ORDERITEMS
     * 
     * @return ORDERITEMS_COL_ID
     */
	public ArrayList<PaymentItem> getOrderItemsId() {
		return mOrderItemsId;
	}		
	
	public void addItem(PaymentItem item) {
		mOrderItemsId.add(item);
	}

	public int size() {
		if (mOrderItemsId != null) {
			return mOrderItemsId.size();
		}
		return 0;
	}
}
