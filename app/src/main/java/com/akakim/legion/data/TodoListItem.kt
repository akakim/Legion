package com.akakim.legion.data

import android.content.ContentValues
import android.os.Parcel
import android.os.Parcelable

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-03-25
 *  boolean type은 Parcelable에서 지원하지 않는다. 따라서 타입캐스팅을하낟.
 *  할일 제목
 *  세부 할일
 *  날짜
 *
 *  TODO : 체크리스트 아이템과 묶어서 생각하기
 *  TODO : 엑셀처럼 정리된 형식의 파일 출력을 염두해두기
 *  TODO : 알람 기간
 *  TODO : 알람 시간
 *  TODO : 다른 앱을 참고해서, 데이터를 더 만들어보기 .
 *  TODO : 체크리스트나, 호흡 루틴을 연결시키는 무엇이 없을까.. ?
 */

 data class TodoListItem (
        val pk : Int,
        val todoTitle : String ,
        val todoDetailContent : String,
        val date : String,
        var checked : Int
        ): Parcelable , DataInterface{


    override fun getPK(): String {

        return pk.toString()
    }

    override fun getContentValue(): ContentValues {

        val contentValues : ContentValues = ContentValues()


//        contentValues.put( )

        return contentValues
    }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(dest: Parcel?, flags: Int) {

        dest?.apply {
            writeInt( pk)
            writeString( todoTitle)
            writeString( todoDetailContent)
            writeString( date)
            writeInt( checked)
        }


    }

    override fun describeContents(): Int {
        return 0
    }




    companion object CREATOR : Parcelable.Creator<TodoListItem> {

        // 인터페이스로 연결할수 있다면 참 좋을거같은데

        // 테이블 명
        val TABLE_TODO_LIST             = "todoListItem"        //  to

        val PK                          : Pair<String,String> = Pair( DataInterface._ID,       DataInterface.INTEGER_TYPE)
        val TODO_LIST_TITLE_COLUMN      : Pair<String,String> = Pair( "TODO_LIST_TITLE_COLUMN",              DataInterface.TEXT_TYPE)
        val TODO_DETAIL_CONTENT_COLUMN  : Pair<String,String> = Pair( "TODO_DETAIL_CONTENT_COLUMN",      DataInterface.TEXT_TYPE)
        val DATE_COLUMN                 : Pair<String,String> = Pair( "DATE_COLUMN",                   DataInterface.TEXT_TYPE)
        val CHECKED_COLUMN              : Pair<String,String> = Pair( "CHECKED_COLUMN",                DataInterface.INTEGER_TYPE)

        val COLUMN_LIST : Array<Pair<String,String>> = arrayOf(TODO_LIST_TITLE_COLUMN, TODO_DETAIL_CONTENT_COLUMN, DATE_COLUMN, CHECKED_COLUMN)

        override fun createFromParcel(parcel: Parcel): TodoListItem {
            return TodoListItem(parcel)
        }

        override fun newArray(size: Int): Array<TodoListItem?> {
            return arrayOfNulls(size)
        }
    }



}