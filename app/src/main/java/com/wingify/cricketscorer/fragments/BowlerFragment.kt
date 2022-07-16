package com.wingify.cricketscorer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.wingify.cricketscorer.R
import com.wingify.cricketscorer.models.Bowler

class BowlerFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference
    private val bowlerData: ArrayList<Bowler> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bowler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseReference = Firebase.database.reference
    }

    fun getDataFromDatabase() {
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        }
        databaseReference.addChildEventListener(childEventListener)
        val child = databaseReference.child("bowlers")
        child.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                bowlerData.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val bowler = Bowler(
                        postSnapshot.child("bowlerName").value.toString(),
                        postSnapshot.child("overs").value.toString(),
                        postSnapshot.child("wickets").value.toString(),
                        postSnapshot.child("maidenOvers").value.toString()
                    )
                    bowlerData.add(bowler)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
            }
        })

    }
}