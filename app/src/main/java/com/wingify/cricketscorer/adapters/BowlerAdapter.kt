package com.wingify.cricketscorer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wingify.cricketscorer.R
import com.wingify.cricketscorer.models.Bowler

class BowlerAdapter(private val context: Context, private val itemsData: ArrayList<Bowler>) :
    RecyclerView.Adapter<BowlerAdapter.BowlerViewHolder>() {

    class BowlerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bowlerName: TextView = itemView.findViewById(R.id.bowler_name)
        val bowlerOvers: TextView = itemView.findViewById(R.id.bowler_overs)
        val bowlerWickets: TextView = itemView.findViewById(R.id.bowler_wickets)
        val bowlerMaidenOvers: TextView = itemView.findViewById(R.id.bowler_maiden)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BowlerViewHolder {
        val v1: View = LayoutInflater.from(context)
            .inflate(R.layout.bowler_recycler_item, parent, false)
        return BowlerViewHolder(v1)
    }

    override fun onBindViewHolder(holder: BowlerViewHolder, position: Int) {
        holder.bowlerName.text = itemsData[position].bowlerName
        holder.bowlerOvers.text = itemsData[position].overs + " Overs"
        holder.bowlerWickets.text = itemsData[position].wickets + " Wickets"
        holder.bowlerMaidenOvers.text = itemsData[position].maidenOvers + " Maiden Overs"
    }

    override fun getItemCount(): Int {
        return itemsData.size
    }

}