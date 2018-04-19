package com.akakim.legion.data

import android.content.ContentValues
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by RyoRyeong Kim on 2018-04-17.
 *
 * PK 값은 , groupName과 순번으로 정하자.
 * gruop
 * sequence
 * viewtype  = 0 인경우 Y /N
 *           = 1 인경우 1 - 5 까지 선택
 * score     = Y / N 인경우 0 또는 1 의값 ( 1 이면
 *           = 1 ~ 5 1 점부터 5점까지 체크
 *
 */

data class CheckList(
        var groupName : String,
        var sequence : Int,
        var viewType :Int,
        var content : String,
        var  score : Int ) : Parcelable,DataInterface{

    override fun getPK(): String {

        return groupName + sequence
    }

    override fun getContentValue(): ContentValues {

        val contentValue = ContentValues()
            contentValue.put(GROUP_NAME_COLUMN.first,groupName )
            contentValue.put(SEQUNCE_COLUMN.first,sequence )
            contentValue.put(VIEW_TYPE_COLUMN.first,viewType )
            contentValue.put(CONTENT_COLUMN.first,content )
            contentValue.put(SCORE_COLUMN.first,score )

        return contentValue
    }


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt()) {
    }



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(groupName)
        parcel.writeInt(sequence)
        parcel.writeInt(viewType)
        parcel.writeString(content)
        parcel.writeInt(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CheckList> {
        open val TABLE_CHECKLIST                                = "checkListTable"

        // PK 값이 Group Column과, Sequence값으로 변경
        open val PK                      : Pair<String,String>  = Pair( DataInterface._ID,       DataInterface.TEXT_TYPE)

        open val GROUP_NAME_COLUMN       : Pair<String,String>  = Pair( "groupName",          DataInterface.TEXT_TYPE)
        open val SEQUNCE_COLUMN          : Pair<String,String>  = Pair( "sequence",           DataInterface.INTEGER_TYPE)

        open val VIEW_TYPE_COLUMN        : Pair<String,String>  = Pair( "viewType",               DataInterface.TEXT_TYPE)
        open val CONTENT_COLUMN          : Pair<String,String>  = Pair( "content",                DataInterface.TEXT_TYPE)
        open val SCORE_COLUMN: Pair<String,String>  = Pair( "score",                  DataInterface.INTEGER_TYPE)


        open val COLUMN_LIST : Array<Pair<String,String>> = arrayOf( GROUP_NAME_COLUMN, SEQUNCE_COLUMN,VIEW_TYPE_COLUMN,CONTENT_COLUMN, SCORE_COLUMN)


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