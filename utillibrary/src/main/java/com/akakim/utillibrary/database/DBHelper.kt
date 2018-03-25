package com.akakim.utillibrary.database

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.akakim.utillibrary.data.RecordingItem

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-11
 */


open class DBHelper : SQLiteOpenHelper,BaseColumns{

    companion object {


        val TAG = "DBHelper"

        val DATABASE_VERSION = "saved_recording.db"
        // 테이블 명
        val TABLE_NAME="saved_recording"

        //
        val COLUMN_NAME_RECORDING_NAME = "recording_name"
        val COLUMN_NAME_RECORDING_FILE_PATH = "file_path"
        val COLUMN_NAME_RECORDING_LENGTH = "length"
        val COLUMN_NAME_TIME_ADDED = "time_added"


        //
        val TEXT_TYPE =" TEXT"


        val SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY ," +
                        COLUMN_NAME_RECORDING_NAME + " TEXT," +
                        COLUMN_NAME_RECORDING_FILE_PATH + " TEXT,"+
                        COLUMN_NAME_RECORDING_LENGTH + " INTEGER," +
                        COLUMN_NAME_TIME_ADDED +" INTEGER)"

        @SuppressWarnings(" unused " )
        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME
    }

    var context : Context



    // DB 생성시 호출되는 Callback
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL( SQL_CREATE_ENTRIES )
    }

    // DB가 기존에 있는데 버전이 업그레이되면 생성되는 콜백
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    constructor(context: Context?) : super( context){
//        this.context = context
//    }

    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int, context1: Context) : super(context, name, factory, version) {
        this.context = context1
    }

    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int, errorHandler: DatabaseErrorHandler?, context1: Context) : super(context, name, factory, version, errorHandler) {
        this.context = context1

    }


//    fun restoreRecording(item: RecordingItem):Long {
//
//    }

//    interface DBHelperItem : BaseColumns{
//        abstract val TABLE_NAME = "saved_recordings"
//
//    }
}