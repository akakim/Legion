package com.akakim.legion.widget

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.widget.TextView
import com.akakim.legion.data.CustomCountDownTimer

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-04-15
 */


open class CountDownChronometer : TextView {


    var countDownTimer : CustomCountDownTimer

    init {

    }
    constructor( context: Context? ): this(context,null,-1,-1)



    constructor( context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int )
            : super( context,attrs,defStyleAttr,defStyleRes ){

        countDownTimer = CustomCountDownTimer(60000,1000)
//        val a = context.obtainStyledAttributes( attrs, com.android.internal.R.styleable)
    }
}