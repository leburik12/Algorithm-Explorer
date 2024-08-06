package com.example.byteme1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.byteme1.Models.AlgorithmModel
import com.example.byteme1.Models.ComputerVisionModel
import com.example.byteme1.adapter.CVAdapter.CVItemViewHolder
import com.example.byteme1.databinding.RowAlgorithmBinding

class AlgoAdapter (val clickListener: (algoname: String) -> Unit,
                   private var AlgorithmModelList: ArrayList<AlgorithmModel>)
    : RecyclerView.Adapter<AlgoAdapter.AlgoItemViewHolder>()  {

    fun filterList(filterList: ArrayList<AlgorithmModel>) {
        AlgorithmModelList = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : AlgoItemViewHolder = AlgoItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: AlgoItemViewHolder, position: Int) {
        val item = AlgorithmModelList[position]
        holder.bind(item,clickListener)
    }

    override fun getItemCount() = AlgorithmModelList.size

    class AlgoItemViewHolder(val binding: RowAlgorithmBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): AlgoItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowAlgorithmBinding.inflate(layoutInflater,parent,false)
                return AlgoItemViewHolder(binding)
            }
        }

        fun bind(item: AlgorithmModel, clickListener: (algoname: String) -> Unit) {
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



//class CVAdapter(
//    val clickListener: (algoname: String) -> Unit,
//    private var computerVisionModelList: ArrayList<ComputerVisionModel>)
//    :  RecyclerView.Adapter<CVAdapter.CVItemViewHolder>()
//    class CVItemViewHolder(val binding: RowAlgorithmBinding) : RecyclerView.ViewHolder(binding.root) {
//
//        companion object {
//            fun inflateFrom(parent: ViewGroup): CVItemViewHolder {
//                val layoutInflater = LayoutInflater.from(parent.context)
//                //val view = layoutInflater.inflate(R.layout.row_algorithm,parent,false)
//                val binding = RowAlgorithmBinding.inflate(layoutInflater,parent,false)
//                return CVItemViewHolder(binding)
//            }
//        }
//
//        fun bind(item: ComputerVisionModel, clickListener: (algoname: String) -> Unit) {
//            val algoname = item.name
//            val index = item.index
//
//            binding.algorithmName.text = algoname
//            binding.rvvPrefix.text = index
//
//            itemView.setOnClickListener {
//                clickListener(algoname)
//            }
//        }
//    }
//}