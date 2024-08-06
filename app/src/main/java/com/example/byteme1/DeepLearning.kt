package com.example.byteme1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.byteme1.Models.ComputerVisionModel
import com.example.byteme1.Models.DeepLearningModel
import com.example.byteme1.adapter.CVAdapter
import com.example.byteme1.adapter.DLAdapter
import com.example.byteme1.databinding.ComputerVisionLBinding
import com.example.byteme1.databinding.DeepLearningLBinding
import com.example.byteme1.viewmodel.ComputerVisionViewModel
import com.example.byteme1.viewmodel.DLViewModel

class DeepLearning : Fragment() {
    private var _binding: DeepLearningLBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: DLViewModel
    private var dlmodelList: ArrayList<DeepLearningModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DeepLearningLBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DLViewModel::class.java)
        dlmodelList = viewModel.DLModelList

        val adapter = DLAdapter (
            clickListener = { algoname -> viewModel.onAlgorithmClicked(algoname)},
            DeepLearningModeList = viewModel.DLModelList
        )

        binding.recyclerViewDl.adapter = adapter
        var bundle = Bundle()

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer { algoname ->
            algoname?.let {
                bundle.putString("algorithmName", algoname)
                val action = DeepLearningDirections.actionDeepLearningToFragmentAlgorithmDetail()
                val navController = findNavController()
                val navOptions = NavOptions.Builder()
                    .setExitAnim(R.anim.fade_out)
                    .setEnterAnim(R.anim.slide_in)
                    .setPopExitAnim(R.anim.slide_out)
                    .setPopEnterAnim(R.anim.fade_in)
                    .build()
                navController.navigate(R.id.action_deep_learning_to_fragment_AlgorithmDetail,bundle,navOptions)
                viewModel.onAlgoNavigated()
            }
        })
        binding.searchview2.clearFocus()
        binding.searchview2.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
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
