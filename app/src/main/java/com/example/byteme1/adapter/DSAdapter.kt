package com.example.byteme1.adapter

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.byteme1.Models.DSModel
import com.example.byteme1.R


class DSAdapter(
    private var DSModelList: ArrayList<DSModel>,
    private val listener: RecyclerViewEvent
): RecyclerView.Adapter<DSAdapter.ViewHolder>() {

    private lateinit var context: Context

    interface RecyclerViewEvent {
        fun onItemClick(position: Int, data: DSModel)
    }

    fun filterList(filterList: ArrayList<DSModel>) {
        DSModelList = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_algorithm, parent, false)
        context = parent.context
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val algorithModel = DSModelList.get(position)
        var algorithmName = algorithModel.name
        var index = algorithModel.index

        holder.algorithm_name.text = algorithmName
        holder.index.text = index
    }

    override fun getItemCount(): Int {
        return DSModelList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var algorithm_name = itemView.findViewById<TextView>(R.id.algorithm_name)
        var index = itemView.findViewById<TextView>(R.id.rvvPrefix)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, DSModelList[position])
            }
        }
    }

}