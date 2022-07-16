package com.wingify.cricketscorer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wingify.cricketscorer.R
import com.wingify.cricketscorer.models.Batsman

class BatsmanAdapter(private val context: Context, private val itemsData: ArrayList<Batsman>) :
    RecyclerView.Adapter<BatsmanAdapter.BatsmanViewHolder>() {

    class BatsmanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val batsmanName: TextView = itemView.findViewById(R.id.batsman_name)
        val ballsPlayed: TextView = itemView.findViewById(R.id.balls_played)
        val runsScored: TextView = itemView.findViewById(R.id.runs_scored)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatsmanViewHolder {
        val v1: View = LayoutInflater.from(context)
            .inflate(R.layout.batsman_recycler_item, parent, false)
        return BatsmanViewHolder(v1)
    }

    override fun onBindViewHolder(holder: BatsmanViewHolder, position: Int) {
        holder.batsmanName.text = itemsData[position].batsmanName
        holder.ballsPlayed.text = itemsData[position].ballsPlayed + " Balls"
        holder.runsScored.text = itemsData[position].runs + " Runs"
    }

    override fun getItemCount(): Int {
        return itemsData.size
    }

}