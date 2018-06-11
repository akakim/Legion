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

    }

    override fun onAudioInputFormatChanged(format: Format?) {

    }

    override fun onAudioTrackUnderrun(bufferSize: Int, bufferSizeMs: Long, elapsedSinceLastFeedMs: Long) {

    }

    override fun onAudioSessionId(audioSessionId: Int) {

    }

    override fun onAudioDecoderInitialized(decoderName: String?, initializedTimestampMs: Long, initializationDurationMs: Long) {

    }

    override fun onAudioDisabled(counters: DecoderCounters?) {

    }
}