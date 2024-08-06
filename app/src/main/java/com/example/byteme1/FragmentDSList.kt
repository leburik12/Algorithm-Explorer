package com.example.byteme1

import com.example.byteme1.databinding.AlgorithmListBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import java.util.Locale
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.byteme1.Models.DSModel
import com.example.byteme1.adapter.DSAdapter


class FragmentDSList : Fragment() {

    private lateinit var adapter: DSAdapter
    //private lateinit var recyclerView: RecyclerView
    private lateinit var DSModelArrayList: ArrayList<DSModel>
    //private lateinit var searchView: SearchView
    private var _binding: AlgorithmListBinding? = null
    private val binding get() = _binding!!

    lateinit var algorithmName: Array<String>
    lateinit var algorithmIndex: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = AlgorithmListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
        //return inflater.inflate(R.layout.algorithm_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        //searchView = view.findViewById(R.id.searchview)
        binding.searchview.clearFocus()
        //searchView.clearFocus()
        val layoutManager = LinearLayoutManager(context)
        //recyclerView = view.findViewById(R.id.recycler_view)
        //recyclerView.layoutManager = layoutManager
        binding.recyclerView.layoutManager = layoutManager
        adapter = DSAdapter(DSModelArrayList,object: DSAdapter.RecyclerViewEvent {
            override fun onItemClick(position: Int,data: DSModel) {
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

                navcontroller.navigate(R.id.action_data_structure_list_to_fragment_AlgorithmDetail, bundle,navOptions)
            }
        })
        binding.recyclerView.adapter = adapter

        binding.searchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })
    }

    private fun filter(text: String) {
        val filteredList = ArrayList<DSModel>()
        for (item in DSModelArrayList) {
            if (item.name.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                filteredList.add(item)
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(),"No algorithm found",Toast.LENGTH_SHORT).show()
        }  else {
            adapter.filterList(filteredList)
        }
    }

    private fun dataInitialize() {
        DSModelArrayList = arrayListOf<DSModel>()
        algorithmName = arrayOf(
            getString(R.string.linked_lists),
            getString(R.string.Stacks),
            getString(R.string.Queues),
            getString(R.string.HashTables),
            getString(R.string.AVLTrees),
            getString(R.string.BinaryTrees),
            getString(R.string.Heaps),
            getString(R.string.RedBlackTrees),
            getString(R.string.Tries),
            getString(R.string.FenwickTrees),
            getString(R.string.SegmentTrees),
            getString(R.string.DisjointSetUnion),
            getString(R.string.MinimumSpanningTrees)
        )

        algorithmIndex= arrayOf("0","1","2","3","4","5","6","7","8","9","10","11","12")
        for (i in algorithmName.indices) {
            val index = algorithmIndex[i].toInt() + 1
            val algorithmodel = DSModel(index.toString(),algorithmName[i])
            DSModelArrayList.add(algorithmodel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}