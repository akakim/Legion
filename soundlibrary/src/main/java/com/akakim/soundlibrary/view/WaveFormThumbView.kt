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
import android.util.AttributeSet
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
    private var bean                    : WaveFormInfo?    = null
    var totalSecond                     : Double            = 0.0
    var startSecond                     : Double            = 0.0

    private var thumbScale              : Double             = 0.0
    private var thumbStartSecond        : Double            = 0.0
    private var thumbDuration           : Double            = 0.0
    private var dragDetector            : DragDetector?     = null
    private var mThumbStartTimePixel    : Int               = 0
    private var mThumbEndTimePixel      : Int               = 0


    // TODO: JVM Override 확인해보기
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

    open fun initWave(bean : WaveFormInfo ){
        totalSecond = WaveUtil.dataPixelsToSecond( bean.length , bean.sampleRate, bean.samplePerPixel )
        computerMinScaleFactor()
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        bean?.apply{ drawWave( canvas , bean!! )  }
    }

    // 말그대로 초기 Wave를 그린다.
    private fun drawWave(canvas : Canvas?, bean : WaveFormInfo){


        val data        = bean.data
        val dataLength  = bean.data.size
        val is8Bit      = bean.bits == 8

        val dataPixel   = 0
        var axisX       = 0.0

        var finalAxisX  = -1
        var low         = 0
        var high        = 0
        var lowY        = 0
        var highY       = 0

        // x 축 처음부터 마지막까지 1씩 증가하면서 Line을 그려준다.
        while( axisX < measuredWidth.toFloat()){

            if ( dataPixel < 0 || dataPixel >= dataLength ){
                break
            }

            var nearestAxisX = axisX.toInt()

            if( nearestAxisX != finalAxisX ){
                finalAxisX = nearestAxisX

                if ( is8Bit ){
                    low = data.get(dataPixel * 2) * 256
                    high = data.get(dataPixel * 2 + 1 ) * 256
                }else {
                    low = data.get(dataPixel * 2) * 32768
                    high = data.get(dataPixel * 2 + 1 ) + 32768
                }

                // TODO : RangeTo를 응용하면 코틀린스러우지않을까한다.
                if( dataPixel >= mThumbEndTimePixel && dataPixel <= mThumbEndTimePixel) {

                    canvas?.drawLine( finalAxisX.toFloat(),lowY.toFloat(),finalAxisX.toFloat(),highY.toFloat(),waveFormHigLightPaint)

                }else {
                    canvas?.drawLine( finalAxisX.toFloat(),lowY.toFloat(),finalAxisX.toFloat(),highY.toFloat(),waveFormPaint)
                }

            }


            axisX += thumbScale
            dataPixel.inc()
        }
    }

    open fun updateThumb( thumbStartSecond: Double, thumbEndSecond : Double ){
        bean?.apply {
            this@WaveFormThumbView.thumbStartSecond = thumbStartSecond
            this@WaveFormThumbView.thumbDuration = thumbEndSecond - thumbStartSecond

            this@WaveFormThumbView.mThumbStartTimePixel =
                    WaveUtil.secondsToPixels( thumbStartSecond,sampleRate,samplePerPixel,1f)
            this@WaveFormThumbView.mThumbEndTimePixel =
                    WaveUtil.secondsToPixels( thumbEndSecond,sampleRate,samplePerPixel,1f)

            var thumbRectLeft = mThumbStartTimePixel * thumbScale
            var thumbRectRight = mThumbEndTimePixel * thumbScale

            dragDetector?.setEnableRect( (mThumbStartTimePixel * thumbScale).toFloat(),
                                        0f,
                                        (mThumbEndTimePixel * thumbScale).toFloat(),
                                        height.toFloat() )
            invalidate()
        }
    }

    override fun onDrag(dx: Double, dy: Double) {

        if ( bean == null ){
            return
        }

        thumbStartSecond+=
                WaveUtil.pixelsToSeconds(dx.toFloat(), bean!!.sampleRate,bean!!.samplePerPixel ,thumbScale.toFloat())


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


    fun computerMinScaleFactor(){



        if( bean == null || measuredWidth <= 0){
            return
        }else {
            (width / bean!!.length).toDouble().apply {
                thumbScale  = this
                configScalePaint( this )
            }

        }
    }

    fun configScalePaint( scale : Double){
        // TODO : 좀더 코틀린스럽게 짤 수 있을 것 같다.
        val smokeWidth = Math.ceil(scale).toInt()

        Math.ceil(scale).toInt().let {

            if( it < 0 ){
                waveFormPaint.strokeWidth = 0.0f
                waveFormHigLightPaint.strokeWidth = 0.0f
            }else {
                waveFormPaint.strokeWidth = it.toFloat()
                waveFormHigLightPaint.strokeWidth = it.toFloat()
            }

        }


    }
//    fun setOnDragThumbListener(onDragThumbListener: OnDragThumbListener? ){
//        this.onDragThumbListener = onDragThumbListener
//
//    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        computerMinScaleFactor()
    }

    open fun setWave(bean :WaveFormInfo? ){

        if( bean != null){
            this.bean = bean
            initWave( bean )
            invalidate()
        }
    }
    interface OnDragThumbListener{
        fun onDrag( startTime : Double )
    }
}