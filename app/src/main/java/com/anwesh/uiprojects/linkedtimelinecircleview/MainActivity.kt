package com.anwesh.uiprojects.linkedtimelinecircleview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.timelinecircleview.TimelineCircleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TimelineCircleView.create(this)
    }
}
