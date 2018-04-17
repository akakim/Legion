package com.akakim.legion.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by RyoRyeong Kim on 2018-04-17.
 */

data class CheckList(var viewType :Int,var content : String,var  score : Int ) : Parcelable{


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt()) {
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(viewType)
        parcel.writeString(content)
        parcel.writeInt(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    private companion object CREATOR : Parcelable.Creator<CheckList> {
        const val VALUE_YES = 0
        const val VALUE_NO  = -1

        override fun createFromParcel(parcel: Parcel): CheckList {
            return CheckList(parcel)
        }

        override fun newArray(size: Int): Array<CheckList?> {
            return arrayOfNulls(size)
        }
    }
}