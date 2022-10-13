package com.example.dictionaryapp.play_audio

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PlayAudioService : Service() {
    private val binder = LocalBinder()
    private var mediaPlayer: MediaPlayer? = null
    private var isPlayingAudio = false
    private var playAudioJob: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onBind(p0: Intent?): IBinder = binder

    inner class LocalBinder : Binder() {
        fun getService(): PlayAudioService = this@PlayAudioService
    }

    fun startPlay(
        audioUrl: String
    ) {
        if (isPlayingAudio) return
        playAudioJob?.cancel()
        playAudioJob = coroutineScope.launch {
            try {
                isPlayingAudio = true
                mediaPlayer = MediaPlayer()
                mediaPlayer?.setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build()
                )
                mediaPlayer?.setDataSource(audioUrl)
                mediaPlayer?.prepare()
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener {
                    isPlayingAudio = false
                }
            } catch (e: Exception) {
                isPlayingAudio = false
                mediaPlayer?.reset()
                e.printStackTrace()
            }
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        try {
            playAudioJob?.cancel()
            mediaPlayer?.stop()
            mediaPlayer?.reset()
            mediaPlayer = null
            isPlayingAudio = false
        } catch (e: Exception) {
            isPlayingAudio = false
            mediaPlayer?.reset()
            e.printStackTrace()
        }
        return super.onUnbind(intent)
    }
}