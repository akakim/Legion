package com.akakim.legion.activity

import android.media.MediaPlayer
import android.media.session.MediaController
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.SeekBar
import com.akakim.legion.R
import com.akakim.legion.data.RecordItem
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.ExtractorMediaSource.EventListener
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.activity_player.*
import java.io.IOException
import java.util.*

class PlayerActivity : AppCompatActivity(),SeekBar.OnSeekBarChangeListener,View.OnClickListener,EventListener {




    val musicHandler = object : Handler(){
        override fun sendMessageAtTime(msg: Message?, uptimeMillis: Long): Boolean {
            return super.sendMessageAtTime(msg, uptimeMillis)
        }

        override fun dispatchMessage(msg: Message?) {
            super.dispatchMessage(msg)
        }

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
        }

        override fun getMessageName(message: Message?): String {
            return super.getMessageName(message)
        }

        override fun toString(): String {
            return super.toString()
        }
    }
    lateinit var getMediaItem : RecordItem
    lateinit var mediaPlayer : MediaPlayer

    lateinit var exoPlayer: ExoPlayer


    var playWhenReady : Boolean = false



    override fun onClick(v: View?) {


        when (v?.id){
            R.id.btnPrevSeek->{

            }
            R.id.btnPlay ->{

            }
            R.id.btnPause->{

            }
            R.id.btnNextSeek->{

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_player)


        seekBar.setOnSeekBarChangeListener( this )

//        seekBar.setOnHoverListener()

        btnPrevSeek.setOnClickListener( this )
        btnPlay.setOnClickListener( this )
        btnPause.setOnClickListener( this )
        btnNextSeek.setOnClickListener( this )

        getMediaItem = intent.getParcelableExtra<RecordItem>( PLAY_ITEM )






    }

    fun initializePlayer(){

        val initPlayer = ExoPlayerFactory.newSimpleInstance(

                DefaultRenderersFactory(this),
                DefaultTrackSelector(),
                DefaultLoadControl()


        )
        exoPlayer = initPlayer


            videoView.player = initPlayer
            exoPlayer.playWhenReady = playWhenReady


            val uri = Uri.parse( "android.resource://"+packageName+"/raw/second_run.mp3")

            val mediaSource = buildMediaSource( uri )

        exoPlayer.prepare(mediaSource,true,false)

    }


    fun buildMediaSource( uri : Uri ): MediaSource {


        return ExtractorMediaSource(
                uri,
                DefaultDataSourceFactory(this,"second_run"),
                DefaultExtractorsFactory(),
                null,
                this
        )
    }
    override fun onDestroy() {
        super.onDestroy()

        if ( musicHandler != null ){
            musicHandler.removeCallbacksAndMessages( null )
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onLoadError(error: IOException?) {

        if( error == null ){
            Log.e( javaClass.simpleName,"IOException is NPE, Unexpected")
        }else {
            error?.printStackTrace()
        }
    }

    companion object {

        val PLAY_ITEM = "playItem"

    }

}
