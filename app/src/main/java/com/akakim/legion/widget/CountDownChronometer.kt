package com.akakim.legion.widget

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import com.akakim.legion.data.CustomCountDownTimer
import java.text.SimpleDateFormat

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-04-15
 *
 * 사용하지 않는다.
 */

@Deprecated("none usage")
open class CountDownChronometer : TextView,CustomCountDownTimer.CountDownListener {


    var formatter       : SimpleDateFormat
    var isStart         : Boolean
    var countDownTimer  : CustomCountDownTimer




    init {
        isStart     = false
        formatter   = SimpleDateFormat("HH:MM:SS")
    }
    constructor( context: Context? ): this(context,null,-1,-1)




    constructor( context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int )
            : super( context,attrs,defStyleAttr,defStyleRes ){

        countDownTimer = CustomCountDownTimer(this,60000,1000)

        // TODO : Chronometer 스타일링 분석하기.
    }

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,-1,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr,0)

    open fun setStartTime(futureTime : Long ){
        countDownTimer = CustomCountDownTimer( this, futureTime,1000L)
    }

//    open fun toggle

    open fun start(){


        this.post{
            countDownTimer.start()
        }


    }

    open fun cancel(){
        countDownTimer.cancel()
    }

    open fun finish(){
        countDownTimer.onFinish()
    }



    override fun setText(text: CharSequence?, type: BufferType?) {



        super.setText(text, type)
    }

    override fun onTick(millisUntilFinished: Long, countDownInterval: Long) {


            val str =   (millisUntilFinished/countDownInterval).toString()

            Log.d( "onTick",str)
            this.setText( str , BufferType.EDITABLE )
            this.postInvalidate()



    }

    override fun onFinishCountDown() {


    }

}