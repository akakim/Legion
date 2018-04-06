package com.akakim.utillibrary.service

import android.app.Service
import android.content.Intent
import android.media.MediaRecorder
import android.os.IBinder
import com.akakim.utillibrary.database.DBHelper
import com.akakim.utillibrary.util.SharedPreferenceUtil
import java.io.File
import java.io.IOError

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-15
 */


open class RecordingService : Service() {

    companion object {

        private val TAG = "RecordingService"


    }

    var fileName : String = ""
    var filePath : String = ""

    var recorder : MediaRecorder? = null
    var database : DBHelper? = null


    override fun onCreate() {
        super.onCreate()

        database = DBHelper(applicationContext )
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }





    interface OnTimerChangedListener{
        fun onTImerChanged( seconds : Int )
    }
}

//    : Service() {
//
//
//    val TAG = "RecordingService"
//
//
//
//    var fileName : String? = null
//    var filePath : String? = null
//    var recorder : MediaRecorder?
//    var database : DBHelper? = null
//    var startTimeMillis : Long = 0
//    var elipse
////    var
//
//
//    override fun onBind(intent: Intent?): IBinder {
//
//        return @onBind null
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//
//        database = DBHelper( applicationContext )
//    }
//
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        return super.onStartCommand(intent, flags, startId)
//
//        startRecording()
//        return START_STICKY
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//
//        recorder?.apply {
//            stop()
//            release()
//        }
//
//
//    }
//
//    open fun startRecording(){
//        // set file path
//
////        val filename =
////        var file = File(baseContext.cacheDir,
//
//
//        recorder = MediaRecorder()
//        recorder?.apply {
//
//                setAudioSource( MediaRecorder.AudioSource.MIC )
////            setAudioSource( )
//                setOutputFormat( MediaRecorder.OutputFormat.MPEG_4)
//                setOutputFile (filePath)
//                setAudioEncoder( MediaRecorder.AudioEncoder.AAC )
//                setAudioChannels( 1 )
//
//            if ( SharedPreferenceUtil.getPrefHightQuality( this )){
//
//                setAudioSamplingRate( 44)
//                setAudioSamplingRate( 44100 )
//
//            }
//
//        }?.run {
//            try {
//                prepare()
//                start()
//                startTimeMillis = System.currentTimeMillis()
//
//            }catch ( e : IOError ) {
//                e.printStackTrace()
//            }
//        }
//
//
//
//
//    }
//
//    // TODO : control flow
//    fun setFileNameAndPath(){
//
////        for ( Int k 0 .. )
//    }
//
//    open fun stopRecording(){
//        recorder?.apply{
//            stop()
//            release()
//        }
//
//    }
//
//
//    fun startTimer(){
//
//        time
//    }
//}