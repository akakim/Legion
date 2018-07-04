

package com.akakim.soundlibrary

import com.akakim.soundlibrary.view.WaveFormInfo

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-07-05
 */


interface TaskEndListener{

    fun onTaskEnd(info : WaveFormInfo?)

}