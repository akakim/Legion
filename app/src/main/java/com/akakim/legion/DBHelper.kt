package com.akakim.legion

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.akakim.legion.common.OnDatabaseChangedListener
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
        open    val LOG_TAG ="DBHelper"

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "archive.db"

        var dbInstance : DBHelper? =null

        open fun getInstance(context: Context) : DBHelper? {



            if( dbInstance == null ){
                dbInstance = DBHelper( context )
            }


            return dbInstance

        }


    }



    // default Cursor를 이용한다.

    private constructor(context: Context?) :this ( context, DATABASE_NAME,null, DATABASE_VERSION)
    private constructor(context: Context?, name: String?,factory: SQLiteDatabase.CursorFactory?, version: Int) : super(context, name, factory, version)
//    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int, errorHandler: DatabaseErrorHandler?) : super(context, name, factory, version, errorHandler)


    override fun onCreate(db: SQLiteDatabase?) {


        db?.apply {

            execSQL( createTableUsingPrimaryKey( TodoListItem.TABLE_TODO_LIST,                      TodoListItem.COLUMN_LIST ))
            execSQL( createTableUsingPrimaryKey( RecordItem.TABLE_RECORD,                           RecordItem.COLUMN_LIST ))
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
        builder.append( " PRIMARY KEY AUTOINCREMENT," )   // autoIncrement

        for( aItem in columnPair ){
            builder.append(aItem.first+ " " + aItem.second+",")
        }

        val middle : CharSequence = builder.removeRange( builder.length-1 , builder.length)

        val lastBuilder = StringBuilder( middle )


        lastBuilder.append(")")
//
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
        return StringBuilder().append( "DROP TABLE IF EXISTS ").append(tableName).toString()
    }


    open fun getCount( tableName : String ) : Int {
        val count = readableDatabase.query( tableName, arrayOf( DataInterface._ID ),null,null,null,null,null).count

        readableDatabase.close()


        return count
//                .let {
//
//            val count = it.count
//            it.close()
//            return count
//        }

    }


    /**
     * 인터페이스를 이용하여
     */
    open fun addItem( tableName: String ,items :DataInterface) : Long{

        val rowId : Long = writableDatabase.insert( tableName,null,items.getContentValue() )

        databaseChangeListener?.onNewDatabaseEntryAdded()

        return rowId
    }


    /**
     * 나름 실시간 동기화 하는 방법론
     */
    open fun getItemAtTodoListItem ( position : Int ) : TodoListItem{

        val aItem = TodoListItem()
        writableDatabase.query( TodoListItem.TABLE_TODO_LIST,null,null,null,null,null,null,null)
                .let {
                    if (it.moveToPosition( position )){
                        aItem.pk                =  it.getInt( it.getColumnIndex( DataInterface._ID ))
                        aItem.todoTitle         =  it.getString ( it.getColumnIndex( TodoListItem.TODO_LIST_TITLE_COLUMN.first ))
                        aItem.todoDetailContent =  it.getString( it.getColumnIndex( TodoListItem.TODO_DETAIL_CONTENT_COLUMN.first ))
                        aItem.checked           =  it.getInt (  it.getColumnIndex( TodoListItem.DATE_COLUMN.first ))
                        aItem.date              =  it.getString ( it.getColumnIndex( TodoListItem.DATE_COLUMN.first ))
                    }
                }

        return aItem
    }



    open fun getRecordItemList() : ArrayList<RecordItem>{

        var cursor =  writableDatabase.query( TodoListItem.TABLE_TODO_LIST,null,null,null,null,null,null,null)
        val resultList = ArrayList<RecordItem>()


        cursor.moveToFirst()
        cursor.setNotificationUri()

        while( cursor.moveToNext() ){
            val aItem = RecordItem()
            aItem.pk                        =  cursor.getInt( cursor.getColumnIndex( RecordItem.PK.first ))
            aItem.recordFileName            =  cursor.getString ( cursor.getColumnIndex( RecordItem.RECORD_FILE_NAME_COLUMN.first ))
            aItem.recordFilePath            =  cursor.getString( cursor.getColumnIndex( RecordItem.RECORD_FILE_PATH.first ))
            aItem.recordDate                =  cursor.getString (  cursor.getColumnIndex( RecordItem.RECORD_DATE.first ))
            aItem.recordLength              =  cursor.getLong ( cursor.getColumnIndex( RecordItem.RECORD_LENGTH.first ))

            resultList.add(aItem)

        }

//

        return resultList
    }

    /**
     *
     */
    open fun getRecordItemItem ( position : Int ) : RecordItem {
        val aItem = RecordItem()
        var cursor =  writableDatabase.query( TodoListItem.TABLE_TODO_LIST,null,null,null,null,null,null,null)

                    if (cursor.moveToPosition( position )){
                        aItem.pk                        =  cursor.getInt( cursor.getColumnIndex( RecordItem.PK.first ))
                        aItem.recordFileName            =  cursor.getString ( cursor.getColumnIndex( RecordItem.RECORD_FILE_NAME_COLUMN.first ))
                        aItem.recordFilePath            =  cursor.getString( cursor.getColumnIndex( RecordItem.RECORD_FILE_PATH.first ))
                        aItem.recordDate                =  cursor.getString (  cursor.getColumnIndex( RecordItem.RECORD_DATE.first ))
                        aItem.recordLength              =  cursor.getLong ( cursor.getColumnIndex( RecordItem.RECORD_LENGTH.first ))
                    }




//        if( cursor != null ){
//            cursor.

//            cursor.moveToFirst()
//            cursor.moveToPosition( position )
//
//            val columnNames = cursor.columnNames
//
//            Log.d("DBHelper","position : " + position)
//            for( columName in columnNames ){
//                Log.d("DBHelper","columnName ; " + columName + " "+ "int "  +     cursor.getColumnIndex( columName ))
//            }
//            aItem.pk                        =  cursor.getInt( 0 )
//                        aItem.recordFileName            =  it.getString ( it.getColumnIndex( RecordItem.RECORD_FILE_NAME_COLUMN.first ))
//                        aItem.recordFilePath            =  it.getString( it.getColumnIndex( RecordItem.RECORD_FILE_PATH.first ))
//                        aItem.recordDate                =  it.getString (  it.getColumnIndex( RecordItem.RECORD_DATE.first ))
//                        aItem.recordLength              =  it.getLong ( it.getColumnIndex( RecordItem.RECORD_LENGTH.first ))
//            cursor.getInt( cursor.getColumnIndex( columName ) )

//            cursor.getColumnName( )
//            Log.d("getCount " , cursor.)
//            aItem.pk  = cursor.getInt( 0 )
//            cursor.getInt( 0 )
//        }
//        cursor.moveToPosition( position )
//                .let {
//                    if (it.moveToPosition( position )){
//                        aItem.pk                        =  it.getInt( it.getColumnIndex( RecordItem.PK.first ))
//                        aItem.recordFileName            =  it.getString ( it.getColumnIndex( RecordItem.RECORD_FILE_NAME_COLUMN.first ))
//                        aItem.recordFilePath            =  it.getString( it.getColumnIndex( RecordItem.RECORD_FILE_PATH.first ))
//                        aItem.recordDate                =  it.getString (  it.getColumnIndex( RecordItem.RECORD_DATE.first ))
//                        aItem.recordLength              =  it.getLong ( it.getColumnIndex( RecordItem.RECORD_LENGTH.first ))
//                    }
//
//                }



        return aItem
    }


    open fun getCheckListItem( position: Int ) : CheckList{
        val aItem = CheckList()
        writableDatabase.query( TodoListItem.TABLE_TODO_LIST,null,null,null,null,null,null,null)
                .let {
                    if (it.moveToPosition( position )){
                        aItem.groupName             = it.getString( it.getColumnIndex( CheckList.GROUP_NAME_COLUMN.first))
                        aItem.content               = it.getString( it.getColumnIndex( CheckList.CONTENT_COLUMN.first))
                        aItem.sequence              = it.getInt( it.getColumnIndex( CheckList.SEQUNCE_COLUMN.first))
                        aItem.score                 = it.getInt( it.getColumnIndex( CheckList.SCORE_COLUMN.first))
                        aItem.viewType              = it.getInt( it.getColumnIndex( CheckList.VIEW_TYPE_COLUMN.first))

                    }
                }

        return aItem
    }

    open fun getBreathRoutineCycleItem( position : Int ) : BreatheRoutineCycleItem {
        val aItem = BreatheRoutineCycleItem()
        writableDatabase.query( TodoListItem.TABLE_TODO_LIST,null,null,null,null,null,null,null)
                .let {
                    if (it.moveToPosition( position )){

                        aItem.sequence                  = it.getInt( it.getColumnIndex( BreatheRoutineCycleItem.SEQUENCE.first ))
                        aItem.doThat                    = it.getString ( it.getColumnIndex( BreatheRoutineCycleItem.DO_THAT_COLUMN.first ))
                        aItem.color                     = it.getInt( it.getColumnIndex( BreatheRoutineCycleItem.COLOR_COLUMN.first ))
                        aItem.groupId                   = it.getString( it.getColumnIndex( BreatheRoutineCycleItem.GROUP_ID_COLUMN.first ))
                        aItem.term                      = it.getInt(it.getColumnIndex(BreatheRoutineCycleItem.TERM_COLUMN.first))

                    }
                }

        return aItem
    }

    /**
     * 반영된 열이 없으므로 -1 을 반환한다.
     * 반영된 열이 있다면, 있는 수만큼 반영이된다.
     */
    open fun renameItem(tableName: String, dataInterface: DataInterface?) : Int {

        if( dataInterface == null ){
            return -1
        }


        val affectRow = writableDatabase.update( tableName, dataInterface.getContentValue(), DataInterface._ID+"="+dataInterface.getPK(),null)
        databaseChangeListener?.onDatabaseEntryRenamed( affectRow )

        return affectRow
    }


    /**
     * 반영된 열이 없으므로 -1 을 반환한다.
     * 반영된 열이 있다면, 있는 수만큼 반영이된다.
     */
    open fun updateItem( tableName: String, dataInterface: DataInterface?) : Int {

        if( dataInterface == null ){
            return -1
        }

        val affectRow = writableDatabase.update( tableName, dataInterface.getContentValue(), DataInterface._ID+"="+dataInterface.getPK(),null)
        databaseChangeListener?.onDatabaseEntryRenamed( affectRow )

        return affectRow
    }
    /**
     *
     *
     */
    open fun removeTodoListItem( tableName: String, id : String ): Int {
        val affectRow = writableDatabase.delete(tableName,DataInterface._ID+"=?" , arrayOf( id ))

        return affectRow
    }
}