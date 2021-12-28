package com.lnm011223.foods_secret.ui.analyse

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lnm011223.foods_secret.FoodSelectAdapter
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.ResultAdapter
import com.lnm011223.foods_secret.databinding.AnalyseFragmentBinding
import com.lnm011223.foods_secret.logic.model.Food
import com.lnm011223.foods_secret.logic.model.GetResult
import kotlinx.android.synthetic.main.search_fragment.*

class AnalyseFragment : Fragment() {

    private lateinit var analyseViewModel: AnalyseViewModel
    private var _binding: AnalyseFragmentBinding? = null
    private lateinit var totalFood:Food
    private val resultList = ArrayList<GetResult>()
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
            totalFood.protein += intake * food.protein
        }
        Log.i("TEST", "onActivityCreated: 总的摄入量$totalFood")
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.resultView.layoutManager = layoutManager
        val adapter = ResultAdapter(resultList)
        binding.resultView.adapter = adapter
        analyze()

    }

    // 分析
    private fun analyze() {
        resultList.clear()
        resultList.add(GetResult("热量",totalFood.power,""))
        resultList.add(GetResult("脂肪",totalFood.fat,""))
        resultList.add(GetResult("蛋白质",totalFood.protein,""))
        resultList.add(GetResult("碳水化合物",totalFood.carbohydrate,""))
        resultList.add(GetResult("膳食纤维",totalFood.diaryFiber,""))

    }

}