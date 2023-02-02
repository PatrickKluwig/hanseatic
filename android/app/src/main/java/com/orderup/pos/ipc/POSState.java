package com.orderup.pos.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Martin on 22.05.2017.
 */

public class POSState implements Parcelable {
    public static final int STATE_POS_NOT_RUNNING = 0;
    public static final int STATE_POS_RUNNING = 1;

    public static final int SESSION_POS_NA = 0;
    public static final int SESSION_POS_CLOSE_PAID = 1;
    public static final int SESSION_POS_CLOSE_NOT_PAID = 2;
    public static final int SESSION_POS_OPEN = 3;
    public static final int SESSION_POS_NEW_SESSION = 4;

    private boolean mIsServer;
    private int mState;
    private int mSessionState;


    public POSState(boolean isServer, int state, int sessionState) {
        mIsServer = isServer;
        mState = state;
        mSessionState = sessionState;
    }

    private POSState(Parcel in) {
        mIsServer = in.readByte() != 0;
        mState = in.readInt();
        mSessionState = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeByte((byte) (mIsServer ? 1 : 0));
        out.writeInt(mState);
        out.writeInt(mSessionState);
    }

    public static final Parcelable.Creator<POSState> CREATOR = new Parcelable.Creator<POSState>() {
        public POSState createFromParcel(Parcel in) {
            return new POSState(in);
        }

        public POSState[] newArray(int size) {
            return new POSState[size];
        }
    };

    /**
     * Ist Gerät ein Server oder Client
     *
     * @return Betrag
     */
    public boolean IsServer() {
        return mIsServer;
    }

    /**
     * Status des Gerätes
     *
     * @return TBD
     */
    public int getState() {
        return mState;
    }

    /**
     * Status der Session
     *
     * @return TBD
     */
    public int getSessionState() {
        return mSessionState;
    }
}
