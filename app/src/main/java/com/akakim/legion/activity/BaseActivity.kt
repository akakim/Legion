package com.akakim.legion.activity

import android.support.v7.app.AppCompatActivity
//import com.akakim.utillibrary.database.DBHelper
import android.os.Bundle
import android.support.v4.app.Fragment
import java.util.*


open class BaseActivity : AppCompatActivity() {

    protected var currentFragmentTAG :String = ""


//    protected val permissionList = StringArrayOf(
//            android.Manifest.permission.READ_EXTERNAL_STORAGE,
//            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//            android.Manifest.permission.RECORD_AUDIO
//    )

    protected val permissionList = arrayOf(

            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.RECORD_AUDIO
    )

    protected val requestCode = 1000

}
