package com.akakim.legion.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.annotation.AnimRes
import android.util.Log
import com.akakim.legion.activity.BaseActivity

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-10
 */


class Util{

    companion object {
        fun removeExtension(fileName : String ) : String {


            if (fileName == null ){
                throw NullPointerException("fileName is null")
            }


            if( "".equals(fileName )){
                return fileName
            }

            if( ".".contains(fileName) ){

                // 파일에 . 이 여러게가 있는 경우를 고려하여 .의 위치를 찾는다.

                val buffer      = StringBuilder ( fileName )
                val splitList   = buffer.split(".")
                val result      = buffer.removeSuffix( splitList.get( splitList.size - 1 ))




                return result.toString()
            }else {

                return fileName
            }

        }

        fun convertToDpToPixel(context: Context, dp: Int): Int {
            val density = context.resources.displayMetrics.density
            return dp * density.toInt()
        }


        // animation 효과를 주는 Util
        fun startAnimActivity(context : BaseActivity, targetActivity : BaseActivity, @AnimRes startAnim : Int, @AnimRes endAnim : Int){

            val intent = Intent(context ,targetActivity::class.java )

            context.startActivity( intent )
            context.overridePendingTransition(startAnim,endAnim )
        }
    }
}