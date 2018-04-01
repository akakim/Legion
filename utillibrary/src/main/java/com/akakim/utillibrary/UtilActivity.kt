package com.akakim.utillibrary

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class UtilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_util)

        Log.d(UtilActivity::javaClass.name,"app > UtilActivity ... " )
    }
}
