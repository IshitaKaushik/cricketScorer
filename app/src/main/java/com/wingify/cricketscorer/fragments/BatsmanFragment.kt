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
import com.google.firebase.ktx.Firebase
import com.wingify.cricketscorer.R
import com.wingify.cricketscorer.activities.BatsmanActivity
import com.wingify.cricketscorer.activities.BowlerActivity
import com.wingify.cricketscorer.adapters.BatsmanAdapter
import com.wingify.cricketscorer.adapters.BowlerAdapter
import com.wingify.cricketscorer.models.Batsman
import com.wingify.cricketscorer.models.Bowler

class BatsmanFragment : Fragment() {

    private lateinit var databaseReference: DatabaseReference
    private val batsmanData: ArrayList<Batsman> = ArrayList()
    private lateinit var batsmanAdapter: BatsmanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_batsman, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseReference = Firebase.database.reference
        getDataFromDatabase()
        batsmanAdapter = BatsmanAdapter(requireContext(), batsmanData)
        val recyclerView: RecyclerView = view.findViewById(R.id.batsmen_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = batsmanAdapter
        view.findViewById<FloatingActionButton>(R.id.new_batsman).setOnClickListener{
            val intent = Intent(requireContext(), BatsmanActivity::class.java)
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
        val child = databaseReference.child("batsmen")
        child.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                batsmanData.clear()
                for (postSnapshot in dataSnapshot.children) {
                    val batsman = Batsman(
                        postSnapshot.child("batsmanName").value.toString(),
                        postSnapshot.child("ballsPlayed").value.toString(),
                        postSnapshot.child("runs").value.toString()
                    )
                    batsmanData.add(batsman)
                    batsmanAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
            }
        })

    }

}