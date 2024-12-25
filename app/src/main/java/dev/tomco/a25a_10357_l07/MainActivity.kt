package dev.tomco.a25a_10357_l07

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import dev.tomco.a25a_10357_l07.interfaces.TiltCallback
import dev.tomco.a25a_10357_l07.utilities.BackgroundMusicPlayer
import dev.tomco.a25a_10357_l07.utilities.SingleSoundPlayer
import dev.tomco.a25a_10357_l07.utilities.TiltDetector

class MainActivity : AppCompatActivity() {

    private lateinit var sensors_BTN_boom: MaterialButton

    private lateinit var sensors_LBL_tiltX: MaterialTextView

    private lateinit var sensors_LBL_tiltY: MaterialTextView

    private lateinit var tiltDetector: TiltDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        initViews()
        initTiltDetector()
    }

    private fun initViews() {
        sensors_BTN_boom.setOnClickListener {
            var ssp: SingleSoundPlayer = SingleSoundPlayer(this)
            ssp.playSound(R.raw.boom)
        }
    }

    private fun findViews() {
        sensors_LBL_tiltX = findViewById(R.id.sensors_LBL_tiltX)
        sensors_LBL_tiltY = findViewById(R.id.sensors_LBL_tiltY)
        sensors_BTN_boom = findViewById(R.id.sensors_BTN_boom)
    }

    private fun initTiltDetector() {
        tiltDetector = TiltDetector(
            context = this,
            tiltCallback = object : TiltCallback {
                override fun tiltX() {
                    tiltDetector.tiltCounterX.toString().also { sensors_LBL_tiltX.text = it }
                }

                override fun tiltY() {
                    tiltDetector.tiltCounterY.toString().also { sensors_LBL_tiltY.text = it }
                }

            }
        )
    }

    override fun onResume() {
        super.onResume()
        tiltDetector.start()
        BackgroundMusicPlayer.getInstance().playMusic()
    }

    override fun onPause() {
        super.onPause()
        tiltDetector.stop()
        BackgroundMusicPlayer.getInstance().pauseMusic()
    }
}