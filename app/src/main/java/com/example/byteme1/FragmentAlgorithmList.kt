package com.example.byteme1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.byteme1.Models.AlgorithmModel
import com.example.byteme1.Models.ComputerVisionModel
import com.example.byteme1.adapter.AlgoAdapter
import com.example.byteme1.databinding.ComputerVisionLBinding
import com.example.byteme1.databinding.FragmentAlgorithmListBinding
import com.example.byteme1.viewmodel.AlgoViewModel
import com.example.byteme1.viewmodel.ComputerVisionViewModel

class FragmentAlgorithmList : Fragment() {
    private val TAG: String = "FRAGMENT_ALGORITHM_LIST"
    private var _binding: FragmentAlgorithmListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: AlgoViewModel
    private var algoviewmodellist: ArrayList<AlgorithmModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlgorithmListBinding.inflate(inflater, container,false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(AlgoViewModel::class.java)
        algoviewmodellist = viewModel.AlgoModelList

        val adapter = AlgoAdapter(
            clickListener = { algoname -> viewModel.onAlgorithmClicked(algoname)},
            AlgorithmModelList = algoviewmodellist
        )
        binding.recyclerViewAl.adapter = adapter
        var bundle = Bundle()

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { algoname ->
            algoname?.let {
                bundle.putString("algorithmName",algoname)
                val navController = findNavController()
                val navOptions = NavOptions.Builder()
                    .setExitAnim(R.anim.fade_out)
                    .setEnterAnim(R.anim.slide_in)
                    .setPopExitAnim(R.anim.slide_out)
                    .setPopEnterAnim(R.anim.fade_in)
                    .build()

                navController.navigate(R.id.action_fragmentAlgorithmList_to_fragment_AlgorithmDetail,bundle,navOptions)
                viewModel.onAlgoNavigated()
            }
        })

        binding.searchview3.clearFocus()

        binding.searchview3.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
               return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                val isFound = viewModel.filter(query)
                if (isFound) {
                    adapter.filterList(viewModel.filteredList)
                    viewModel.filteredList = arrayListOf()
                } else {
                    Log.i(TAG, "This is algorithm list")
                    Toast.makeText(requireContext(), "No algorithm found", Toast.LENGTH_SHORT).show()
                }
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}