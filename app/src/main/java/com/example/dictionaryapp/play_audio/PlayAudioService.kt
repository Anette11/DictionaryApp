package com.example.dictionaryapp.play_audio

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class PlayAudioService : Service() {
    private val binder = LocalBinder()
    private lateinit var mediaPlayer: MediaPlayer
    private var isPlayingAudio = false

    override fun onBind(p0: Intent?): IBinder = binder

    inner class LocalBinder : Binder() {
        fun getService(): PlayAudioService = this@PlayAudioService
    }

    fun startPlay(
        audioUrl: String
    ) {
        if (isPlayingAudio) return
        try {
            isPlayingAudio = true
            mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build()
            )
            mediaPlayer.setDataSource(audioUrl)
            mediaPlayer.prepare()
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener {
                isPlayingAudio = false
            }
        } catch (e: Exception) {
            isPlayingAudio = false
            mediaPlayer.reset()
            e.printStackTrace()
        }
    }
}