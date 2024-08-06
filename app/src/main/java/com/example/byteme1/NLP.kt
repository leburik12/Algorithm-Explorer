package com.example.byteme1


import com.example.byteme1.databinding.NlpLBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.byteme1.Models.DSModel
import com.example.byteme1.Models.NLPModel
import com.example.byteme1.adapter.NLPAdapter


class NLP : Fragment() {

    private lateinit var adapter: NLPAdapter
    //private lateinit var recyclerView: RecyclerView
    private lateinit var NLPModelArrayList: ArrayList<NLPModel>
    lateinit var algorithmName: Array<String>
    lateinit var algorithmIndex: Array<String>
    private var _binding: NlpLBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NlpLBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.nlp_l, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        //recyclerView = view.findViewById(R.id.recycler_view_nlp)
        //recyclerView.layoutManager = layoutManager
        //recyclerView.setHasFixedSize(true)
        binding.recyclerViewNlp.layoutManager = layoutManager
        binding.recyclerViewNlp.setHasFixedSize(true)

        adapter = NLPAdapter(NLPModelArrayList,object: NLPAdapter.RecyclerViewEvent {
            override fun onItemClick(position: Int, data: NLPModel) {
                var bundle = Bundle()
                bundle.putString("algorithmName",data.name)
                bundle.putString("algorithmIndex",data.index)
                val navcontroller = findNavController()
                val navOptions = NavOptions.Builder()
                    .setExitAnim(R.anim.fade_out)
                    .setEnterAnim(R.anim.slide_in)
                    .setPopExitAnim(R.anim.slide_out)
                    .setPopEnterAnim(R.anim.fade_in)
                    .build()
                navcontroller.navigate(R.id.action_natural_language_processing_to_fragment_AlgorithmDetail, bundle, navOptions)
            }
        })
        //recyclerView.adapter = adapter
        binding.recyclerViewNlp.adapter = adapter
    }

    private fun dataInitialize() {
        NLPModelArrayList = arrayListOf<NLPModel>()
        algorithmName = arrayOf(
            getString(R.string.nlp1),
            getString(R.string.nlp2),
            getString(R.string.nlp3),
            getString(R.string.nlp4),
            getString(R.string.nlp5),
            getString(R.string.nlp6),
            getString(R.string.nlp7),
            getString(R.string.nlp8),
            getString(R.string.nlp9),
            getString(R.string.nlp10)
        )
        algorithmIndex = arrayOf("0","1","2","3","4","5","6","7","8","9")
        for (i in algorithmName.indices) {
            val index = algorithmIndex[i].toInt() + 1
            val algorithmodel = NLPModel(index.toString(), algorithmName[i])
            NLPModelArrayList.add(algorithmodel)
        }
    }
}