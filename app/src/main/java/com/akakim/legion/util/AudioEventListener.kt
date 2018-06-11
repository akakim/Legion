package com.akakim.legion.util

import com.google.android.exoplayer2.Format
import com.google.android.exoplayer2.audio.AudioRendererEventListener
import com.google.android.exoplayer2.decoder.DecoderCounters

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-12
 */

interface AudioEventListener : AudioRendererEventListener{

    companion object {
        val tag = "AudioEventListener"
    }


    override fun onAudioEnabled(counters: DecoderCounters?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAudioInputFormatChanged(format: Format?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAudioTrackUnderrun(bufferSize: Int, bufferSizeMs: Long, elapsedSinceLastFeedMs: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAudioSessionId(audioSessionId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAudioDecoderInitialized(decoderName: String?, initializedTimestampMs: Long, initializationDurationMs: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAudioDisabled(counters: DecoderCounters?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}