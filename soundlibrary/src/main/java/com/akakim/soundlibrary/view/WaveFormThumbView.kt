/*
 *
 *  Copyright (C) 2008 Google Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.akakim.soundlibrary.view

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.v4.content.res.TypedArrayUtils
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import com.akakim.soundlibrary.R
import com.akakim.soundlibrary.WaveUtil
import com.akakim.soundlibrary.gesture.DragDetector
import com.akakim.soundlibrary.gesture.OnDragGestureListener

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-07-01
 */

open class WaveFormThumbView : View , OnDragGestureListener {

    private val waveFormPaint           : Paint             = Paint()
    private val waveFormHigLightPaint   : Paint             = Paint()
    private val bean                    : WaveForamInfo?    = null
    private var totalSecond             : Double            = 0.0

    private var thumbScale              : Float             = 0.0f
    private var thumbStartSecond        : Double            = 0.0
    private var thumbDuration           : Double            = 0.0
    private var dragDetector           : DragDetector?     = null
    private var mThumbStartTimePixel    : Int               = 0
    private var mThumbEndTimePixel      : Int               = 0


    var onDragThumbListener : OnDragThumbListener? = null

    constructor(context: Context?) : this(context, null )
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){

        init ( context, attrs )


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes){
        init( context, attrs )
    }


    private fun init(context: Context?, attr : AttributeSet?){

        var waveFormColor   : Int? = Color.BLACK
        var highLightColor  : Int? = Color.GRAY
        if( attr != null ){
            val typedArray = context?.obtainStyledAttributes( attr, R.styleable.WaveFormThumbView )

            waveFormColor = typedArray?.getColor(R.styleable.WaveFormThumbView_wf_wave_form_color ,Color.BLACK )
            highLightColor = typedArray?.getColor( R.styleable.WaveFormThumbView_wf_waveform_highlight_color, Color.GRAY)

            typedArray?.recycle()
        }

        if( waveFormColor != null ){
            waveFormPaint.color = waveFormColor
        }
        waveFormPaint.style =  Paint.Style.FILL_AND_STROKE
        waveFormPaint.strokeWidth = 0f


        if( highLightColor != null ){
            waveFormHigLightPaint.color = highLightColor
        }

        waveFormHigLightPaint.style = Paint.Style.FILL_AND_STROKE
        waveFormHigLightPaint.strokeWidth = 0f

        dragDetector = DragDetector( context , this )
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if( bean != null ){
            drawWave( canvas , bean )
        }
    }


    private fun drawWave(canvas : Canvas?, bean : WaveForamInfo ){

//        measuredWidth
//        measuredHeight

        val data  = bean.data
        val dataLength = bean.data.size
        val is8Bit = bean.bits == 8

        val dataPixel = 0
        var axisX = 0.0

        var finalAxis = -1
        var low = 0
        var high = 0
        var lowY = 0
        var highY = 0


        while( 0 < measuredWidth.toFloat()){

            if ( dataPixel < 0 || dataPixel >= dataLength ){
                break
            }
        }
    }
    override fun onDrag(dx: Float, dy: Float) {

        if ( bean == null ){
            return
        }



        thumbStartSecond.plus(
                WaveUtil.pixelsToSeconds(dx, bean.sampleRate,bean.samplePerPixel ,thumbScale)
        )

        // 오른쪽
        if( thumbStartSecond + thumbDuration > totalSecond ){
            thumbStartSecond = totalSecond - thumbDuration
        }

        // 왼쪽
        if( thumbStartSecond <0 ){
            thumbStartSecond = 0.0
        }

        onDragThumbListener?.onDrag( thumbStartSecond )

    }

    fun setOnDragThumbListener(onDragGestureListener: OnDragGestureListener ){
        this.onDragThumbListener = onDragThumbListener

    }

    interface OnDragThumbListener{
        fun onDrag( startTime : Double )
    }
}