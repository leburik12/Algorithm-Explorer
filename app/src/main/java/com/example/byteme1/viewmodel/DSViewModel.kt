package com.example.byteme1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.byteme1.Models.AlgorithmModel
import com.example.byteme1.Models.DSModel

class DSViewModel (): ViewModel() {
    private var _DSModelArrayList: ArrayList<DSModel> = arrayListOf()
    private lateinit var _algorithmName: Array<String>
    private lateinit var _algorithmIndex: Array<String>
    private val _navigateToDetail = MutableLiveData<String?>()
    private var _Algomap: HashMap<String, String> = hashMapOf()

    var Algomap: Map<String, String>
        get() = _Algomap
        private set(value) {
            _Algomap.putAll(value)
        }

    val navigateToDetail: LiveData<String?>
        get() = _navigateToDetail

    val DSModelArrayList: ArrayList<DSModel>
        get() = _DSModelArrayList

    val algorithmName: Array<String>
        get() = _algorithmName

    val algorithmIndex: Array<String>
        get() = _algorithmName

    init {
      dataInitialize()
    }

    private fun dataInitialize() {
        _DSModelArrayList = arrayListOf<DSModel>()
         Algomap = hashMapOf(
            "dl1" to "Linked Lists", "dl2" to "Stacks", "dl3" to "Queues", "dl4" to "HashTables", "dl5" to "AVLTrees", "dl6" to "BinaryTrees",
            "dl7" to "Heaps", "dl8" to "RedBlackTrees", "dl9" to "Tries",
            "dl10" to "FenwickTrees", "dl11" to "SegmentTrees", "dl12" to "DisjointSetUnion", "dl13" to "MinimumSpanningTrees"
        )
        var indexCounter = 1
        for ((key, value) in Algomap) {
            val model = DSModel(index=indexCounter.toString(),name=value)
            _DSModelArrayList.add(model)
            indexCounter++
        }
    }

    fun onAlgorithmClicked(algoName: String) {
        _navigateToDetail.value = algoName
    }

    fun onAlgoNavigated() {
        _navigateToDetail.value = null
    }


}