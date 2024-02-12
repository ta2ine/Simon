package com.example.simon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simon.data.ScoreOnline

class AdapterClassement(val data: List<ScoreOnline>) : RecyclerView.Adapter<AdapterClassement.MyViewHolder>() {

    private val limite = 10
    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val nameTextView = row.findViewById<TextView>(R.id.nameTextView)
        val scoreTextView = row.findViewById<TextView>(R.id.scoreTextView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameTextView.text = data.get(position).name
        holder.scoreTextView.text = data.get(position).score.toString()
    }
    override fun getItemCount(): Int = data.size

}
