package com.akakim.legion.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.akakim.legion.MainActivity
import com.akakim.legion.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),View.OnClickListener{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener { this }
    }

    override fun onClick(v: View?) {

        when (v!!.id){
            R.id.btnLogin -> {

                val i = Intent ( this , classLoader.loadClass(  RecordingActivity::class.java.canonicalName ))
                startActivity(i )
            }

        }
    }
}
