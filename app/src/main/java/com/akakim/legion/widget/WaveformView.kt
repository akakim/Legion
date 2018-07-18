

package com.akakim.legion.widget

/*
 *
 *  * Copyright (C) 2008 Google Inc.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.view.View

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-14
 */

open class WaveformView : View {

    interface WaveformViewListener{
        fun waveformTouchStart(x : Float )
        fun waveformTouchMove(x : Float )
        fun waveformTouchEnd()
        fun waveformTouchFling(x : Float )
        fun waveformDraw()
        fun waveformZoomIn()
        fun waveformZoomOut()
    }


    //Colors

    protected lateinit var gridPaint                : Paint
    protected lateinit var selectedLinePaint        : Paint
    protected lateinit var unSelectedLinePaint      : Paint
    protected lateinit var unSelectedBkgndLinePaint : Paint
    protected lateinit var borderLinePaint          : Paint
    protected lateinit var playbackLinePaint        : Paint
    protected lateinit var timecodePaint            : Paint

//    protected CheapSound

//    protected

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr,0){


        focusable = NOT_FOCUSABLE


        gridPaint = Paint()
        gridPaint.isAntiAlias = false
//        grid

    }

    companion object {
        val TAG = "WaveformView"
    }




}