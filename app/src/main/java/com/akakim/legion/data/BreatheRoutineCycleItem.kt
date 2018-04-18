package com.akakim.legion.data

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
        var pk : Int,
        var doThat : String ,
        var term : Int,
        var color: Int
) : Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt( pk )
        parcel.writeString( doThat )
        parcel.writeInt( term )
        parcel.writeInt( color )
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BreatheRoutineCycleItem> {
        override fun createFromParcel(parcel: Parcel): BreatheRoutineCycleItem {
            return BreatheRoutineCycleItem(parcel)
        }

        override fun newArray(size: Int): Array<BreatheRoutineCycleItem?> {
            return arrayOfNulls(size)
        }
    }

}