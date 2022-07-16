package com.wingify.cricketscorer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.wingify.cricketscorer.R

class BowlerActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var overs: EditText
    lateinit var wickets: EditText
    lateinit var maidens: EditText
    lateinit var extras:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bowler)

        name = findViewById(R.id.name)
        overs = findViewById(R.id.overs)
        wickets = findViewById(R.id.wickets)
        maidens = findViewById(R.id.maidens)
        extras = findViewById(R.id.extras)
    }
}