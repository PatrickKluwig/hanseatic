package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Martin on 03.09.2020.
 */

public class ShopCartDataItems implements Parcelable {

    private long mProductId;
    private String mComment;
    private long mDiscountId;
    private long[] mProductOptions;
    private Float mFreePrice;
    private int mTaxSelection;

    public ShopCartDataItems(long productId, String comment, long[] productOptions, long discountId, Float freePrice, int taxSelection) {
        mProductId = productId;
        mComment = comment;
        mDiscountId = discountId;
        mProductOptions = productOptions;
        mFreePrice = freePrice;
        mTaxSelection = taxSelection;
    }

    public ShopCartDataItems(long productId, String comment, long[] productOptions, long discountId, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mDiscountId = discountId;
        mProductOptions = productOptions;
        mFreePrice = freePrice;
        mTaxSelection = 0;
    }

    public ShopCartDataItems(long productId, String comment, long discountId, Float freePrice, int taxSelection) {
        mProductId = productId;
        mComment = comment;
        mDiscountId = discountId;
        mProductOptions = null;
        mFreePrice = freePrice;
        mTaxSelection = taxSelection;
    }

    public ShopCartDataItems(long productId, String comment, long discountId, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mDiscountId = discountId;
        mProductOptions = null;
        mFreePrice = freePrice;
        mTaxSelection = 0;
    }

    public ShopCartDataItems(long productId, String comment, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mDiscountId = 0;
        mProductOptions = null;
        mFreePrice = freePrice;
        mTaxSelection = 0;
    }

    public ShopCartDataItems(long productId, String comment, long[] productOptions, Float freePrice) {
        mProductId = productId;
        mComment = comment;
        mDiscountId = 0;
        mProductOptions = productOptions;
        mFreePrice = freePrice;
        mTaxSelection = 0;
    }

    public ShopCartDataItems() {
        mProductId = 0;
        mComment = null;
        mDiscountId = 0;
        mProductOptions = null;
        mFreePrice = null;
        mTaxSelection = 0;
    }

    private ShopCartDataItems(Parcel in) {
        mProductId = in.readLong();
        mComment = in.readString();
        mDiscountId = in.readLong();
        mProductOptions = in.createLongArray();
        float p = in.readFloat();
        if (Float.isNaN(p)) {
            mFreePrice = null;
        } else {
            mFreePrice = p;
        }
        mTaxSelection = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(mProductId);
        out.writeString(mComment);
        out.writeLong(mDiscountId);
        out.writeLongArray(mProductOptions);
        if (mFreePrice == null) {
            out.writeFloat(Float.NaN);
        } else {
            out.writeFloat(mFreePrice);
        }
        out.writeInt(mTaxSelection);
    }

    public static final Creator<ShopCartDataItems> CREATOR = new Creator<ShopCartDataItems>() {
        public ShopCartDataItems createFromParcel(Parcel in) {
            return new ShopCartDataItems(in);
        }

        public ShopCartDataItems[] newArray(int size) {
            return new ShopCartDataItems[size];
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

    public long getDiscountId() {
        return mDiscountId;
    }

    public void setDiscountId(long discountId) {
        this.mDiscountId = discountId;
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

    public int getTaxSelection() {
        return mTaxSelection;
    }

    public void setTaxSelection(int taxSelection) {
        this.mTaxSelection = taxSelection;
    }
}

