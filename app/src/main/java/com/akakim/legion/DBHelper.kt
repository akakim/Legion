package com.akakim.legion

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-10
 */

class DBHelper :SQLiteOpenHelper{

    companion object {
        open val LOG_TAG ="DBHelper"
        open val TABLE_NAME= " saving_void"

        open val COLUMN_NAME_RECORDING_NAME = "recording_name"
        open val COLUMN_NAME_RECORDING_FILE_PATH = "file_path"
        open val COLUMN_NAME_RECODDING_LENGTH= "length"
        open val COLUMN_NAME_TIME_ADDED = "time_added"

             val TEXT_TYPE = "TEXT"

    }



    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(context, name, factory, version)
    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int, errorHandler: DatabaseErrorHandler?) : super(context, name, factory, version, errorHandler)


    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}