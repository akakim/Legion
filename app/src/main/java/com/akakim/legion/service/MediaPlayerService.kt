package com.akakim.legion.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-07
 */

open class MediaPlayerService: Service() {


    lateinit var mediaPlayer : MediaPlayer

    lateinit var exoPlayer: ExoPlayer
    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()

    }


    override fun onDestroy() {
        super.onDestroy()
        if( mediaPlayer != null){
            mediaPlayer.release()
//            mediaPlayer = null
        }
    }

    fun initializePlayer(){
//        exoPlayer = ExoPlayerFactory.newInstance(
//                DefaultRenderersFactory ( this ),
//        )

    }

    companion object {
        val STATE_IDLE= "idle"
        val STATE_INIT= "init"
        val STATE_PREPARE= "prepare"
        val STATE_STARTED="started"
        val STATE_PAUSED="paused"
        val STATE_STOPPED="stop"
        val STATE_PLAYBACK_COMPLETED="playbackComplete"
    }
}
