package com.akakim.legion.data

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-03-24
 *
 * 녹음한 파일과 관련된 이벤트 리스너
 */


interface OnEventListener{

    fun onEvent(event:  Int , file : String?)
}