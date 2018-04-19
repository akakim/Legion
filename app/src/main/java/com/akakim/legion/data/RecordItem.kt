package com.akakim.legion.data

import android.content.ContentValues

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-21
 *
 *  리스트에 파일을 보여주는 것과 sqlite에 지정할 파일 정보도 지정할 것 ,
 */

data class RecordItem (
        val pk : Int ,
        val recordFileName :String,
        val recordLength : String,
        val recordDate : String,
        val recordFilePath :String
) : DataInterface{
    override fun getPK(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContentValue(): ContentValues {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        val TABLE_RECORD             = "tableRecord"        //  to

        val pk                  : Pair<String,String> = Pair( DataInterface._ID,       DataInterface.INTEGER_TYPE)
        val recordFileName      : Pair<String,String> = Pair( "recordFileName",              DataInterface.TEXT_TYPE)
        val recordLength        : Pair<String,String> = Pair( "recordLength",      DataInterface.TEXT_TYPE)
        val recordDate          : Pair<String,String> = Pair( "recordDate",                   DataInterface.TEXT_TYPE)
        var recordFilePath      : Pair<String,String> = Pair( "recordFilePath",                DataInterface.TEXT_TYPE)


        val COLUMN_LIST : Array<Pair<String,String>> = arrayOf( recordFileName,recordLength, recordDate, recordFileName )
    }
}