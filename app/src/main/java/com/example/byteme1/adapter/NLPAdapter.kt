package com.example.byteme1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.byteme1.Models.NLPModel
import com.example.byteme1.R

class NLPAdapter (
    private val NLPModelList: ArrayList<NLPModel>,
    private val listener: RecyclerViewEvent
) : RecyclerView.Adapter<NLPAdapter.ViewHolder>() {

    interface RecyclerViewEvent {
        fun onItemClick(position: Int, data: NLPModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NLPAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_algorithm, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NLPAdapter.ViewHolder, position: Int) {
       val NLPmodel = NLPModelList.get(position)
       val algorithmName = NLPmodel.name
       val algorithmIndex = NLPmodel.index

       holder.algorithm_name.text = algorithmName
       holder.index.text = algorithmIndex
    }

    override fun getItemCount(): Int {
      return NLPModelList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
       var algorithm_name = itemView.findViewById<TextView>(R.id.algorithm_name)
       var index = itemView.findViewById<TextView>(R.id.rvvPrefix)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position,NLPModelList[position])
            }
        }
    }

}