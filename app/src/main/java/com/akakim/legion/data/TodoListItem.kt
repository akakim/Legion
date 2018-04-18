package com.akakim.legion.data

import android.os.Parcel
import android.os.Parcelable
import android.provider.BaseColumns
import java.time.Period

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-25
 *  boolean type은 Parcelable에서 지원하지 않는다. 따라서 타입캐스팅을하낟.
 *  할일 제목
 *  세부 할일
 *  날짜
 *
 *  TODO : 체크리스트 아이템과 묶어서 생각하기
 *  TODO : 엑셀처럼 정리된 형식의 파일 출력을 염두해두기
 *  TODO : 프라이머리키값은 뭘로하지?
 *  TODO : 알람 기간
 *  TODO : 알람 시간
 *  TODO : 다른 앱을 참고해서, 데이터를 더 만들어보기 .
 */

 data class TodoListItem (
        val pk : Int,
        val todoTitle : String ,
        val todoDetailContent : String,
        val date : String,
        var checked : Int
        ): Parcelable{
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


        // 테이블 명
        val TABLE_TODO_LIST             = "todoListItem"        //  to

        val pk                  : Pair<String,String> = Pair( DatabaseColumn._ID,       DatabaseColumn.INTEGER_TYPE)
        val todoTitle           : Pair<String,String> = Pair( "todoTitle",              DatabaseColumn.TEXT_TYPE)
        val todoDetailContent   : Pair<String,String> = Pair( "todoDetailContent",      DatabaseColumn.TEXT_TYPE)
        val date                : Pair<String,String> = Pair( "date",                   DatabaseColumn.TEXT_TYPE)
        var checked             : Pair<String,String> = Pair( "checked",                DatabaseColumn.INTEGER_TYPE)


        override fun createFromParcel(parcel: Parcel): TodoListItem {
            return TodoListItem(parcel)
        }

        override fun newArray(size: Int): Array<TodoListItem?> {
            return arrayOfNulls(size)
        }
    }



}