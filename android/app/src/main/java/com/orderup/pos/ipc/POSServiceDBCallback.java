package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

public class POSServiceDBCallback implements Parcelable {

    private IPOSServiceDBCallback mServiceDBCallback;

    public POSServiceDBCallback(String field, int type, IPOSServiceDBCallback serviceDBCallback) {
        mServiceDBCallback = serviceDBCallback;
    }

    public POSServiceDBCallback() {
        mServiceDBCallback = null;
    }

    private POSServiceDBCallback(Parcel in) {
        //mServiceDBCallback = in.readValue(IPOSServiceDBCallback.class);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        //out.writeString(mField);
    }

    public static final Parcelable.Creator<POSServiceDBCallback> CREATOR = new Parcelable.Creator<POSServiceDBCallback>() {
        public POSServiceDBCallback createFromParcel(Parcel in) {
            return new POSServiceDBCallback(in);
        }

        public POSServiceDBCallback[] newArray(int size) {
            return new POSServiceDBCallback[size];
        }
    };
}
