package com.example.rpgapp

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs


interface IGestureView {
    fun onSwipeDown()
    fun onSwipeUp()
    fun onSwipeLeft()
    fun onSwipeRight()
}

open class OnSwipeTouchListener(ctx:Context) : View.OnTouchListener, IGestureView {
    private val gestureDetector: GestureDetector = GestureDetector(ctx, MyGestureRecognizer(this))

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(p1);
    }

    override fun onSwipeDown() {
        TODO("Not yet implemented")
    }

    override fun onSwipeUp() {
        TODO("Not yet implemented")
    }

    override fun onSwipeLeft() {
        TODO("Not yet implemented")
    }

    override fun onSwipeRight() {
        TODO("Not yet implemented")
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