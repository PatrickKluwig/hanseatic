package com.orderup.pos.ipc;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Martin on 26.05.2017.
 */

public class OrderDataItems implements Parcelable {

    private long mProductId;
    private String mComment;
    private ContentValues mAdditionalValues;
    private long[] mProductOptions;
    private Float mFreePrice;

    public OrderDataItems(long productId, String comment, ContentValues additionalValue, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mAdditionalValues = additionalValue;
        mProductOptions = null;
        mFreePrice = freePrice;
    }

    public OrderDataItems(long productId, String comment, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mAdditionalValues = null;
        mProductOptions = null;
        mFreePrice = freePrice;
    }

    public OrderDataItems(long productId, String comment, ContentValues additionalCv, long[] productOptions, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mAdditionalValues = null;
        mProductOptions = productOptions;
        mFreePrice = freePrice;
    }

    public OrderDataItems() {
        mProductId = 0;
        mComment = null;
        mAdditionalValues = null;
        mProductOptions = null;
        mFreePrice = null;
    }

    private OrderDataItems(Parcel in) {
        mProductId = in.readLong();
        mComment = in.readString();
        if (in != null) {
            //mAdditionalValues = (HashMap) in.readSerializable();
            mAdditionalValues = in.readParcelable(null);
        }
        mProductOptions = in.createLongArray();
        float p = in.readFloat();
        if (Float.isNaN(p)) {
            mFreePrice = null;
        } else {
            mFreePrice = p;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(mProductId);
        out.writeString(mComment);
        //out.writeSerializable(mAdditionalValues);
        out.writeParcelable(mAdditionalValues, flags);
        //out.writeMap(mAdditionalValues);
        //out.writeParcelable(mAdditionalValues, flags);
        out.writeLongArray(mProductOptions);
        if (mFreePrice == null) {
            out.writeFloat(Float.NaN);
        } else {
            out.writeFloat(mFreePrice);
        }
    }

    public static final Creator<OrderDataItems> CREATOR = new Creator<OrderDataItems>() {
        public OrderDataItems createFromParcel(Parcel in) {
            return new OrderDataItems(in);
        }

        public OrderDataItems[] newArray(int size) {
            return new OrderDataItems[size];
        }
    };

    public long getProductId() {
        return mProductId;
    }

    public void setProductId(long productId) {
        this.mProductId = productId;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        this.mComment = comment;
    }

    public ContentValues getAdditionalValues() {
        return mAdditionalValues;
    }

    public void setAdditionalValues(ContentValues additionalValues) {
        this.mAdditionalValues = additionalValues;
    }

    public long[] getProductOptions() {
        return mProductOptions;
    }

    public void setAProductOptions(long[] productOptions) {
        this.mProductOptions = productOptions;
    }

    public Float getFreePrice() {
        return mFreePrice;
    }

    public void setFreePrice(Float freePrice) {
        this.mFreePrice = freePrice;
    }
}

