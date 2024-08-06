package com.example.byteme1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.byteme1.databinding.AlgorithmDetailBinding
import com.example.byteme1.databinding.ComputerVisionLBinding
import io.github.kbiakov.codeview.CodeView
import io.github.kbiakov.codeview.adapters.Options
import io.github.kbiakov.codeview.highlight.ColorTheme

//private var _binding: ComputerVisionLBinding? = null
//private val binding get() = _binding!!

class Fragment_AlgorithmDetail : Fragment() {
    private var _binding: AlgorithmDetailBinding? = null
    private val binding get() = _binding!!
    private var _Algomap: HashMap<String, String> = hashMapOf()
    private var _Algolistmap: HashMap<String, String> = hashMapOf()
    private var _dSAlgoMap: HashMap<String, String> = hashMapOf()

    var Algomap: Map<String,String>
        get() = _Algomap
        private set(value) {
            _Algomap.putAll(value)
        }

    var dSAlgoMap: Map<String, String>
        get() = _dSAlgoMap
        private set(value) {
            _dSAlgoMap.putAll(value)
        }

    var Algolistmap: Map<String, String>
        get() = _Algolistmap
        private set(value) {
            _Algolistmap.putAll(value)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AlgorithmDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)

        val algorithmName = arguments?.getString("algorithmName")
        val algorithmIndex = arguments?.getString("algorithmIndex")

        //val algoname = Fragment_AlgorithmDetailArgs.fromBundle(requireArguments()).algoname

        var dsAlgo = arrayListOf("Linked Lists","Stacks","Queues","Hash Tables","AVL Trees",
            "BinaryTrees","Heaps","Red-Black Trees","Tries","Fenwick Trees","Segment Trees","DisjointSetUnion","Minimum Spanning Trees")
        var nlpAlgo = arrayListOf("Lemmatization + Stemming","Topic Modelling","Keyword Extraction","Knowledge Graphs",
            "Words Cloud","Named Entity Recognition","Sentiment Analysis","Text Summarization","Bag of Words","Tokenization")
        var cvAlgo =arrayListOf(
            "FREAK (Fast Retina Keypoint)","FLANN (Fast Library for Approximate Nearest Neighbors)","CLAHE (Contrast Limited Adaptive Histogram Equalization)",
            "K-means Clustering","Faster R-CNN (Region-based Convolutional Neural Networks)","ResNet (Residual Networks)",
            "AlexNet","Support Vector Machines (SVM)","Lucas-Kanade Method","Affine and Perspective Transformations",
            "Structure from Motion (SfM)","Homography Estimation","Eigenfaces and Fisherfaces","PnP (Perspective-n-Point)","EAST (Efficient and Accurate Scene Text Detector)",
            "Show, Attend and Tell (Attention Mechanisms)","SRCNN (Super-Resolution Convolutional Neural Network)","Variational Autoencoders (VAEs)","Isolation Forest\n"
        )

        Algomap = hashMapOf(
            "cv1" to "FREAK", "cv2" to "FLANN", "cv3" to "CLAHE", "cv4" to "K-means Clustering",
            "cv5" to "Faster R-CNN", "cv6" to "ResNet (Residual Networks)", "cv7" to "AlexNet",
            "cv8" to "(SVM)", "cv9" to "Lucas-Kanade Method", "cv10" to "Affine & Perspective Transformations",
            "cv11" to "(SfM)", "cv12" to "Homography Estimation", "cv13" to "Eigenfaces & Fisherfaces",
            "cv14" to "PnP (Perspective-n-Point)", "cv15" to "EAST", "cv16" to "Show, Attend and Tell",
            "cv17" to "SRCNN", "cv18" to "VAEs", "cv19" to "Isolation Forest"
        )

        dSAlgoMap = hashMapOf(
            "dl1" to "Linked Lists", "dl2" to "Stacks", "dl3" to "Queues", "dl4" to "HashTables", "dl5" to "AVLTrees", "dl6" to "BinaryTrees",
            "dl7" to "Heaps", "dl8" to "RedBlackTrees", "dl9" to "Tries",
            "dl10" to "FenwickTrees", "dl11" to "SegmentTrees", "dl12" to "DisjointSetUnion", "dl13" to "MinimumSpanningTrees"
        )

