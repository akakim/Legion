package com.akakim.legion.data

import android.os.CountDownTimer
import android.widget.TextView
import java.text.SimpleDateFormat

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-04-15
 */

open class CustomCountDownTimer : CountDownTimer{


    val targetTextView : TextView

    var formatter : SimpleDateFormat

    init {
        formatter = SimpleDateFormat("MM:SS")
    }
    constructor(targetTextView : TextView, millisInFuture: Long, countDownInterval: Long)
    : super(millisInFuture, countDownInterval) {
        this. targetTextView = targetTextView
    }
    override fun onFinish() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTick(millisUntilFinished: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}