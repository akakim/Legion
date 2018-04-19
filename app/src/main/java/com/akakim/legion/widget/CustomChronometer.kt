package com.akakim.legion.widget

import android.content.Context
import android.content.res.TypedArray
import android.os.SystemClock
import android.util.AttributeSet
import android.widget.RemoteViews
import android.widget.TextView
import com.akakim.legion.data.CustomCountDownTimer
import java.util.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-04-16
 */


@RemoteViews.RemoteView
open class CustomChronometer : TextView{

    var mBase = 0L
        set(value :Long) {

            mBase = value

            dispatchChronometerTick()
            updateText( SystemClock.elapsedRealtime() )
        }
    var mNow    : Long = 0L

    var mVisible    : Boolean = false
    var mStart      : Boolean = false
    var mRunning    : Boolean = false
    var mLogged     : Boolean = false

    var mFormat     : String    =""
    var mFormatter  : Formatter? = null
    var mFormatterLocale :  Locale = Locale.getDefault()
//    var mFormatterArgs : Array<Object> = Array<Object>(0,0)
//    var mFormatBuilder : StringBuilder
    var mOnChronometerTickListener : OnChronometerTickListener? = null
//    var mRecycler : StringBuilder
    var mCountDown : Boolean = true

    open interface  OnChronometerTickListener{
        fun onChronometerTick(chronometer : CustomChronometer )
    }




    constructor( context: Context? ): this(context,null,-1,-1)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,-1,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int )
            : super( context,attrs,defStyleAttr,defStyleRes ){

//        val a : TypedArray = context.obtainStyledAttributes( attrs,com.android.internal.R.styleable.C)
    }

    @Synchronized
    private fun updateText(now : Long ){
        mNow = now
//        var seconds : Long = mCou

    }

    fun dispatchChronometerTick(){
        mOnChronometerTickListener?.onChronometerTick( this )
    }

    companion object {
        val MIN_IN_SEC = 60
        val HOUR_IN_SEC = MIN_IN_SEC * 60


    }
}