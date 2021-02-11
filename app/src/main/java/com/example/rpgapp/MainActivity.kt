package com.example.rpgapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat

class MainActivity : AppCompatActivity() {
    private lateinit var myGestureRecognizer: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.setOnTouchListener(object: OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeDown() {
                Log.d("DEBUG","User swiped down!")
                Toast.makeText(this@MainActivity, "User swiped down!",
                        Toast.LENGTH_LONG).show();
            }

            override fun onSwipeUp() {
                Toast.makeText(this@MainActivity, "User swiped up!",
                        Toast.LENGTH_LONG).show();
            }

            override fun onSwipeLeft() {
                Toast.makeText(this@MainActivity, "User swiped left!",
                        Toast.LENGTH_LONG).show();
            }

            override fun onSwipeRight() {
                Toast.makeText(this@MainActivity, "User swiped right!",
                        Toast.LENGTH_LONG).show();
            }
        })
//        this.myGestureRecognizer = GestureDetectorCompat(this, MyGestureRecognizer(this))
        setContentView(R.layout.activity_main)
    }


}