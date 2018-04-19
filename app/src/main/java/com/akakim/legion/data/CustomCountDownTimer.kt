package com.akakim.legion.data

import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import java.text.SimpleDateFormat

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-04-15
 */

open class CustomCountDownTimer : CountDownTimer{

    var millisInFuture : Long
    var countDownInterval : Long
    var mListener : CountDownListener


    constructor(mListener : CountDownListener, millisInFuture: Long, countDownInterval: Long)
            : super(millisInFuture, countDownInterval) {
        this. mListener = mListener

        this.millisInFuture = millisInFuture
        this.countDownInterval = countDownInterval
    }
    override fun onFinish() {
        Log.d("CustomCountDownTimer", "onFinish"  )
        mListener.onFinishCountDown()

    }

    override fun onTick(millisUntilFinished: Long) {

        Log.d("CustomCountDownTimer", "onTicksssss" + millisUntilFinished )
        mListener.onTick(millisUntilFinished,countDownInterval)
    }


    open interface CountDownListener{
        fun onFinishCountDown()
        fun onTick(millisUntilFinished: Long , countDownInterval: Long)
    }

}