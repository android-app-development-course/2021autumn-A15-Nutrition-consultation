package com.lnm011223.foods_secret.ui.detail

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.RecyclerView




/**
 * 某个分类下的食物列表页
 */
class DetailFragment : Fragment() {
    var foodCategoryName = ""
    private lateinit var detailViewModel: DetailViewModel
    private var _binding: DetailFragmentBinding? = null
    var page_num = 2
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

        foodCategoryName = view_foodCategoryName.text.toString()
        if (foodCategoryName.isNotEmpty()) {
            // 发起请求获取食物列表
            detailViewModel.getFoodList(foodCategoryName, 1)
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



        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                //Log.i("page_test", "--------------------------------------")
                if (binding.recyclerView.canScrollVertically(1)) {
                    //Log.i(TAG, "direction 1: true")
                } else {
                    Log.i("page_test", "direction 1: false") //滑动到底部
                    page_num++
                    Log.i("page_test","page = $page_num")
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    jumptofirst()
                }
                if (binding.recyclerView.canScrollVertically(-1)) {
                    //Log.i(TAG, "direction -1: true")
                } else {
                    Log.i("page_test", "direction -1: false") //滑动到顶部
                    page_num--
                    Log.i("page_test","page = $page_num")
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    jumptofirst()
                }
            }
        })

    }

    private fun jumptofirst(){
        when(foodCategoryName) {
            //max=5
            "谷类" ->{
                Log.d("page_test",foodCategoryName)
                if (page_num>5) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max=4
            "干豆类" ->{
                Log.d("page_test",foodCategoryName)
                if (page_num>4) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max==12
            "蔬菜类"->{
                Log.d("page_test",foodCategoryName)
                if (page_num>12) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max=9
            "水果类"->{
                Log.d("page_test",foodCategoryName)
                if (page_num>9) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max=7
            "畜肉类"->{
                Log.d("page_test",foodCategoryName)
                if (page_num>7) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max=3
            "禽肉类"->{
                Log.d("page_test",foodCategoryName)
                if (page_num>3) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max=2
            "乳类"->{
                Log.d("page_test",foodCategoryName)
                if (page_num>2) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max=2
            "蛋类"->{
                Log.d("page_test",foodCategoryName)
                if (page_num>2) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max=8
            "鱼虾蟹贝"->{
                Log.d("page_test",foodCategoryName)
                if (page_num>8) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max=2
            "油脂类"->{
                Log.d("page_test",foodCategoryName)
                if (page_num>2) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
            //max=1
            "薯类淀粉"->{

            }
            //max=3
            "坚果种子"->{
                Log.d("page_test",foodCategoryName)
                if (page_num>3) {
                    page_num = 1
                    detailViewModel.getFoodList(foodCategoryName, page_num)
                    Log.d("page_test", "$foodCategoryName jump succeed")
                }
            }
        }
    }
}