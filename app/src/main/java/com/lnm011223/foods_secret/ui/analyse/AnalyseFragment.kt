package com.lnm011223.foods_secret.ui.analyse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lnm011223.foods_secret.ResultAdapter
import com.lnm011223.foods_secret.databinding.AnalyseFragmentBinding
import com.lnm011223.foods_secret.logic.model.Food
import com.lnm011223.foods_secret.logic.model.GetResult
import kotlinx.android.synthetic.main.analyse_fragment.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class AnalyseFragment : Fragment() {

    private lateinit var analyseViewModel: AnalyseViewModel
    private var _binding: AnalyseFragmentBinding? = null
    private lateinit var totalFood:Food
    private val resultList = ArrayList<GetResult>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    // 营养成分标准对照表
    private val nutritionComparisonMap:HashMap<String,Float> = mapOf(
        "carbohydrate" to 120.0f,
        "fat" to 27.0f,
        "protein" to 60.0f,
        "diaryFiber" to 25.0f,
        "power" to 2000.0f
    ) as HashMap<String, Float>
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
        day_text.text = getCurrentMealTime()


    }

    // 分析
    private fun analyze() {
        resultList.clear()
        // 分析逻辑
        resultList.add(GetResult("热量",totalFood.power,getAnalyzeResult("power",totalFood.power)))
        resultList.add(GetResult("脂肪",totalFood.fat,getAnalyzeResult("fat",totalFood.fat)))
        resultList.add(GetResult("蛋白质",totalFood.protein,getAnalyzeResult("protein",totalFood.protein)))
        resultList.add(GetResult("碳水化合物",totalFood.carbohydrate,getAnalyzeResult("carbohydrate",totalFood.carbohydrate)))
        resultList.add(GetResult("膳食纤维",totalFood.diaryFiber,getAnalyzeResult("diaryFiber",totalFood.diaryFiber)))

    }

    /** 获取当前时间段对应的饭点
     * @return 午饭、晚饭、早饭
     */
    private fun getCurrentMealTime():String{
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 5..10 -> {
                "早饭"
            }
            in 11..16 -> {
                "午饭"
            }
            in 17..22 -> {

                "晚饭"
            }
            else -> {
                "夜宵"
            }
        }
    }


    /**
     * 参考标准表获取评价
     */
    private fun getAnalyzeResult(nutritionName:String, value:Float):String {
        var standardValue: Float? = nutritionComparisonMap[nutritionName]
        standardValue = when(getCurrentMealTime()) {
            "晚饭" -> standardValue?.times(0.3.toFloat())
            "早饭" -> standardValue?.times(0.3.toFloat())
            "午饭" -> standardValue?.times(0.4.toFloat())
            else -> standardValue?.times(0.3.toFloat())

        }
        when {
            value < 0.2* standardValue!! -> {
                Log.d("1111",standardValue.toString())
                return "急需摄入";
            }
            value <0.5*standardValue -> {
                return "建议多摄取相应的营养物质";
            }
            value < 0.8*standardValue -> {
                return  "建议适量摄入响应物质"
            }
            value < standardValue -> {
                return  "对应营养物质基本满足营养需求。建议减少摄入"
            }
            value < 1.2*standardValue -> {
                return  "建议少摄入或者不摄入相应的营养物质"
            }
            value < 1.5*standardValue -> {
                return  "强烈建议当天不再摄入富含该营养物质的食物"
            }
            else -> {
                return "严重过量，严重警告"
            }
        }
    }


}