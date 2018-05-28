package com.akakim.legion.data

import android.content.ContentValues
import android.os.Parcel
import android.os.Parcelable

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-03-21
 *
 *  리스트에 파일을 보여주는 것과 sqlite에 지정할 파일 정보도 지정할 것 ,
 */

data class RecordItem (
        var pk  : Int ,
        var recordFileName  : String,
        var recordLength    : Long,
        var recordDate      : String,
        var recordFilePath  : String
) : DataInterface,Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString()) {
    }

    constructor(): this(-1,"",-1L,"","")


    override fun getPK(): String {
        return pk.toString()
    }

    override fun getContentValue(): ContentValues {

        val contentValue = ContentValues()

        contentValue.put(RECORD_FILE_NAME_COLUMN.first,recordFileName)
        contentValue.put(RECORD_LENGTH.first,recordLength)
        contentValue.put(RECORD_DATE.first,recordDate)
        contentValue.put(RECORD_FILE_PATH.first,recordFilePath)

        return contentValue
    }

    override fun toString(): String {
        return "RecordItem(pk=$pk, recordFileName='$recordFileName', recordLength=$recordLength, recordDate=$recordDate, recordFilePath='$recordFilePath')"
    }


    companion object CREATOR : Parcelable.Creator<RecordItem> {
        val TABLE_RECORD             = "tableRecord"        //  to

        val PK                      : Pair<String,String> = Pair( DataInterface._ID,            DataInterface.INTEGER_TYPE)
        val RECORD_FILE_NAME_COLUMN : Pair<String,String> = Pair( "RECORD_FILE_NAME_COLUMN",              DataInterface.TEXT_TYPE)
        val RECORD_LENGTH           : Pair<String,String> = Pair( "RECORD_LENGTH",                  DataInterface.INTEGER_TYPE)
        val RECORD_DATE             : Pair<String,String> = Pair( "RECORD_DATE",                   DataInterface.TEXT_TYPE)
        val RECORD_FILE_PATH        : Pair<String,String> = Pair( "RECORD_FILE_PATH",                DataInterface.TEXT_TYPE)


        val COLUMN_LIST             : Array<Pair<String,String>> = arrayOf(RECORD_FILE_NAME_COLUMN, RECORD_LENGTH, RECORD_DATE, RECORD_FILE_PATH)

        override fun createFromParcel(parcel: Parcel): RecordItem {
            return RecordItem(parcel)
        }

        override fun newArray(size: Int): Array<RecordItem?> {
            return arrayOfNulls(size)
        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pk)
        parcel.writeString(recordFileName)
        parcel.writeLong(recordLength)
        parcel.writeString(recordDate)
        parcel.writeString(recordFilePath)
    }

    override fun describeContents(): Int {
        return 0
    }

}