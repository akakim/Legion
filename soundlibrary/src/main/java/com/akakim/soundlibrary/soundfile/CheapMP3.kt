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

package com.akakim.soundlibrary.soundfile

import java.util.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-14
 * CheapMP3 represents an MP3 file by doing a "cheap" scan of the file,
 * parsing the frame headers only and getting an extremely rough estimate
 * of the volume level of each frame.
 *
 * Modified by Anna Stępień <anna.stepien@semantive.com>
 */

open class CheapMP3 : CheapSoundFile{
    companion object {

        val BITRATE_MPEG1_L3 = arrayOf(
                0,  32,  40,  48,  56,  64,  80,  96,
                112, 128, 160, 192, 224, 256, 320,  0
        )

        val BITRATE_MPEG2_L3 = arrayOf(
                0,   8,  16,  24,  32,  40,  48,  56,
                64,  80,  96, 112, 128, 144, 160, 0
        )


        fun getFactory() : Factory{
            return object :Factory{
                override fun create(): CheapSoundFile {
                    return CheapMP3()

                }

                override fun getSupportExtensions(): Array<String> {
                    return arrayOf("mp3")
                }
            }
        }
    }

    constructor():super()

    // 프레임 데이터가 표현되는 멤버 변수

    var numFrams            :  Int = 0
    lateinit var frameGains : IntArray
    var fileSize            : Int = 0
    var avgBitRate          : Int = 0
    var globalSampleRate    : Int = 0
    var globalChannels      : Int = 0

    // 초기화 중 사용되는 맴버들
    var maxFrames : Int = 0
    var bitrateSum : Int = 0
    var minGain         : Int = 0
    var maxGain : Int = 0

    fun getSamplesPerFrame() : Int { return 1152}

    fun getFileType() : String { return "MP3"}


}

