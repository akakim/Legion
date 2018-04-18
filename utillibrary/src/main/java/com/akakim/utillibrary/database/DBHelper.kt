package com.akakim.utillibrary.database

import android.content.ContentValues
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

        val DATABASE_NAME = "saved_recording.db"
        val DATABASE_VERSION = 1

        // 테이블 명
        val TABLE_NAME                  = "savedRecording"            //  녹음파일
        val TABLE_TODO_LIST             = "todoListItem"        //  to
        val TABLE_BREATH_REOUTINE_LIST  = "breathRoutine"  //
        val TABLE_CHECK_LIST            = "checklistTable"



        // 컬럼명
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

        var onDatabaseChangedListener : OnDatabaseChangedListener? = null


    }

    var context : Context?




    // DB 생성시 호출되는 Callback
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL( SQL_CREATE_ENTRIES )
    }

    // DB가 기존에 있는데 버전이 업그레이되면 생성되는 콜백
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    constructor(context: Context?) : super( context, DATABASE_NAME,null, DATABASE_VERSION){
        this.context = context
    }


    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int, context1: Context) : super(context, name, factory, version) {
        this.context = context1
    }

    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int, errorHandler: DatabaseErrorHandler?, context1: Context) : super(context, name, factory, version, errorHandler) {
        this.context = context1

    }



    fun getItemAt( position : Int ) : RecordingItem? {


        val db = readableDatabase

        var projection = arrayOf(
                BaseColumns._ID,
                COLUMN_NAME_RECORDING_NAME,
                COLUMN_NAME_RECORDING_FILE_PATH,
                COLUMN_NAME_RECORDING_LENGTH,
                COLUMN_NAME_TIME_ADDED
        )

        var cursor = db.query(TABLE_NAME, projection, null,null,null,null,null,null)

        if( cursor.moveToPosition( position )){

            var aItem = RecordingItem().apply {
                id          = cursor.getInt( cursor.getColumnIndex( BaseColumns._ID))
                name        = cursor.getString( cursor.getColumnIndex ( COLUMN_NAME_RECORDING_NAME ))
                filePath    = cursor.getString( cursor.getColumnIndex( COLUMN_NAME_RECORDING_FILE_PATH ))
                length      = cursor.getInt( cursor.getColumnIndex( COLUMN_NAME_RECORDING_LENGTH ))
                time        = cursor.getLong( cursor.getColumnIndex( COLUMN_NAME_TIME_ADDED ))

            }

            cursor.close()
            return aItem

        }
        return null
    }

    fun removeItemWithId(id : Int){

    }


    fun restoreRecording(item: RecordingItem):Long {

        val db = writableDatabase

        val cv = ContentValues()

        return 0L

    }
}