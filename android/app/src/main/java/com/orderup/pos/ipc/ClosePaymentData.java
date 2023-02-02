package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ClosePaymentData implements Parcelable {
	
	private long mPrinterId;
	private long mCustomerId;
	private ArrayList<PrinterInvoiceItem> mPrinterInvoiceItems;
	private String mPaymentProviderData;
	private float mTip;
	private ArrayList<ShopCartDataItems> mAdditionalShopCartDataItems;
	
	public ClosePaymentData() {		
		mPrinterId = 0;
		mCustomerId = 0;
		mPaymentProviderData = null;
		mTip = 0.0f;
		mAdditionalShopCartDataItems = null;
	}
	
	public ClosePaymentData(long printerId, long customerId) {		
		mPrinterId = printerId;
		mCustomerId = customerId;
		mPaymentProviderData = null;
		mTip = 0.0f;
		mAdditionalShopCartDataItems = null;
	}

	public ClosePaymentData(long printerId, long customerId, String paymentProviderData) {
		mPrinterId = printerId;
		mCustomerId = customerId;
		mPaymentProviderData = paymentProviderData;
		mTip = 0.0f;
		mAdditionalShopCartDataItems = null;
	}

	public ClosePaymentData(ArrayList<PrinterInvoiceItem> printerInvoiceItems) {
		mPrinterId = 0;
		mCustomerId = 0;
		mPrinterInvoiceItems = printerInvoiceItems;
		mPaymentProviderData = null;
		mTip = 0.0f;
		mAdditionalShopCartDataItems = null;
	}

	public ClosePaymentData(String paymentProviderData) {
		mPrinterId = 0;
		mCustomerId = 0;
		mPrinterInvoiceItems = null;
		mPaymentProviderData = paymentProviderData;
		mTip = 0.0f;
		mAdditionalShopCartDataItems = null;
	}

	public ClosePaymentData(String paymentProviderData, float tip) {
		mPrinterId = 0;
		mCustomerId = 0;
		mPrinterInvoiceItems = null;
		mPaymentProviderData = paymentProviderData;
		mTip = tip;
		mAdditionalShopCartDataItems = null;
	}

	public ClosePaymentData(String paymentProviderData, float tip, ArrayList<ShopCartDataItems> additionalShopCartDataItems) {
		mPrinterId = 0;
		mCustomerId = 0;
		mPrinterInvoiceItems = null;
		mPaymentProviderData = paymentProviderData;
		mTip = tip;
		mAdditionalShopCartDataItems = additionalShopCartDataItems;
	}

	public ClosePaymentData(long printerId, String paymentProviderData, float tip, ArrayList<ShopCartDataItems> additionalShopCartDataItems) {
		mPrinterId = printerId;
		mCustomerId = 0;
		mPrinterInvoiceItems = null;
		mPaymentProviderData = paymentProviderData;
		mTip = tip;
		mAdditionalShopCartDataItems = additionalShopCartDataItems;
	}

	public ClosePaymentData(ArrayList<PrinterInvoiceItem> printerInvoiceItems, String paymentProviderData) {
		mPrinterId = 0;
		mCustomerId = 0;
		mPrinterInvoiceItems = printerInvoiceItems;
		mPaymentProviderData = paymentProviderData;
		mTip = 0.0f;
		mAdditionalShopCartDataItems = null;
	}

	public ClosePaymentData(long printerId, long customerId, ArrayList<PrinterInvoiceItem> printerInvoiceItems) {
		mPrinterId = printerId;
		mCustomerId = customerId;
		mPrinterInvoiceItems = printerInvoiceItems;
		mPaymentProviderData = null;
		mTip = 0.0f;
		mAdditionalShopCartDataItems = null;
	}

	public ClosePaymentData(long printerId, long customerId, ArrayList<PrinterInvoiceItem> printerInvoiceItems, String paymentProviderData) {
		mPrinterId = printerId;
		mCustomerId = customerId;
		mPrinterInvoiceItems = printerInvoiceItems;
		mPaymentProviderData = paymentProviderData;
		mTip = 0.0f;
		mAdditionalShopCartDataItems = null;
	}

	public ClosePaymentData(long printerId, long customerId, ArrayList<PrinterInvoiceItem> printerInvoiceItems, String paymentProviderData, float tip) {
		mPrinterId = printerId;
		mCustomerId = customerId;
		mPrinterInvoiceItems = printerInvoiceItems;
		mPaymentProviderData = paymentProviderData;
		mTip = tip;
		mAdditionalShopCartDataItems = null;
	}
	
	public ClosePaymentData(Parcel in) {	
		readFromParcel(in);
	}
	
	public void readFromParcel(Parcel in) {
		mPrinterId = in.readLong();
		mCustomerId = in.readLong();
		if (mPrinterInvoiceItems == null) {
			mPrinterInvoiceItems = new ArrayList<PrinterInvoiceItem>();
		}
		if (in != null) {
			in.readTypedList(mPrinterInvoiceItems, PrinterInvoiceItem.CREATOR);
		}
		mPaymentProviderData = in.readString();
		mTip = in.readFloat();
		if (mAdditionalShopCartDataItems == null) {
			mAdditionalShopCartDataItems = new ArrayList<ShopCartDataItems>();
		}
		if (in != null) {
			in.readTypedList(mAdditionalShopCartDataItems, ShopCartDataItems.CREATOR);
		}
	 }
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeLong(mPrinterId);
		out.writeLong(mCustomerId);
		out.writeTypedList(mPrinterInvoiceItems);
		out.writeString(mPaymentProviderData);
		out.writeFloat(mTip);
		out.writeTypedList(mAdditionalShopCartDataItems);
	}
	
    public static final Parcelable.Creator<ClosePaymentData> CREATOR = new Parcelable.Creator<ClosePaymentData>() {
		public ClosePaymentData createFromParcel(Parcel in) {
		    return new ClosePaymentData(in);
		}

		public ClosePaymentData[] newArray(int size) {
		    return new ClosePaymentData[size];
		}
    };
    
    /**
     * Printer Id on wich to print bill or receipt
     * 
     * @return ID
     */
    public long getPrinterId() {
        return mPrinterId;
    }
    
    /**
     * Customer Id on wich to assign bill or receipt
     * 
     * @return ID
     */
    public long getCustomerId() {
        return mCustomerId;
    }

	public ArrayList<PrinterInvoiceItem> getPrinterInvoiceItems() {
		return mPrinterInvoiceItems;
	}

	public void setPrinterInvoiceItems(ArrayList<PrinterInvoiceItem> printerInvoiceItems) {
		this.mPrinterInvoiceItems = printerInvoiceItems;
	}


	public String getPaymentProviderData() {
		return mPaymentProviderData;
	}

	public void setTip(float tip) {
    	mTip = tip;
	}

	public float getTip() {
    	return mTip;
	}

	public ArrayList<ShopCartDataItems> getAdditionalShopCartDataItems() {
		return mAdditionalShopCartDataItems;
	}

	public void setAdditionalShopCartDataItems(ArrayList<ShopCartDataItems> additionalOrderDataItems) {
		this.mAdditionalShopCartDataItems = additionalOrderDataItems;
	}
}