         Algolistmap = hashMapOf(
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

        val header_title: TextView = view.findViewById<TextView>(R.id.header_title)
        val image: ImageView = view.findViewById<ImageView>(R.id.detailimage)

       if (algorithmName in dsAlgo) {
            binding.detailimage.setImageResource(R.drawable.cv)
        } else if (algorithmName in nlpAlgo) {
            binding.detailimage.setImageResource(R.drawable.data_structure)
       } else if (algorithmName in cvAlgo) {
           binding.detailimage.setImageResource(R.drawable.dld)
       }

       binding.headerTitle.text = algorithmName

        for ((key, value) in Algomap) {
            if (value == algorithmName) {
                var body1 = "${key}_body1"
                var sub_header_2 = "${key}_sub_header_2"
                var body2 = "${key}_body2"
                var sub_header_3 = "${key}_sub_header_3"
                var body3 = "${key}_body3"
                var sub_header_4 = "${key}_sub_header_4"
                var body4 = "${key}_body4"
                var example_header = "${key}_example_header"
                var example_body = "${key}_example_body"


                var resId: Int
                var stringResource: String

                resId = resources.getIdentifier(body1,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body1.text = stringResource

                resId = resources.getIdentifier(sub_header_2,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.subHeader2.text = stringResource

                resId = resources.getIdentifier(body2,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body2.text = stringResource

                resId = resources.getIdentifier(sub_header_3,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.subHeader3.text = stringResource

                resId = resources.getIdentifier(body3,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body3.text = stringResource

                resId = resources.getIdentifier(sub_header_4,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.subHeader4.text = stringResource

                resId = resources.getIdentifier(body4,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body4.text = stringResource

                resId = resources.getIdentifier(example_header,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.exampleHeader.text = stringResource

                resId = resources.getIdentifier(example_body,"string","com.example.byteme1")
                stringResource = getString(resId)
//                binding.exampleBody.text = stringResource
                var codeView: CodeView = view.findViewById(R.id.example_body)
                codeView.setOptions(
                    Options.Default.get(requireContext())
                    .withLanguage("python")
                    .withTheme(ColorTheme.MONOKAI));
                //val codeView = view.findViewById<CodeView>(R.id.example_body)
                codeView.setCode(stringResource)
                break
            }
        }

        for ((key, value) in dSAlgoMap) {
            if (value == algorithmName) {
                binding.detailimage.setImageResource(R.drawable.algorithm)

                var body1 = "${key}_body1"
                var sub_header_2 = "${key}_sub_header_2"
                var body2 = "${key}_body2"
                var sub_header_3 = "${key}_sub_header_3"
                var body3 = "${key}_body3"
                var sub_header_4 = "${key}_sub_header_4"
                var body4 = "${key}_body4"
                var example_header = "${key}_example_header"
                var example_body = "${key}_example_body"

                var resId: Int
                var stringResource: String

                resId = resources.getIdentifier(body1,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body1.text = stringResource

                resId = resources.getIdentifier(sub_header_2,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.subHeader2.text = stringResource

                resId = resources.getIdentifier(body2,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body2.text = stringResource

                resId = resources.getIdentifier(sub_header_3,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.subHeader3.text = stringResource

                resId = resources.getIdentifier(body3,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body3.text = stringResource

                resId = resources.getIdentifier(sub_header_4,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.subHeader4.text = stringResource

                resId = resources.getIdentifier(body4,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body4.text = stringResource

                resId = resources.getIdentifier(example_header,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.exampleHeader.text = stringResource

                resId = resources.getIdentifier(example_body,"string","com.example.byteme1")
                stringResource = getString(resId)
//                binding.exampleBody.text = stringResource
                var codeView: CodeView = view.findViewById(R.id.example_body)
                codeView.setOptions(
                    Options.Default.get(requireContext())
                        .withLanguage("python")
                        .withTheme(ColorTheme.MONOKAI));
                //val codeView = view.findViewById<CodeView>(R.id.example_body)
                codeView.setCode(stringResource)
                break
            }
        }

        for ((key, value) in Algolistmap) {
            if (value == algorithmName) {
                binding.detailimage.setImageResource(R.drawable.algorithm)

                var body1 = "${key}_body1"
                var sub_header_2 = "${key}_sub_header_2"
                var body2 = "${key}_body2"
                var sub_header_3 = "${key}_sub_header_3"
                var body3 = "${key}_body3"
                var sub_header_4 = "${key}_sub_header_4"
                var body4 = "${key}_body4"
                var example_header = "${key}_example_header"
                var example_body = "${key}_example_body"

                var resId: Int
                var stringResource: String

                resId = resources.getIdentifier(body1,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body1.text = stringResource

                resId = resources.getIdentifier(sub_header_2,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.subHeader2.text = stringResource

                resId = resources.getIdentifier(body2,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body2.text = stringResource

                resId = resources.getIdentifier(sub_header_3,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.subHeader3.text = stringResource

                resId = resources.getIdentifier(body3,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body3.text = stringResource

                resId = resources.getIdentifier(sub_header_4,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.subHeader4.text = stringResource

                resId = resources.getIdentifier(body4,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.body4.text = stringResource

                resId = resources.getIdentifier(example_header,"string","com.example.byteme1")
                stringResource = getString(resId)
                binding.exampleHeader.text = stringResource

                resId = resources.getIdentifier(example_body,"string","com.example.byteme1")
                stringResource = getString(resId)
//                binding.exampleBody.text = stringResource
                var codeView: CodeView = view.findViewById(R.id.example_body)
                codeView.setOptions(
                    Options.Default.get(requireContext())
                        .withLanguage("python")
                        .withTheme(ColorTheme.MONOKAI));
                //val codeView = view.findViewById<CodeView>(R.id.example_body)
                codeView.setCode(stringResource)
                break
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as? AppCompatActivity)?.supportActionBar?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}