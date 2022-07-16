package com.wingify.cricketscorer.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.wingify.cricketscorer.R
import com.wingify.cricketscorer.activities.BowlerActivity
import com.wingify.cricketscorer.activities.DashboardActivity
import com.wingify.cricketscorer.adapters.BowlerAdapter
import com.wingify.cricketscorer.models.Bowler

class BowlerFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference
    private val bowlerData: ArrayList<Bowler> = ArrayList()
    private lateinit var bowlerAdapter: BowlerAdapter

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
        getDataFromDatabase()
        bowlerAdapter = BowlerAdapter(requireContext(), bowlerData)
        val recyclerView: RecyclerView = view.findViewById(R.id.bowlers_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = bowlerAdapter
        view.findViewById<FloatingActionButton>(R.id.new_bowler).setOnClickListener{
            val intent = Intent(requireContext(), BowlerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getDataFromDatabase() {
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
                    bowlerAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
            }
        })

    }
}