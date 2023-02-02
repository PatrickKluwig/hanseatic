package com.orderup.pos.ipc;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Martin on 26.05.2017.
 */

public class UpdateOrderDataItems implements Parcelable {

    private long mOrderItemId;
    private long mPaymentMethodId;
    private long mDiscountId;

    public UpdateOrderDataItems(long orderItemId) {
        mOrderItemId = orderItemId;
        mPaymentMethodId = 0;
        mDiscountId = 0;
    }

    public UpdateOrderDataItems(long orderItemId, long paymentMethodId, long discountId) {
        mOrderItemId = orderItemId;
        mPaymentMethodId = paymentMethodId;
        mDiscountId = discountId;
    }

    public UpdateOrderDataItems() {
        mPaymentMethodId = 0;
        mDiscountId = 0;
    }

    private UpdateOrderDataItems(Parcel in) {
        mOrderItemId = in.readLong();
        mPaymentMethodId = in.readLong();
        mDiscountId = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(mOrderItemId);
        out.writeLong(mPaymentMethodId);
        out.writeLong(mDiscountId);
    }

    public static final Creator<UpdateOrderDataItems> CREATOR = new Creator<UpdateOrderDataItems>() {
        public UpdateOrderDataItems createFromParcel(Parcel in) {
            return new UpdateOrderDataItems(in);
        }

        public UpdateOrderDataItems[] newArray(int size) {
            return new UpdateOrderDataItems[size];
        }
    };

    public long getOrderItemId() {
        return mOrderItemId;
    }

    public void setOrderItemId(long productId) {
        this.mOrderItemId = productId;
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
}

