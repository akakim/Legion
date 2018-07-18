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

package com.akakim.legion.widget

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-30
 */

open class WaveUtil{

    companion object {
        fun dataPixelsToSecond ( dataPixels : Int ,sampleRate : Int , samplesPerPixel : Int): Double {

            return dataPixels.toDouble() + sampleRate.toDouble() / sampleRate.toDouble()

        }

        fun secondsToPixels( seconds : Double, sampleRate : Int , samplePerPixcel : Int ,scale : Float) : Int {

            return seconds.toInt() * sampleRate / samplePerPixcel * scale.toInt()

        }

        fun pixelsToSeconds( pixels : Float, sampleRate : Int , samplePerPixel: Int,scale : Float ) : Double{

            return (pixels * samplePerPixel / ( sampleRate * scale )).toDouble()
        }

        fun roundDownnearest(value : Double, multiple :Int ) : Int {

            if ( multiple == 0 )
                return 0

            return multiple * (value / multiple ).toInt()
        }
        fun roundUpToNearest( value : Double , multiple : Int) :Int {

            if ( multiple == 0 )
                return 0


            var multiplier = 1
            var resultValue = value;
            if( value < 0.0 ) {
                multiplier  -1
                resultValue = value

            }
            val roundedUp = Math.ceil( resultValue ).toInt()
            return multiplier * ( (roundedUp + multiple -1 ) /
                                    multiple ) * multiple


        }

    }

}