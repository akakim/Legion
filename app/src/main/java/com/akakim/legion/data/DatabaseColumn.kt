package com.akakim.legion.data

import android.provider.BaseColumns

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-04-18
 */


open interface DatabaseColumn {

//    recordTableName :String "saved_recording"

    companion object {
        const val _ID           = "_id"
        const val _COUNT        = "_count"
        const val INTEGER_TYPE  = "INTEGER"
        const val TEXT_TYPE     ="TEXT"
        const val BLOB_TYPE     = "BLOB"
    }

}