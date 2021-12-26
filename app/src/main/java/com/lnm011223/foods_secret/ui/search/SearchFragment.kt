package com.lnm011223.foods_secret.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.SearchFragmentBinding
import com.lnm011223.foods_secret.logic.network.FoodService
import com.lnm011223.foods_secret.logic.network.ServiceCreator
import kotlinx.android.synthetic.main.search_fragment.*


class SearchFragment : Fragment() {
    private lateinit var foodViewModel: FoodViewModel
    private var _binding: SearchFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        foodViewModel =
            ViewModelProvider(this).get(FoodViewModel::class.java)

        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        // 搜索按钮
        binding.searchButton.setOnClickListener {
            searchInfo()
        }
        // 【添加搭配】按钮
        binding.addButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_select, null))

        // 查看是否由食物分类功能跳转过来
        val foodName = arguments?.getString("foodName")
        if (foodName!=null) {
            if (foodName.toString().isNotEmpty()) {
                text_foodName.setText(foodName)
                searchInfo()
            }
        }

        // 观察食物名称是否变化
        foodViewModel.foodLiveData.observe(viewLifecycleOwner, Observer { result->
            val food = result.getOrNull()
            // 设置界面信息
            if (food != null) {
                view_fat.text = food.fat.toString()
                view_power.text = food.power.toString()
                view_protein.text = food.protein.toString()
                view_carbohydrate.text = food.carbohydrate.toString()
                view_diaryFiber.text = food.diaryFiber.toString()
            } else {
                Toast.makeText(activity, "查询失败",Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun searchInfo() {
        // 获取食物名称
        val foodName = text_foodName.text.toString()
        if (foodName.isNotEmpty()) {
            // 发起请求
            foodViewModel.getFoodInfo(foodName)
            binding.searchImage.visibility = View.GONE
            binding.addButton.visibility = View.VISIBLE
            binding.textList.visibility = View.VISIBLE
        }
    }


}