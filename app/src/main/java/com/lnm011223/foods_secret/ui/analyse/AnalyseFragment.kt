package com.lnm011223.foods_secret.ui.analyse

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.AnalyseFragmentBinding
import com.lnm011223.foods_secret.logic.model.Food
import kotlinx.android.synthetic.main.search_fragment.*

class AnalyseFragment : Fragment() {

    private lateinit var analyseViewModel: AnalyseViewModel
    private var _binding: AnalyseFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        analyseViewModel =
            ViewModelProvider(this).get(AnalyseViewModel::class.java)

        _binding = AnalyseFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 获取选择的搭配
        val foodList:ArrayList<Food> = arguments?.getSerializable("foodList") as ArrayList<Food>
        for (food in foodList) {
            Log.i("Test", food.toString());
        }
    }

}