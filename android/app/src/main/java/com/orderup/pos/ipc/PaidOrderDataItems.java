package com.orderup.pos.ipc;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Martin on 26.05.2017.
 */

public class PaidOrderDataItems implements Parcelable {

    private long mProductId;
    private String mComment;
    private ContentValues mAdditionalValues;
    private long mPaymentMethodId;
    private long mDiscountId;
    private long[] mProductOptions;
    private Float mFreePrice;

    public PaidOrderDataItems(long productId, String comment, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mPaymentMethodId = 0;
        mDiscountId = 0;
        mAdditionalValues = null;
        mProductOptions = null;
        mFreePrice = freePrice;
    }

    public PaidOrderDataItems(long productId, String comment, ContentValues additionalValue, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mPaymentMethodId = 0;
        mDiscountId = 0;
        mAdditionalValues = additionalValue;
        mProductOptions = null;
        mFreePrice = freePrice;
    }

    public PaidOrderDataItems(long productId, String comment, long paymentMethodId, long discountId, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mPaymentMethodId = paymentMethodId;
        mDiscountId = discountId;
        mAdditionalValues = null;
        mProductOptions = null;
        mFreePrice = freePrice;
    }

    public PaidOrderDataItems(long productId, String comment, long paymentMethodId, long discountId, ContentValues additionalValue, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mPaymentMethodId = paymentMethodId;
        mDiscountId = discountId;
        mAdditionalValues = additionalValue;
        mProductOptions = null;
        mFreePrice = freePrice;
    }

    public PaidOrderDataItems(long productId, String comment, long paymentMethodId, long discountId, ContentValues additionalValue, long[] productOptions, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mPaymentMethodId = paymentMethodId;
        mDiscountId = discountId;
        mAdditionalValues = additionalValue;
        mProductOptions = productOptions;
        mFreePrice = freePrice;
    }

    public PaidOrderDataItems() {
        mProductId = 0;
        mComment = null;
        mPaymentMethodId = 0;
        mDiscountId = 0;
        mAdditionalValues = null;
        mProductOptions = null;
        mFreePrice = null;
    }

    private PaidOrderDataItems(Parcel in) {
        mProductId = in.readLong();
        mComment = in.readString();
        mPaymentMethodId = in.readLong();
        mDiscountId = in.readLong();
        if (in != null) {
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
        out.writeLong(mPaymentMethodId);
        out.writeLong(mDiscountId);
        out.writeParcelable(mAdditionalValues, flags);
        out.writeLongArray(mProductOptions);
        if (mFreePrice == null) {
            out.writeFloat(Float.NaN);
        } else {
            out.writeFloat(mFreePrice);
        }
    }

    public static final Creator<PaidOrderDataItems> CREATOR = new Creator<PaidOrderDataItems>() {
        public PaidOrderDataItems createFromParcel(Parcel in) {
            return new PaidOrderDataItems(in);
        }

        public PaidOrderDataItems[] newArray(int size) {
            return new PaidOrderDataItems[size];
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

    public long getPaymentMethodId() {
        return mPaymentMethodId;
    }

    public void setPaymentMethodId(long paymentMethodId) {
        this.mPaymentMethodId = paymentMethodId;
    }

    public long getDiscountId() {
        return mDiscountId;
    }

    public void setDiscountId(long discountId) {
        this.mDiscountId = discountId;
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

