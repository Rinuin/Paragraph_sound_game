package com.example.rpgapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    private lateinit var myGestureRecognizer: GestureDetectorCompat

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
        window.decorView.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeDown() {
                Log.d("DEBUG", "User swiped down!")
                Toast.makeText(
                    this@MainActivity, "User swiped down!",
                    Toast.LENGTH_LONG
                ).show();
            }

            override fun onSwipeUp() {
                Toast.makeText(
                    this@MainActivity, "User swiped up!",
                    Toast.LENGTH_LONG
                ).show();
            }

            override fun onSwipeLeft() {
                Toast.makeText(
                    this@MainActivity, "User swiped left!",
                    Toast.LENGTH_LONG
                ).show();
            }

            override fun onSwipeRight() {
                Toast.makeText(
                    this@MainActivity, "User swiped right!",
                    Toast.LENGTH_LONG
                ).show();
            }
        })
//        this.myGestureRecognizer = GestureDetectorCompat(this, MyGestureRecognizer(this))
        setContentView(R.layout.activity_main)
    }


}