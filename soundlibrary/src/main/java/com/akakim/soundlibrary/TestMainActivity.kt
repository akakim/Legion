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

package com.akakim.soundlibrary

import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.akakim.soundlibrary.view.WaveForamInfo
import com.akakim.soundlibrary.view.WaveFormThumbView
import kotlinx.android.synthetic.main.activity_test_main.*
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.util.*

class TestMainActivity : AppCompatActivity(),WaveFormThumbView.OnDragThumbListener {
    override fun onDrag(startTime: Double) {


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_test_main )

        waveFormThumbView.onDragThumbListener =  this
    }

    class ReaderTask : AsyncTask<Void, Void, WaveForamInfo>() {

        val context : Context
        constructor( context : Context):super(){
            this.context = context
        }


        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Void?): WaveForamInfo {

            var inputStream : InputStream? = null

            try {
                inputStream = context.resources.openRawResource(R.raw.waveform)
                val data = ByteArray(inputStream.available())

                inputStream.read(data)

//                return JSONObject(data, )
            }catch ( e : Exception ){
                e.printStackTrace()
            }finally {
                try{
                    inputStream?.close()
                }catch (e : IOException ){
                    e.printStackTrace()
                }
            }
        }

    }
}
