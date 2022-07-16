package com.wingify.cricketscorer.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.wingify.cricketscorer.R
import com.wingify.cricketscorer.models.Batsman

class BatsmanActivity : AppCompatActivity() {
    lateinit var name:EditText
    lateinit var ballsPlayed: EditText
    lateinit var runsScored:EditText
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batsman)

        name = findViewById(R.id.name)
        ballsPlayed = findViewById(R.id.balls_played)
        runsScored = findViewById(R.id.runs_scored)
        findViewById<Button>(R.id.add_batsman).setOnClickListener {
            if (validateData()) {
                databaseReference = FirebaseDatabase.getInstance().reference.child("batsmen")
                insertDataInDatabase()
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validateData(): Boolean {
        if (name.text.toString() == "") {
            Toast.makeText(this, "Please enter batsman name", Toast.LENGTH_SHORT).show()
            return false
        } else if (ballsPlayed.text.toString() == "") {
            Toast.makeText(this, "Please enter the balls played by batsman", Toast.LENGTH_SHORT).show()
            return false
        } else if (runsScored.text.toString() == "") {
            Toast.makeText(
                this,
                "Please enter the runs scored by batsman",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun insertDataInDatabase() {
        val batsman = Batsman(
            name.text.toString(),
            ballsPlayed.text.toString(),
            runsScored.text.toString()
        )
        databaseReference.push().setValue(batsman)
    }

}