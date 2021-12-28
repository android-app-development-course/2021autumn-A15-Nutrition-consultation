package com.lnm011223.foods_secret.ui.analyse

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.AnalyseFragmentBinding
import com.lnm011223.foods_secret.logic.model.Food
import kotlinx.android.synthetic.main.search_fragment.*

@Suppress("UNCHECKED_CAST")
class AnalyseFragment : Fragment() {

    private lateinit var analyseViewModel: AnalyseViewModel
    private var _binding: AnalyseFragmentBinding? = null
    private lateinit var totalFood:Food
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
        totalFood = Food()

        // 计算各营养成分的含量
        for (food in foodList) {
            val intake = food.intakeAmount / 100.0F
            totalFood.carbohydrate  += intake * food.carbohydrate
            totalFood.diaryFiber += intake * food.diaryFiber
            totalFood.fat += intake * food.fat
            totalFood.power += intake * food.power
        }
        Log.i("TEST", "onActivityCreated: 总的摄入量$totalFood");
    }

    // 分析
    private fun analyze() {

    }
}