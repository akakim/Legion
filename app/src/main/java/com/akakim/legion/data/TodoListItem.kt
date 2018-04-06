package com.akakim.legion.data

import android.os.Parcel
import android.os.Parcelable
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
        val pk : String,
        val todoTitle : String ,
        val todoDetailContent : String,
        val date : String,
        var checked : Int): Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<TodoListItem> {
        override fun createFromParcel(parcel: Parcel): TodoListItem {
            return TodoListItem(parcel)
        }

        override fun newArray(size: Int): Array<TodoListItem?> {
            return arrayOfNulls(size)
        }
    }

}