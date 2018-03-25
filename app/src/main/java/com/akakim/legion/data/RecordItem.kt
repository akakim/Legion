package com.akakim.legion.data

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-21
 *
 *  리스트에 파일을 보여주는 것과 sqlite에 지정할 파일 정보도 지정할 것 ,
 */

data class RecordItem (
        val recordFileName :String,
        val recordLength : String,
        val recordDate : String,
        val recordFilePath :String
)