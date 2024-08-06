package com.example.byteme1.adapter

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.byteme1.Models.ComputerVisionModel
import com.example.byteme1.R
import com.example.byteme1.databinding.ComputerVisionLBinding
import com.example.byteme1.databinding.RowAlgorithmBinding

class CVAdapter(
          val clickListener: (algoname: String) -> Unit,
          private var computerVisionModelList: ArrayList<ComputerVisionModel>)
       :  RecyclerView.Adapter<CVAdapter.CVItemViewHolder>() {

    fun filterList(filterList: ArrayList<ComputerVisionModel>) {
        computerVisionModelList = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : CVItemViewHolder = CVItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: CVItemViewHolder, position: Int) {
        val item = computerVisionModelList[position]
        holder.bind(item,clickListener)
    }

    override fun getItemCount() = computerVisionModelList.size

    class CVItemViewHolder(val binding: RowAlgorithmBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): CVItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                //val view = layoutInflater.inflate(R.layout.row_algorithm,parent,false)
                val binding = RowAlgorithmBinding.inflate(layoutInflater,parent,false)
                return CVItemViewHolder(binding)
            }
        }

        fun bind(item: ComputerVisionModel, clickListener: (algoname: String) -> Unit) {
            val algoname = item.name
            val index = item.index

            binding.algorithmName.text = algoname
            binding.rvvPrefix.text = index

            itemView.setOnClickListener {
                clickListener(algoname)
            }
        }
    }
}