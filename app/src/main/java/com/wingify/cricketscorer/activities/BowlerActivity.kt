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
import com.wingify.cricketscorer.models.Bowler

class BowlerActivity : AppCompatActivity() {
    lateinit var name: EditText
    lateinit var overs: EditText
    lateinit var wickets: EditText
    lateinit var maidens: EditText
    lateinit var extras: EditText
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bowler)

        name = findViewById(R.id.name)
        overs = findViewById(R.id.overs)
        wickets = findViewById(R.id.wickets)
        maidens = findViewById(R.id.maidens)
        extras = findViewById(R.id.extras)
        findViewById<Button>(R.id.add_bowler).setOnClickListener {
            if (validateData()) {
                databaseReference = FirebaseDatabase.getInstance().reference.child("bowlers")
                insertDataInDatabase()
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun validateData(): Boolean {
        if (name.text.toString() == "") {
            Toast.makeText(this, "Please enter bowler name", Toast.LENGTH_SHORT).show()
            return false
        } else if (overs.text.toString() == "") {
            Toast.makeText(this, "Please enter the overs of bowler", Toast.LENGTH_SHORT).show()
            return false
        } else if (wickets.text.toString() == "") {
            Toast.makeText(
                this,
                "Please enter the wickets of bowler(if no wickets taken please enter 0)",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (maidens.text.toString() == "") {
            Toast.makeText(this, "Please enter the maiden overs of bowler", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    private fun insertDataInDatabase() {
        val bowler = Bowler(
            name.text.toString(),
            overs.text.toString(),
            wickets.text.toString(),
            maidens.text.toString(),
            extras.text.toString()
        )
        databaseReference.push().setValue(bowler)
    }
}