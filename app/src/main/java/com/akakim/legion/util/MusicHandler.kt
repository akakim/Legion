package com.akakim.legion.util

import android.os.Handler
import android.os.Message


/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-11
 */


class MusicHandler : Handler(){

    override fun sendMessageAtTime(msg: Message?, uptimeMillis: Long): Boolean {
        return super.sendMessageAtTime(msg, uptimeMillis)
    }

    override fun dispatchMessage(msg: Message?) {
        super.dispatchMessage(msg)
    }

    override fun handleMessage(msg: Message?) {
        super.handleMessage(msg)
    }

    override fun getMessageName(message: Message?): String {
        return super.getMessageName(message)
    }

    override fun toString(): String {
        return super.toString()
    }
}