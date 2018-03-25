package com.akakim.utillibrary.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-17
 */


class SharedPreferenceUtil {

    companion object {
        val PREF_HIGH_QUALLITY  = "prefHighQuality"
        open fun setPrefHighQuality(context : Context, isEnabled : Boolean ){


            val prefer = PreferenceManager.getDefaultSharedPreferences( context )

            val editor = prefer.edit()
            editor.putBoolean( PREF_HIGH_QUALLITY , isEnabled )
            editor.apply()

        }

        open fun getPrefHightQuality(context : Context) : Boolean {
            val prefer = PreferenceManager.getDefaultSharedPreferences( context )
            return prefer.getBoolean( PREF_HIGH_QUALLITY , false )
        }


    }

}