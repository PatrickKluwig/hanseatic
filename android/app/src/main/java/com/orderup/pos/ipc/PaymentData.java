package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;


public class PaymentData implements Parcelable {
	
	private float mAmount;
	private int mStatus;
	
	public PaymentData(float amount, int status) {
		mAmount = amount;
		mStatus = status;
	}
	
	private PaymentData(Parcel in) {
		mAmount = in.readFloat();
		mStatus = in.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeFloat(mAmount);		
		out.writeInt(mStatus);	
	}
	
    public static final Parcelable.Creator<PaymentData> CREATOR = new Parcelable.Creator<PaymentData>() {
		public PaymentData createFromParcel(Parcel in) {
		    return new PaymentData(in);
		}

		public PaymentData[] newArray(int size) {
		    return new PaymentData[size];
		}
    };

    /**
     * Zu zahlender Betrag dieser Bezahlung
     * 
     * @return Betrag
     */
    public float getAmount() {
        return mAmount;
    }
        
    /**
     * Prüft ob Bezahlung getätigt werden kann
     * 
     * @return 1 Bezahlung in Ordnung, -1 keine Cashless Bezahlart ausgewählt
     */
    public int getStatus() {
        return mStatus;
    }  
}
