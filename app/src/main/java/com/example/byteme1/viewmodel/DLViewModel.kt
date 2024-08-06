package com.example.byteme1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.byteme1.Models.ComputerVisionModel
import com.example.byteme1.Models.DeepLearningModel
import java.util.Locale

class DLViewModel() : ViewModel() {
    private var _DlModelList: ArrayList<DeepLearningModel> = arrayListOf()
    private lateinit var _algorithmName: Array<String>
    private lateinit var _algorithmIndex: Array<String>
    private var _navigateToDetail = MutableLiveData<String?>()
    private var _filteredList: ArrayList<DeepLearningModel> = arrayListOf()
    private var _Algomap: HashMap<String, String> = hashMapOf()

    var Algomap: Map<String, String>
        get() = _Algomap
        private set(value) {
            _Algomap.putAll(value)
        }

    init {
        dataInitialize()
    }

    val navigateToDetail: LiveData<String?>
        get() = _navigateToDetail

    val DLModelList: ArrayList<DeepLearningModel>
        get() = _DlModelList

    var filteredList: ArrayList<DeepLearningModel>
        get() = _filteredList
        set(value) {
            _filteredList = value
        }

    fun onAlgorithmClicked(algoName: String){
        _navigateToDetail.value = algoName
    }

    fun onAlgoNavigated() {
        _navigateToDetail.value = null
    }

    private fun dataInitialize() {
        _DlModelList = arrayListOf<DeepLearningModel>()
        _algorithmName = arrayOf(
            "Multilayer Perceptrons","RBFNs","CNNs", "RNNs","LSTM ","GRUs", "GANs","VAEs","Autoencoders","DBNs",
            "Transformer Networks","DQNs","SOMs","GNNs","Hybrid Models","Transfer Learning","Model Optimization")

        val Algomap = hashMapOf(
            "dl1" to "Multilayer Perceptrons", "dl2" to "RBFNs", "dl3" to "CNNs", "dl4" to "RNNs",
            "dl5" to "LSTM", "dl6" to "GRUs", "dl7" to "GANs", "dl8" to "VAEs",
            "dl9" to "Autoencoders", "dl10" to "DBNs", "dl11" to "Transformer Networks", "dl12" to "DQNs",
            "dl13" to "SOMs", "dl14" to "GNNs", "dl15" to "Hybrid Models", "dl16" to "Transfer Learning", "dl17" to "Model Optimization"
        )

        var indexCounter = 1
        for ((key, value) in Algomap) {
            val model = DeepLearningModel(index = indexCounter.toString(),name = value)
            DLModelList.add(model)
            indexCounter++
        }
    }

    fun filter(text: String): Boolean {
        var found = false
        for (item in _DlModelList) {
            if (item.name.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                _filteredList.add(item)
                found = true
            }
        }
        return found
    }
}




