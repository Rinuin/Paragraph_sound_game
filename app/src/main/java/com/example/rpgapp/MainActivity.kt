package com.example.rpgapp

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import java.io.*
import java.nio.charset.Charset
import android.util.*
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var myGestureRecognizer: GestureDetectorCompat
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val csvreader = CSVReader()
        csvreader.readCsvFile(
            BufferedReader(
                InputStreamReader(
                    applicationContext.resources.openRawResource(
                        R.raw.game_story
                    ), Charset.forName("UTF-8")
                )
            )
        )
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this, this)
        window.decorView.setOnTouchListener(OnSwipeTouchListener(this@MainActivity, tts!!))
//        this.myGestureRecognizer = GestureDetectorCompat(this, MyGestureRecognizer(this))
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == MY_DATA_CHECK_CODE) {
        if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
            // success, create the TTS instance
            tts = TextToSpeech(this, this)
        } else {
            // missing data, install it
            val installIntent = Intent()
            installIntent.action = TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA
            startActivity(installIntent)
        }
//        }
    }


    override fun onInit(p0: Int) {
        if (p0 == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }
        } else {
            Log.e("TTS", "Initilization Failed!")
            Log.e("TTS", "tts status: $p0")
        }
    }

//    fun loadBlockFromFile():Int?{
//        return BufferedReader(
//            InputStreamReader(
//                applicationContext.resources.openRawResource(R.raw.saved_game),
//                Charset.forName("UTF-8")
//            )
//        ).readLine().toInt()
//    }

//    fun saveBlockToFile(id: Int){
//        val fw = FileWriter(applicationContext.resources.R.raw.saved_game))
//        BufferedStreamWriter(
//            OutputStreamWriter(applicationContext.resources.openRawResource(R.raw.saved_game)
//            )
//        )
//    }


}