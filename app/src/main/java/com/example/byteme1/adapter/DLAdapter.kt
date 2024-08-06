package com.example.byteme1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.byteme1.Models.ComputerVisionModel
import com.example.byteme1.Models.DSModel
import com.example.byteme1.Models.DeepLearningModel
import com.example.byteme1.R
import com.example.byteme1.adapter.CVAdapter.CVItemViewHolder
import com.example.byteme1.databinding.RowAlgorithmBinding

class DLAdapter (
    val clickListener: (algoname: String) -> Unit,
    private var DeepLearningModeList: ArrayList<DeepLearningModel>
): RecyclerView.Adapter<DLAdapter.DLViewHolder>() {

    fun filterList(filterList: ArrayList<DeepLearningModel>) {
        DeepLearningModeList = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
         : DLViewHolder = DLViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: DLViewHolder, position: Int) {
        val item = DeepLearningModeList[position]
        holder.bind(item,clickListener)
    }

    override fun getItemCount() = DeepLearningModeList.size

    class DLViewHolder(val binding: RowAlgorithmBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): DLViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowAlgorithmBinding.inflate(layoutInflater, parent, false)
                return DLViewHolder(binding)
            }
        }

        fun bind(item: DeepLearningModel, clickListener: (algoname: String) -> Unit) {
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