package com.lnm011223.foods_secret.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.livedata.core.ktx.R
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lnm011223.foods_secret.FoodAdapter
import com.lnm011223.foods_secret.databinding.DetailFragmentBinding
import com.lnm011223.foods_secret.logic.model.Food
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private var _binding: DetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val foodList = ArrayList<Food>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel =
            ViewModelProvider(this).get(DetailViewModel::class.java)

        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        initFruits() // 初始化水果数据
        val tagname = arguments?.getString("tagname")
        binding.viewFoodCategoryName.text = tagname
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = FoodAdapter(foodList)
        binding.recyclerView.adapter = adapter

        val foodCategoryName = view_foodCategoryName.text.toString()
        if (foodCategoryName.isNotEmpty()) {
            // 发起请求获取食物列表
            detailViewModel.getFoodList(foodCategoryName)
        }
        // 查看请求响应
        detailViewModel.foodListLiveData.observe(viewLifecycleOwner, {
            val response = it.getOrNull()
            // 设置新数据
            if (response != null) {
                foodList.clear()
                foodList.addAll(response)
                adapter.notifyDataSetChanged()
            }
        })

        activity?.apply {
            app_bar_main?.toolbar?.setBackgroundColor(Color.parseColor("#ECCE5F"))
            window.statusBarColor = Color.parseColor("#ECCE5F")
        }


    }


}