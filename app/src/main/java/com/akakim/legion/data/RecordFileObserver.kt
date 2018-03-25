package com.akakim.legion.data

import android.content.Context
import android.os.FileObserver

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-24
 */


class RecordFileObserver : FileObserver{


    val listener : OnEventListener
    override fun onEvent(event: Int, path: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        listener?.onEvent( event, path )
    }

    constructor(path: String?,listener: OnEventListener) : this(path,ALL_EVENTS,listener)
    constructor(path: String?, mask: Int,listener: OnEventListener) : super(path, mask){

        this.listener = listener
    }


}