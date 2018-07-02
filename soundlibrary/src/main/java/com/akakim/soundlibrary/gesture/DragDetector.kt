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

package com.akakim.soundlibrary.gesture

import android.content.Context
import android.graphics.RectF
import android.view.MotionEvent
import android.view.ViewConfiguration

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-30
 */

open class DragDetector{

    companion object {
        val INVALID_POINTER_ID = -1
    }

    val touchSlop  : Double
    var isDragging : Boolean                            = false
    var lastTouchX : Double                             = 0.0
    var lastTouchY : Double                             = 0.0
    var activePointerId                                 = INVALID_POINTER_ID
    var activePointerIndex                              = 0
    var onDragGestureListener : OnDragGestureListener?  = null

    val enableRect = RectF()

    constructor(context : Context?, onDragGestureListener: OnDragGestureListener?) {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop.toDouble()
        this.onDragGestureListener = onDragGestureListener
    }

    /**
     *
     */
    fun getActiveX(ev : MotionEvent) : Double {
        try {
            return ev.getX( activePointerIndex).toDouble()
        }catch ( e : Exception ){
            return ev.x.toDouble()
        }
    }

    fun getActiveY(ev : MotionEvent ): Double{
        try{
            return ev.getY( activePointerIndex ).toDouble()
        }catch (e : Exception ){
            return ev.y.toDouble()
        }
    }

    fun onTouchEvent( ev : MotionEvent ) : Boolean {
        val action  = ev.actionMasked

        onTouchActivePointer( action ,ev )
        onTouchDragEvent(action, ev )
        return true
    }


    private fun onTouchActivePointer( action : Int , ev :MotionEvent){
        when (action ) {
            MotionEvent.ACTION_DOWN -> {
                activePointerId = ev.getPointerId( 0 )
            }
            MotionEvent.ACTION_CANCEL , MotionEvent.ACTION_UP-> {
                activePointerId = INVALID_POINTER_ID
            }
            MotionEvent.ACTION_POINTER_UP ->{

                val pointerIndex = ev.actionIndex
                val pointerId = ev.getPointerId( pointerIndex )

                if( pointerId == activePointerId ){
                    val newPointerIndex : Int

                    // TODO : 엘비스 식으로 바꾸기
                    if( pointerIndex == 0 ) {
                        newPointerIndex = 1
                    }else {
                        newPointerIndex = 0
                    }

                    activePointerId = ev.getPointerId( newPointerIndex )
                    lastTouchX = ev.getX( newPointerIndex ).toDouble()
                    lastTouchY = ev.getY( newPointerIndex ).toDouble()
                }
            }

        }

        // TODO : if
        if( activePointerId != INVALID_POINTER_ID ){
            activePointerIndex = ev.findPointerIndex(
                activePointerId
            )
        }else {
            activePointerIndex = ev.findPointerIndex(
                0)
        }
    }

    fun onTouchDragEvent(action : Int , ev : MotionEvent ){
        when (action){
            MotionEvent.ACTION_DOWN->{
                lastTouchX = getActiveX( ev )
                lastTouchY = getActiveY( ev )

                isDragging = false
            }
            MotionEvent.ACTION_MOVE ->{


                val dx = getActiveX( ev ) - lastTouchX
                val dy = getActiveY ( ev ) - lastTouchY

                if( isDragging){
                    onDragGestureListener?.onDrag( dx , dy)

                }else {
                    isDragging =
                            enableRect.contains( lastTouchX.toFloat(), lastTouchY.toFloat() ).and(
                                    Math.sqrt( (dx * dx) + ( dy * dy ) ) >= touchSlop
                            )
                }

            }

        }
    }

    open fun setEnableRect( left : Float , top : Float, right : Float , bottom : Float){
        enableRect.set( left ,top,right,bottom )
    }
}