package com.example.byteme1

import com.example.byteme1.databinding.ComputerVisionLBinding
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.byteme1.Models.ComputerVisionModel
import com.example.byteme1.adapter.CVAdapter
import com.example.byteme1.viewmodel.ComputerVisionViewModel


class ComputerVision : Fragment() {
    private var _binding: ComputerVisionLBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ComputerVisionViewModel
    private var cvmodellist: ArrayList<ComputerVisionModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ComputerVisionLBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ComputerVisionViewModel::class.java)
        cvmodellist = viewModel.CVModelList
        //val adapter = CVAdapter(algoname -> viewModel.onAlgorithmClicked(algoname) , cvmodellist)
        val adapter = CVAdapter (
            clickListener = { algoname -> viewModel.onAlgorithmClicked(algoname)},
            computerVisionModelList = viewModel.CVModelList
        )
        //binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerViewCv.adapter = adapter
        var bundle = Bundle()

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { algoname ->
            algoname?.let {
                bundle.putString("algorithmName", algoname)
                val action = ComputerVisionDirections.actionComputerVisionToFragmentAlgorithmDetail()
                val navcontroller = findNavController()
                val navOptions = NavOptions.Builder()
                    .setExitAnim(R.anim.fade_out)
                    .setEnterAnim(R.anim.slide_in)
                    .setPopExitAnim(R.anim.slide_out)
                    .setPopEnterAnim(R.anim.fade_in)
                    .build()
                navcontroller.navigate(R.id.action_computer_vision_to_fragment_AlgorithmDetail,bundle,navOptions)
                viewModel.onAlgoNavigated()
            }
        })

        binding.searchview1.clearFocus()
        binding.searchview1.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(query: String): Boolean {
                val isFound = viewModel.filter(query)
                if (isFound) {
                    adapter.filterList(viewModel.filteredList)
                    viewModel.filteredList = arrayListOf()
                } else {
                    Toast.makeText(requireContext(),"No algorithm found", Toast.LENGTH_SHORT).show()
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