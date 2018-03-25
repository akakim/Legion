package com.akakim.utillibrary.data

import android.os.Parcel
import android.os.Parcelable

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-15
 */


data class RecordingItem(
        val name: String,
        val filePath : String,
        val id : Int,
        val length : Int,
        val time : Long) : Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readLong()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString( name )
        dest?.writeString( filePath )
        dest?.writeInt( id )
        dest?.writeInt( length )
        dest?.writeLong( time )
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecordingItem> {
        override fun createFromParcel(parcel: Parcel): RecordingItem {
            return RecordingItem(parcel)
        }

        override fun newArray(size: Int): Array<RecordingItem?> {
            return arrayOfNulls(size)
        }
    }

}