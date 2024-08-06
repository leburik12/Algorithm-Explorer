package com.example.byteme1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.byteme1.Models.AlgorithmModel
import com.example.byteme1.Models.ComputerVisionModel
import java.util.Locale

class AlgoViewModel() : ViewModel() {
    private var _AlgorithmModelList: ArrayList<AlgorithmModel> = arrayListOf()
    private lateinit var _algorithmName: Array<String>
    private lateinit var _algorithmIndex: Array<String>
    private val _navigateToDetail = MutableLiveData<String?>()
    private var _filteredList: ArrayList<AlgorithmModel> = arrayListOf()
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

    val AlgoModelList: ArrayList<AlgorithmModel>
        get() = _AlgorithmModelList

    val algorithmModel: Array<String>
        get() = _algorithmName

    val algorithmIndex: Array<String>
        get() = algorithmIndex

    var filteredList: ArrayList<AlgorithmModel>
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

    fun updateMap(newMap: Map<String, String>) {
        Algomap = newMap
    }

    fun addEntry(key: String, value: String) {
        _Algomap[key] = value
    }

    fun addEntries(newEntries: Map<String, String>) {
        _Algomap.putAll(newEntries)
    }

    fun initializeMap(keys: List<String>, values: List<String>) {
        if (keys.size != values.size) {
            throw IllegalArgumentException("Keys and values lists must have the same size.")
        }
        _Algomap.clear()
        for (i in keys.indices) {
            _Algomap[keys[i]] = values[i]
         }

        }


    private fun dataInitialize() {
        _AlgorithmModelList = arrayListOf<AlgorithmModel>()
        Algomap = hashMapOf(
            "algo1" to "Travelling Salesman Problem (TSP)",
            "algo2" to "Knapsack Problem",
            "algo3" to "Graph Isomorphism Problem",
            "algo4" to "Boolean Satisfiability Problem (SAT)",
            "algo5" to "Integer Factorization",
            "algo6" to "Maximum Clique Problem",
            "algo7" to "Subset Sum Problem",
            "algo8" to "Hamiltonian Path Problem",
            "algo9" to "P vs NP Problem",
            "algo10" to "Vertex Cover Problem",
            "algo11" to "3-SAT Problem",
            "algo12" to "Karger's Min Cut Algorithm",
            "algo13" to "Elliptic Curve Cryptography (ECC)",
            "algo14" to "Smith-Waterman Algorithm",
            "algo15" to "Alpha-Beta Pruning"
        )

        var indexCounter = 1
        for ((key, value) in Algomap) {
            val model = AlgorithmModel(index=indexCounter.toString(),name=value)
            _AlgorithmModelList.add(model)
            indexCounter++
        }
    }

    fun filter(text: String): Boolean {
        var found = false
        for (item in _AlgorithmModelList) {
            if (item.name.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                _filteredList.add(item)
                found = true
            }
        }
        return found
    }
}