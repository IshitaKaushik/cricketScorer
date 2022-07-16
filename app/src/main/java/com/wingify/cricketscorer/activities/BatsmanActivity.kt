package com.wingify.cricketscorer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.wingify.cricketscorer.R

class BatsmanActivity : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var ballsPlayed: EditText
    lateinit var runsScored:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batsman)

        name = findViewById(R.id.name)
        ballsPlayed = findViewById(R.id.balls_played)
        runsScored = findViewById(R.id.runs_scored)

    }
}