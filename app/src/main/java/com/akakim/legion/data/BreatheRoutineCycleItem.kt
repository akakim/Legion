package com.akakim.legion.data

import android.content.ContentValues
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by RyoRyeong Kim on 2018-04-06.
 *
 * 들숨 , 호흡 멈춤, 날숨 과 그 기간을 의미하는 클래스
 * 이게 적어도 들숨 날숨은 반드시 들어가고 호흡 멈춤은 들어갈수 있고 아닐 수 있다.
 * TODO : 사용자가 만들기 나름
 * TODO : 기본 설정도 만들어 놓자.
 */


data class BreatheRoutineCycleItem(
        var sequence : Int,
        var doThat : String,
        var term : Int,
        var color: Int,
        var groupId : String
) : Parcelable,DataInterface{
    override fun getPK(): String {
        return groupId + sequence
    }

    constructor() : this (-1,"",-1,-1,"")
    override fun getContentValue(): ContentValues {

        val resultValue = ContentValues()

        resultValue.put(DO_THAT_COLUMN.first, doThat)
        resultValue.put(TERM_COLUMN.first, term)
        resultValue.put(COLOR_COLUMN.first, color)
        resultValue.put(GROUP_ID_COLUMN.first, groupId)


        return resultValue
    }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt( sequence )
        parcel.writeString( doThat )
        parcel.writeInt( term )
        parcel.writeInt( color )
        parcel.writeString( groupId )
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BreatheRoutineCycleItem> {

        open val TABLE_BREATH_ROUTINE                                = "breathRoutine"
        open val SEQUENCE                         : Pair<String,String>  = Pair( DataInterface._ID,       DataInterface.INTEGER_TYPE)
        open val DO_THAT_COLUMN             : Pair<String,String>  = Pair( "viewType",               DataInterface.TEXT_TYPE)
        open val TERM_COLUMN                       : Pair<String,String>  = Pair( "content",                DataInterface.TEXT_TYPE)
        open val COLOR_COLUMN                      : Pair<String,String>  = Pair( "score",                  DataInterface.INTEGER_TYPE)
        open val GROUP_ID_COLUMN                   : Pair<String,String>  = Pair( "groupId",                  DataInterface.INTEGER_TYPE)


        open val COLUMN_LIST : Array<Pair<String,String>> = arrayOf( DO_THAT_COLUMN, TERM_COLUMN, COLOR_COLUMN ,GROUP_ID_COLUMN)

        override fun createFromParcel(parcel: Parcel): BreatheRoutineCycleItem {
            return BreatheRoutineCycleItem(parcel)
        }

        override fun newArray(size: Int): Array<BreatheRoutineCycleItem?> {
            return arrayOfNulls(size)
        }
    }

}