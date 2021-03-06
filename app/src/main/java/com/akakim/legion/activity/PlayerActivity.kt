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
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Toast
import com.akakim.legion.R
import com.akakim.legion.data.RecordItem
import com.akakim.legion.util.AudioEventListener
import com.akakim.legion.util.MusicHandler
import com.akakim.legion.util.PlayerEventListener
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.decoder.DecoderCounters
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource

import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import kotlinx.android.synthetic.main.activity_player.*
import java.io.IOException
import java.util.*


/*
* https://github.com/google/ExoPlayer/blob/release-v2/library/ui/src/main/res/layout/exo_playback_control_view.xml
* */
class PlayerActivity : AppCompatActivity(),
                        SeekBar.OnSeekBarChangeListener,
                        View.OnClickListener,
                        ExtractorMediaSource.EventListener{

    lateinit var musicHandler : MusicHandler
    lateinit var getMediaItem : RecordItem
    lateinit var exoPlayer: ExoPlayer
    lateinit var playerEventListener : PlayerEventListener
    lateinit var audioEventListener : AudioEventListener

    var playWhenReady : Boolean = true




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        musicHandler = MusicHandler()
        getMediaItem = intent.getParcelableExtra<RecordItem>( PLAY_ITEM )
        initializePlayer()
        playBackControllerView.show()

    }

    fun initializePlayer(){

        playerEventListener = object : PlayerEventListener{
            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
                super.onPlaybackParametersChanged(playbackParameters)
                Log.d(PlayerEventListener.tag,"onPlaybackParameter")
            }

            override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
                super.onTracksChanged(trackGroups, trackSelections)
            }

            override fun onPlayerError(error: ExoPlaybackException?) {
                super.onPlayerError(error)

                error?.printStackTrace()
            }

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                super.onPlayerStateChanged(playWhenReady, playbackState)


                if( playWhenReady){
                    Log.d( PlayerEventListener.tag,"is Ready status " + playbackState )
                }else {
                    Log.d( PlayerEventListener.tag,"is Not Ready status " + playbackState )
                }

                when (playbackState ){
                    Player.STATE_IDLE ->{
                        Log.d( PlayerEventListener.tag," STATE_IDLE ")
                    }
                    Player.STATE_BUFFERING->{
                        Log.d( PlayerEventListener.tag," STATE_BUFFERING ")
                    }
                    Player.STATE_READY->{
                        Log.d( PlayerEventListener.tag," STATE_READY ")
                    }
                    Player.STATE_ENDED->{
                        Log.d( PlayerEventListener.tag," STATE_ENDED ")
                    }
                }
            }

            override fun onLoadingChanged(isLoading: Boolean) {
                super.onLoadingChanged(isLoading)
            }

            override fun onPositionDiscontinuity() {
                super.onPositionDiscontinuity()
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                super.onRepeatModeChanged(repeatMode)
            }

            override fun onTimelineChanged(timeline: Timeline?, manifest: Any?) {
                super.onTimelineChanged(timeline, manifest)
            }
        }
        audioEventListener = object : AudioEventListener{
            override fun onAudioEnabled(counters: DecoderCounters?) {
                super.onAudioEnabled(counters)
            }

            override fun onAudioInputFormatChanged(format: Format?) {
                super.onAudioInputFormatChanged(format)
            }

            override fun onAudioTrackUnderrun(bufferSize: Int, bufferSizeMs: Long, elapsedSinceLastFeedMs: Long) {
                super.onAudioTrackUnderrun(bufferSize, bufferSizeMs, elapsedSinceLastFeedMs)
            }

            override fun onAudioSessionId(audioSessionId: Int) {
                super.onAudioSessionId(audioSessionId)
            }

            override fun onAudioDecoderInitialized(decoderName: String?, initializedTimestampMs: Long, initializationDurationMs: Long) {
                super.onAudioDecoderInitialized(decoderName, initializedTimestampMs, initializationDurationMs)
            }

            override fun onAudioDisabled(counters: DecoderCounters?) {
                super.onAudioDisabled(counters)
            }
        }

        val initPlayer = ExoPlayerFactory.newSimpleInstance(

                DefaultRenderersFactory(this),
                DefaultTrackSelector(),
                DefaultLoadControl()


        )
        initPlayer.addListener( playerEventListener )
        initPlayer.setAudioDebugListener( audioEventListener )
        initPlayer.playWhenReady = true

        exoPlayer = initPlayer

        playBackControllerView.player = initPlayer

        exoPlayer.playWhenReady = playWhenReady


        val uri = Uri.parse( "android.resource://"+packageName+"/raw/second_run")

        val rawResourceDataSou : RawResourceDataSource = RawResourceDataSource( this )
        val dataSpec : DataSpec = DataSpec(RawResourceDataSource.buildRawResourceUri( R.raw.second_run ) )

        try{

            rawResourceDataSou.open(dataSpec)

            val factory = object : DataSource.Factory{
                override fun createDataSource(): DataSource {

                    return rawResourceDataSou
                }
            }


            val mediaSource : MediaSource = ExtractorMediaSource(
                    rawResourceDataSou.uri,
                    factory,
                    DefaultExtractorsFactory(),
                    musicHandler,this
            )

            exoPlayer.prepare(mediaSource,true,false)


        }catch ( e :RawResourceDataSource.RawResourceDataSourceException ){
            e.printStackTrace()

            // TODO :
            Toast.makeText(this,"파일 재생을 실패했습니다. ",Toast.LENGTH_SHORT).show()
        }


    }


    fun buildMediaSource( uri : Uri ): MediaSource {


        return ExtractorMediaSource(
                uri,
                DefaultDataSourceFactory(this,"second_run"),
                DefaultExtractorsFactory(),
                musicHandler,
                this
        )
    }
    override fun onDestroy() {
        super.onDestroy()

        if ( musicHandler != null ){
            musicHandler.removeCallbacksAndMessages( null )

        }

        if( exoPlayer != null){
            exoPlayer.release()

        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }


    override fun onLoadError(error: IOException?) {

        if( error == null ){
            Log.e( javaClass.simpleName,"IOException is NPE, Unexpected")
        }else {
            error.printStackTrace()
        }
    }

    override fun onClick(v: View?) {

    }


    companion object {

        val PLAY_ITEM = "playItem"

    }

}
