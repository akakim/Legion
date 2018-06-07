package com.akakim.legion.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-07
 */

open class MediaPlayerService: Service() {


    lateinit var mediaPlayer : MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()

    }
}
