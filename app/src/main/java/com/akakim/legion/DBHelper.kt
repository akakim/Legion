package com.akakim.legion

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.akakim.legion.data.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-03-10
 */

class DBHelper :SQLiteOpenHelper{

    var databaseChangeListener : OnDatabaseChangedListener? = null


    companion object {
        open val LOG_TAG ="DBHelper"

        private val DATABASE_VERSION = 1

    }



    // default Cursor를 이용한다.
    constructor(context: Context?, name: String?, version: Int) : super(context, name, null, version)
    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int, errorHandler: DatabaseErrorHandler?) : super(context, name, factory, version, errorHandler)


    override fun onCreate(db: SQLiteDatabase?) {


        db?.apply {

            execSQL( createTableUsingPrimaryKey( TodoListItem.TABLE_TODO_LIST,                  TodoListItem.COLUMN_LIST ))
            execSQL( createTableUsingPrimaryKey( RecordItem.TABLE_RECORD,                       RecordItem.COLUMN_LIST ))
            execSQL( createTableNotUseAutoIncrement( CheckList.TABLE_CHECKLIST,                     CheckList.COLUMN_LIST ))
            execSQL( createTableNotUseAutoIncrement( BreatheRoutineCycleItem.TABLE_BREATH_ROUTINE,  BreatheRoutineCycleItem.COLUMN_LIST ))
        }


    }



    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }

    private fun createTableUsingPrimaryKey(tableName: String,columnPair: Array< Pair<String,String> >) : String{

        val builder =StringBuilder()

        builder.append("CREATE TABLE ")
        builder.append(tableName)
        builder.append("(")

        builder.append( DataInterface._ID )
        builder.append( " " + DataInterface.INTEGER_TYPE)
        builder.append( " PRIMARY KEY," )   // autoIncrement

        for( aItem in columnPair ){
            builder.append(aItem.first+ " " + aItem.second+",")
        }

        val middle : CharSequence = builder.removeRange( builder.length-1 , builder.length)

        val lastBuilder = StringBuilder( middle )


        lastBuilder.append(")")


        return lastBuilder.toString()
    }

    private fun createTableNotUseAutoIncrement(tableName: String,columnPair: Array< Pair<String,String>>) : String{
        val builder =StringBuilder()

        builder.append("CREATE TABLE ")
        builder.append(tableName)
        builder.append("(")

        builder.append( DataInterface._ID )
        builder.append( " " + DataInterface.TEXT_TYPE)
        builder.append( " PRIMARY KEY," )

        for( aItem in columnPair ){
            builder.append(aItem.first+ " " + aItem.second+",")
        }

        val middle : CharSequence = builder.removeRange( builder.length-1 , builder.length)

        val lastBuilder = StringBuilder( middle )


        lastBuilder.append(")")


        return lastBuilder.toString()
    }

    private fun dropTable( tableName : String) :String{

        var strBuilder = StringBuilder()
        strBuilder.append("DROP TABLE IF EXISTS ")
        strBuilder.append(tableName)
        return strBuilder.toString()
    }


    open fun getCount( tableName : String , position : Int ) : Int {

        val db = readableDatabase
        val projection = arrayOf( DataInterface._ID)
        val cursor = db.query( tableName, projection,null,null,null,null,null)
        val count = cursor.count

        cursor.close()
        return count
    }


    /**
     * 인터페이스를 이용하여
     */
    open fun addItem( tableName: String ,items :DataInterface) : Long{

        val db = writableDatabase

        val rowId = db.insert( tableName,null,items.getContentValue() )


        return rowId
    }

    // Parcelable로 일단 입력하고 객체를 만드는 방법은 없을까??
//    open fun getItemAtTodoListItem ( position : Int ) : TodoListItem{
//
//
//
//        val db = writableDatabase
//
//        db.query( TodoListItem.TABLE_TODO_LIST,null,null,null,null,null,null,null)
//    }
//
//
//
//    open fun getRecordItemItem ( position : Int ) : RecordItem {
//
//    }
//
}