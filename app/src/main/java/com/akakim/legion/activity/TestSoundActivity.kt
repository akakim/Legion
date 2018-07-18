package com.akakim.legion.activity

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.DragEvent
import android.view.View
import com.akakim.legion.R
import com.akakim.legion.widget.WaveFormInfo
import com.akakim.legion.widget.WaveFormThumbView
import com.akakim.legion.widget.WaveFormThumbView.OnDragThumbListener
import kotlinx.android.synthetic.main.activity_test_sound.*
import java.io.IOException
import java.io.InputStream
import java.util.*

class TestSoundActivity : AppCompatActivity() {


    lateinit var waveFormThumbView : WaveFormThumbView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_sound)


        waveFormThumbView = findViewById( R.id.waveFormThumbView )

        waveFormThumbView.onDragThumbListener = object : OnDragThumbListener{
            override fun onDrag(startTime: Double) {
                waveFormThumbView.startSecond = startTime
            }

        }
//        waveFormThumbView.onDraw
    }


//    open class ReaderTask : AsyncTask<Void, Void, WaveFormInfo>() {
//
//        val context : Context
//
//        constructor(context : Context){
//            this.context = context
//        }
//        override fun doInBackground(vararg params: Void?): WaveFormInfo {
//            var inputStream: InputStream? = null
//            try {
//
//                inputStream = context.resources.openRawResource( R.raw.waveform )
//                val data = ByteArray(inputStream!!.available())
//                inputStream!!.read(data)
//                return JSON.parseObject(data, WaveFormInfo::class.java)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            } finally {
//                try {
//                    if (inputStream != null) {
//                        inputStream.close()
//                    }
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//
//            }
//            return null        }
//    }
}
