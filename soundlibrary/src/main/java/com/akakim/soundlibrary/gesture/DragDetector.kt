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

    val touchSlop : Float
    val isDragging : Boolean
    var lastTouchX : Float = 0f
    var lastTouchY : Float = 0f
    var activePointerId = INVALID_POINTER_ID
    var activePointerIndex  = 0 ;
    var onDragGestureListener : OnDragGestureListener? = null
    var isDragging

    constructor(context : Context?, onDragGestureListener: OnDragGestureListener?) {
        touchSlop = ViewConfiguration.get(context).scaledTouchSlop.toFloat()
        this.onDragGestureListener = onDragGestureListener
    }

    /**
     *
     */
    fun getActiveX(ev : MotionEvent) : Float {
        try {
            return ev.getX( activePointerIndex)
        }catch ( e : Exception ){
            return ev.x
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
                    lastTouchX = ev.getX( newPointerIndex ).toFloat()
                    lastTouchY = ev.getY( newPointerIndex ).toFloat()
                }
            }

        }

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
                lastTouchX = getActiveX( )
            }
            MotionEvent.ACTION_MOVE ->{

            }
        }
    }
}