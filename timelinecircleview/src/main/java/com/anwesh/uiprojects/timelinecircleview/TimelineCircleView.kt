package com.anwesh.uiprojects.timelinecircleview

/**
 * Created by anweshmishra on 24/08/18.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Canvas
import android.graphics.Paint
import android.content.Context
import android.graphics.Color

val nodes : Int = 5

fun Canvas.drawTCNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.strokeWidth = Math.min(w, h) / 50
    paint.strokeCap = Paint.Cap.ROUND
    paint.color = Color.parseColor("#43A047")
    val gap : Float = h / nodes
    var sc1 : Float = Math.min(0.5f, scale) * 2
    val sc2 : Float = Math.min(0.5f, Math.min(scale - 0.5f, 0f)) * 2
    val r : Float = gap / 4
    val index : Float = 1f - 2 * (i % 2)

    save()
    translate(w / 2, gap * i)
    save()
    translate((w/2 + r) * index * (1 - sc1) , 0f)
    drawCircle(0f, 0f, r, paint)
    restore()
    drawLine(0f, 0f, 0f, gap * scale, paint)
    restore()
}

class TimelineCircleView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var prevScale : Float = 0f, var dir : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            this.scale += 0.05f * this.dir
            if (Math.abs(this.scale - this.prevScale) > 1) {
                this.scale = this.prevScale + this.dir
                this.dir = 0f
                this.prevScale = this.scale
                cb(this.prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (this.dir == 0f) {
                this.dir = 1 - 2 * this.prevScale
                cb()
            }
        }
    }

    data class Animator (var view : View, var animated : Boolean = false) {

        fun animate(cb : () -> Unit) {
            if (animated) {
                cb()
                try {
                    Thread.sleep(50)
                    view.invalidate()
                } catch (ex : Exception) {

                }
            }
        }

        fun start() {
            if (!animated) {
                animated = true
                view.postInvalidate()
            }
        }

        fun stop() {
            if (animated) {
                animated = false
            }
        }
    }
}