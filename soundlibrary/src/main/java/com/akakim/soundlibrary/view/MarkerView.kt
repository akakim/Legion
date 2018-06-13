
package com.akakim.soundlibrary.view

/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent

//import Motio

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-13
 *
 * 드래그 가능한 마커의 시작과 끝을 표현한다.
 *
 * 대부분의 이벤트들은 설계된 인터페이스를 따라서 client로 들어간다.
 *
 * 이 클래스는 직접적으로 그것의 속도를 추적하는 것을 유지한다.
 *
 * 이 제어가 컨트롤 할 때 왼쪽 혹은 오른쪽으로
 *
 * Represents a draggable start or end marker.
 *
 * Most events are passed back to the client class using a
 * listener interface.
 *
 * This class directly keeps track of its own velocity, though,
 * accelerating as the user holds down the left or right arrows
 * while this control is focused.
 *
 *  TODO : MotionEvent 가
 */


open class MarkerView : AppCompatImageView {



    interface MarkerListener {

        fun markerTouchStart(markerView: MarkerView, pos: Float)
        fun markerTouchMove(markerView: MarkerView, pos: Float)
        fun markerTouchEnd(markerView: MarkerView)
        fun markerFocus(markerView: MarkerView)
        fun markerLeft(markerView: MarkerView,velocity : Int )
        fun markerRight(markerView: MarkerView,velocity: Int)
        fun markerEnter(markerView: MarkerView)
        fun markerKeyUp()
        fun markerDraw()
    }


    var veloocity: Int
    private var listener: MarkerListener?

    init {
        veloocity = 0
        listener = null
    }

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    open fun setListener(listener: MarkerListener) {
        this.listener = listener
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // 우리는 raw x 이 뷰
                //
                requestFocus()
                listener?.markerTouchStart(this, event.rawX)
            }
            MotionEvent.ACTION_MOVE -> {
                listener?.markerTouchMove(this, event.rawX)
            }
            MotionEvent.ACTION_UP -> {
                listener?.markerTouchEnd(this)
            }

        }

        return true
    }

    override fun onFocusChanged(gainFocus: Boolean, direction: Int, previouslyFocusedRect: Rect?) {

        if( gainFocus ){
            listener?.markerFocus( this )
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        listener?.markerDraw()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        veloocity.inc()

        var v = Math.sqrt( (1 + veloocity / 2).toDouble() )


        when( keyCode ){
            KeyEvent.KEYCODE_DPAD_LEFT->{
                listener?.markerLeft( this ,v.toInt() )
                return true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT->{
                listener?.markerRight( this ,v.toInt())
                return true
            }
            KeyEvent.KEYCODE_DPAD_CENTER->{
                listener?.markerEnter( this )
                return true
            }
        }


        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {

        veloocity = 0
        listener?.markerKeyUp()

        return super.onKeyUp(keyCode, event)
    }
}