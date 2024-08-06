package com.example.byteme1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.byteme1.Models.ComputerVisionModel
import com.example.byteme1.Models.DSModel
import java.util.Locale

class ComputerVisionViewModel (): ViewModel() {
    private var _CVModelList: ArrayList<ComputerVisionModel> = arrayListOf()
    private lateinit var _algorithmName: Array<String>
    private lateinit var _algorithmIndex: Array<String>
    private val _navigateToDetail = MutableLiveData<String?>()
    private var _filteredList: ArrayList<ComputerVisionModel> = arrayListOf()
    private var _Algomap: HashMap<String, String> = hashMapOf()

    var Algomap: Map<String,String>
        get() = _Algomap
        private set(value) {
            _Algomap.putAll(value)
        }

    val navigateToDetail: LiveData<String?>
        get() = _navigateToDetail

    val CVModelList: ArrayList<ComputerVisionModel>
        get() = _CVModelList

    val algorithmModel: Array<String>
        get() = _algorithmName

    val algorithmIndex: Array<String>
        get() = algorithmIndex

    var filteredList: ArrayList<ComputerVisionModel>
        get() = _filteredList
        set(value) {
            _filteredList = value
        }


    init {
        dataInitialize()
    }

    fun onAlgorithmClicked(algoName: String){
        _navigateToDetail.value = algoName
    }

    fun onAlgoNavigated() {
        _navigateToDetail.value = null
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

    fun updateMap(newMap: Map<String, String>) {
        Algomap = newMap
    }

    fun addEntry(key: String, value: String) {
        _Algomap[key] = value
    }

    fun addEntries(newEntries: Map<String, String>) {
        _Algomap.putAll(newEntries)
    }

    private fun dataInitialize() {
        _CVModelList = arrayListOf<ComputerVisionModel>()
        _algorithmName = arrayOf(
            "FREAK","FLANN","CLAHE",
            "K-means Clustering","Faster R-CNN ","ResNet (Residual Networks)",
            "AlexNet"," (SVM)","Lucas-Kanade Method","Affine & Perspective Transformations",
            "(SfM)","Homography Estimation","Eigenfaces & Fisherfaces","PnP (Perspective-n-Point)","EAST",
            "Show, Attend and Tell ","SRCNN","VAEs","Isolation Forest"
        )

        Algomap = hashMapOf(
            "cv1" to "FREAK", "cv2" to "FLANN", "cv3" to "CLAHE", "cv4" to "K-means Clustering",
            "cv5" to "Faster R-CNN", "cv6" to "ResNet (Residual Networks)", "cv7" to "AlexNet",
            "cv8" to "(SVM)", "cv9" to "Lucas-Kanade Method", "cv10" to "Affine & Perspective Transformations",
            "cv11" to "(SfM)", "cv12" to "Homography Estimation", "cv13" to "Eigenfaces & Fisherfaces",
            "cv14" to "PnP (Perspective-n-Point)", "cv15" to "EAST", "cv16" to "Show, Attend and Tell",
            "cv17" to "SRCNN", "cv18" to "VAEs", "cv19" to "Isolation Forest"
        )
        var indexCounter = 1
        for ((key, value) in Algomap) {
                val model = ComputerVisionModel(index = indexCounter.toString(),name = value)
            CVModelList.add(model)
            indexCounter++
        }

    }

    fun filter(text: String): Boolean {
        var found = false
        for (item in _CVModelList) {
            if (item.name.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                _filteredList.add(item)
                found = true
            }
        }
        return found
    }
}