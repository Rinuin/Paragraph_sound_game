package com.example.rpgapp

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.Charset
import kotlin.math.abs
import kotlin.system.exitProcess


interface IGestureView {
    fun onSwipeDown()
    fun onSwipeUp()
    fun onSwipeLeft()
    fun onSwipeRight()
}

open class OnSwipeTouchListener(private val ctx: Context, val tts: TextToSpeech) :
    View.OnTouchListener, IGestureView {
    private val gestureDetector: GestureDetector = GestureDetector(ctx, MyGestureRecognizer(this))
    private val csvreader: CSVReader
    private var firstTime: Boolean = true

    init {
        this.csvreader = CSVReader()
        this.csvreader.readCsvFile(
            BufferedReader(
                InputStreamReader(
                    ctx.resources.openRawResource(
                        R.raw.game_story
                    ), Charset.forName("UTF-8")
                )
            )
        )
//        tts.speak(this.csvreader.getBlockById(0)?.msg, TextToSpeech.QUEUE_ADD, null, "")
    }

    fun checkGameEnd(){
        if(this.csvreader.currentChoice == 3){
            tts.speak(
                this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                TextToSpeech.QUEUE_FLUSH,
                null,
                ""
            )
            Thread.sleep(7000)
            exitProcess(0)
        }
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(p1);
    }

    override fun onSwipeDown() {
        Toast.makeText(
            ctx, "User swiped down!",
            Toast.LENGTH_LONG
        ).show();
        if(this.firstTime){
            tts.speak(
                this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                TextToSpeech.QUEUE_ADD,
                null,
                ""
            )
            this.firstTime = false
        }
        else {
            this.csvreader.saveChoice(this.csvreader.currentChoice?.let {
                this.csvreader.getBlockById(
                    it
                )?.down
            })
            if(this.csvreader.currentChoice == -1) {
                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )

                this.csvreader.currentChoice = this.csvreader.previousChoice

                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )

            }
            else{
                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )
            }
            checkGameEnd()
        }
    }

    override fun onSwipeUp() {
        Toast.makeText(
            ctx, "User swiped up!",
            Toast.LENGTH_LONG
        ).show();
//        tts.speak("User swiped up!", TextToSpeech.QUEUE_ADD, null, "")
        if(this.firstTime){
            tts.speak(
                this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                TextToSpeech.QUEUE_ADD,
                null,
                ""
            )
            this.firstTime = false
        }
        else {
            this.csvreader.saveChoice(this.csvreader.currentChoice?.let {
                this.csvreader.getBlockById(
                    it
                )?.up
            })
            if(this.csvreader.currentChoice == -1) {
                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )

                this.csvreader.currentChoice = this.csvreader.previousChoice

                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )

            }
            else{
                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )
            }
            checkGameEnd()
        }
    }

    override fun onSwipeLeft() {
        Toast.makeText(
            ctx, "User swiped left!",
            Toast.LENGTH_LONG
        ).show();
        if(this.firstTime){
            tts.speak(
                this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                TextToSpeech.QUEUE_ADD,
                null,
                ""
            )
            this.firstTime = false
        }
        else {
            this.csvreader.saveChoice(this.csvreader.currentChoice?.let {
                this.csvreader.getBlockById(
                    it
                )?.left
            })
            if(this.csvreader.currentChoice == -1) {
                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )

                this.csvreader.currentChoice = this.csvreader.previousChoice

                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )

            }
            else{
                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )
            }
            checkGameEnd()
        }
    }

    override fun onSwipeRight() {
        Toast.makeText(
            ctx, "User swiped right!",
            Toast.LENGTH_LONG
        ).show();
        if(this.firstTime){
            tts.speak(
                this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                TextToSpeech.QUEUE_ADD,
                null,
                ""
            )
            this.firstTime = false
        }
        else {
            this.csvreader.saveChoice(this.csvreader.currentChoice?.let {
                this.csvreader.getBlockById(
                    it
                )?.right
            })
            if(this.csvreader.currentChoice == -1) {
                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )

                this.csvreader.currentChoice = this.csvreader.previousChoice

                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )

            }
            else{
                tts.speak(
                    this.csvreader.currentChoice?.let { this.csvreader.getBlockById(it)?.msg },
                    TextToSpeech.QUEUE_ADD,
                    null,
                    ""
                )
            }
            checkGameEnd()
        }
    }

}


class MyGestureRecognizer(private var IGestureView: IGestureView) :
    GestureDetector.SimpleOnGestureListener() {

    private val SWIPE_THRESHOLD = 100
    private val SWIPE_VELOCITY_THRESHOLD = 100

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        IGestureView.onSwipeDown()
        return super.onDoubleTap(e)
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        var result = false
        try {
            val diffY = e2!!.y - e1!!.y
            val diffX = e2.x - e1.x
            if (abs(diffX) > abs(diffY)) {
                if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        IGestureView.onSwipeRight()
                    } else {
                        IGestureView.onSwipeLeft()
                    }
                    result = true
                }
            } else if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    IGestureView.onSwipeDown()
                } else {
                    IGestureView.onSwipeUp()
                }
                result = true
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return result
//        return super.onFling(e1, e2, velocityX, velocityY)
    }
}