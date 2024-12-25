package dev.tomco.a25a_10357_l07

import android.app.Application
import dev.tomco.a25a_10357_l07.utilities.BackgroundMusicPlayer

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        BackgroundMusicPlayer.init(this)
        BackgroundMusicPlayer.getInstance().setResourceId(R.raw.background_music)
    }

    override fun onTerminate() {
        super.onTerminate()
        BackgroundMusicPlayer.getInstance().stopMusic()
    }
}