package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ClosePaymentDlgData implements Parcelable {
	private long mMethod1;
	private float mAmount1;
	private int mType1;
	private long mMethod2;
	private float mAmount2;
	private int mType2;
	private float mTip;
	private int mIsRefund;
	private String mOldPaymentData;
	private ArrayList<ShopCartDataItems> mShopCartDataItems;


	public ClosePaymentDlgData() {
		mMethod1 = 0;
		mAmount1 = 0.0f;
		mType1 = 0;
		mMethod2 = 0;
		mAmount2 = 0;
		mType2 = 0;
		mTip = 0.0f;
		mIsRefund = 0;
		mOldPaymentData = null;
		mShopCartDataItems = null;
	}

	public ClosePaymentDlgData(long method1, float amount1, int type1, long method2, float amount2, int type2, float tip, boolean isRefund, String oldPaymentData, ArrayList<ShopCartDataItems> shopCartDataItems) {
		mMethod1 = method1;
		mAmount1 = amount1;
		mType1 = type1;
		mMethod2 = method2;
		mAmount2 = amount2;
		mType2 = type2;
		mTip = tip;
		mIsRefund = (isRefund) ? 1 : 0;
		mOldPaymentData = oldPaymentData;
		mShopCartDataItems = shopCartDataItems;
	}

	public ClosePaymentDlgData(long method1, float amount1, int type1, long method2, float amount2, int type2, float tip, boolean isRefund, String oldPaymentData) {
		mMethod1 = method1;
		mAmount1 = amount1;
		mType1 = type1;
		mMethod2 = method2;
		mAmount2 = amount2;
		mType2 = type2;
		mTip = tip;
		mIsRefund = (isRefund) ? 1 : 0;
		mOldPaymentData = oldPaymentData;
		mShopCartDataItems = null;
	}

	public ClosePaymentDlgData(Parcel in) {
		readFromParcel(in);
	}

	public void readFromParcel(Parcel in) {
		mMethod1 = in.readLong();
		mAmount1 = in.readFloat();
		mType1 = in.readInt();
		mMethod2 = in.readLong();
		mAmount2 = in.readFloat();
		mType2 = in.readInt();
		mTip = in.readFloat();
		mIsRefund = in.readInt();
		mOldPaymentData = in.readString();
		if (mShopCartDataItems == null) {
			mShopCartDataItems = new ArrayList<ShopCartDataItems>();
		}
		if (in != null) {
			in.readTypedList(mShopCartDataItems, ShopCartDataItems.CREATOR);
		}
	 }

	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeLong(mMethod1);
		out.writeFloat(mAmount1);
		out.writeInt(mType1);
		out.writeLong(mMethod2);
		out.writeFloat(mAmount2);
		out.writeInt(mType2);
		out.writeFloat(mTip);
		out.writeInt(mIsRefund);
		out.writeString(mOldPaymentData);
		out.writeTypedList(mShopCartDataItems);
	}

    public static final Creator<ClosePaymentDlgData> CREATOR = new Creator<ClosePaymentDlgData>() {
		public ClosePaymentDlgData createFromParcel(Parcel in) {
		    return new ClosePaymentDlgData(in);
		}

		public ClosePaymentDlgData[] newArray(int size) {
		    return new ClosePaymentDlgData[size];
		}
    };

	public long getMethod1() {
		return mMethod1;
	}

	public void setMethod1(long method1) {
		this.mMethod1 = method1;
	}

	public float getAmount1() {
		return mAmount1;
	}

	public void setAmount1(float amount1) {
		this.mAmount1 = amount1;
	}

	public int getType1() {
		return mType1;
	}

	public void setType1(int type1) {
		this.mType1 = type1;
	}

	public long getMethod2() {
		return mMethod2;
	}

	public void setMethod2(long method2) {
		this.mMethod2 = method2;
	}

	public float getAmount2() {
		return mAmount2;
	}

	public void setAmount2(float amount2) {
		this.mAmount2 = amount2;
	}

	public int getType2() {
		return mType2;
	}

	public void setType2(int type2) {
		this.mType2 = type2;
	}

	public float getTip() {
		return mTip;
	}

	public void setTip(float tip) {
		this.mTip = tip;
	}

	public boolean getIsRefund() {
		return (mIsRefund > 0) ? true : false;
	}

	public void setIsRefund(boolean isRefund) {
		this.mIsRefund = (isRefund) ? 1 : 0;
	}

	public String getOldPaymentData() {
		return mOldPaymentData;
	}

	public void setOldPaymentData(String oldPaymentData) {
		this.mOldPaymentData = oldPaymentData;
	}

	public ArrayList<ShopCartDataItems> getShopCartDataItems() {
		return mShopCartDataItems;
	}

	public void setShopCartDataItems(ArrayList<ShopCartDataItems> orderDataItems) {
		this.mShopCartDataItems = orderDataItems;
	}
}
