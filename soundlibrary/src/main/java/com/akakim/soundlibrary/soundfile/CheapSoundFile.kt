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

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-14
 *
 * 이 클래스는 몇몇 하위클래스를 가진다. 하위클래스는 cheap 스캔을 다양한 파일
 * 양식에 대해서 한다. 파싱은 상위 단계의 구조 때문에, 가능하면 적게 한다.
 * 그리고 각 프레임에 대한 volumn level을 측정하는 것을 얻는다. ?
 * 각 하위 클래스는 다음 이 가능하다.
 * - 음원 파일 열기
 * - 프레임의 갯수 그리고 샘플 rate 을 반환
 * - 각 프레임의 volumne 수준에 대한 대략적인 것 을 반환
 *
 * 프레임은 적어도 1ms 보단 작지않고  100ms 보다는 크지 않지 않다.
 * CheapSoundFile is the parent class of several subclasses that each
 * do a "cheap" scan of various sound file formats, parsing as little
 * as possible in order to understand the high-level frame structure
 * and get a rough estimate of the volume level of each frame.  Each
 * subclass is able to:
 *  - open a sound file
 *  - return the sample rate and number of frames
 *  - return an approximation of the volume level of each frame
 *
 * A frame should represent no less than 1 ms and no more than 100 ms of
 * audio.  This is compatible with the native frame sizes of most audio
 * file formats already, but if not, this class should expose virtual
 * frames in that size range.
 */


open class CheapSoundFile {
    interface ProgressListener{

        fun reportProgress( fractionComplete : Double )
    }

    interface Factory{
        fun create() : CheapSoundFile
        fun getSupportExtensions (): Array<String>
    }

    companion object {

        val subclassFactories = arrayOf(
                CheapMP3.getFactory()
        )

        open var supportedExtnesions = ArrayList<String>()
        open var extensionMap = HashMap<String,Factory>().apply {

            for( f in subclassFactories ){
                for( extension in f.getSupportExtensions() ) {
                    supportedExtnesions.add(extension)
                    put(extension,f)
                }
            }
        }


    }
}