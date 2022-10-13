package com.example.dictionaryapp.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.dictionaryapp.play_audio.PlayAudioService
import com.example.dictionaryapp.ui.components.main_screen.MainScreen
import com.example.dictionaryapp.ui.theme.DictionaryAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var playAudioService: PlayAudioService
    private var isBound = false
    val playAudio = MutableSharedFlow<String>()

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(
            componentName: ComponentName?,
            iBinder: IBinder?
        ) {
            val binder = iBinder as PlayAudioService.LocalBinder
            playAudioService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(
            componentName: ComponentName?
        ) {
            isBound = false
        }
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            playAudio.collect { audioUrl ->
                if (isBound) playAudioService.startPlay(audioUrl)
            }
        }

        setContent {
            DictionaryAppTheme {
                MainScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, PlayAudioService::class.java).also {
            bindService(it, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(serviceConnection)
        isBound = false
    }
}