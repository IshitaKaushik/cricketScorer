package com.wingify.cricketscorer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
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
        databaseReference = FirebaseDatabase.getInstance().reference.child("bowlers")
        insertDataInDatabase()
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