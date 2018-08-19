package com.recom.www.recommenderapp;

import android.os.Parcel;
import android.os.Parcelable;

public class MyParcelable implements Parcelable {

    private int mData;
    private String mName;



    public static final Creator<MyParcelable> CREATOR = new Creator<MyParcelable>() {
        @Override
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };
    private MyParcelable(Parcel in) {
        mData = in.readInt();
        mName = in.readString();
    }

    public MyParcelable() {
        // Normal actions performed by class, since this is still a normal object!
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
        out.writeString(mName);
        //out.writeParcelable(mInfo, flags);
    }
}
