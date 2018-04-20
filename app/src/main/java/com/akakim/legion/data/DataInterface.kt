package com.akakim.legion.data

import android.content.ContentValues
import android.provider.BaseColumns

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-04-18
 *
 * 규격은 정해 져있지만 타입은 알수가없어서..
 * getItem같은걸 할때도 타입에 대해서 알 수가 없다.
 * Parcelable과 같이 순서를 강제해서 형태를 알 수있는 그런 건 없나 ..?
 */




open interface DataInterface {

//    recordTableName :String "saved_recording"

    companion object {
        const val _ID           = "_id"             // 공통 PK 컬럼명.
        const val _COUNT        = "_count"
        const val INTEGER_TYPE  = "INTEGER"
        const val TEXT_TYPE     ="TEXT"
        const val BLOB_TYPE     = "BLOB"

        const val DELUMINITER   = "_"
    }

    fun getPK() : String

    /**
     * PK는 AutoIncreament로 이기때문에 어떤 아이템을 더하면
     * PK값을 제외한 나머지 값들이 ContentValue에 들어가게된다.
     */
    fun getContentValue() : ContentValues

}