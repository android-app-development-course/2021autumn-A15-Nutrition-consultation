package com.lnm011223.foods_secret.ui.select

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lnm011223.foods_secret.FoodSelectAdapter
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.SelectFragmentBinding
import com.lnm011223.foods_secret.logic.model.Food


class SelectFragment : Fragment() {
    private lateinit var selectViewModel: SelectViewModel
    private var _binding: SelectFragmentBinding? = null
    private val selectList = ArrayList<Food>()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        selectViewModel =
            ViewModelProvider(activity!!).get(SelectViewModel::class.java)

        _binding = SelectFragmentBinding.inflate(inflater, container, false)
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
        // 分析搭配按钮，将选中的食物发送给分析界面
        binding.analyseButton.setOnClickListener {
            val selectedFoodList:ArrayList<Food> = selectViewModel.foodSet.filter { i-> i.isSelected } as ArrayList<Food>
            val bundle = Bundle()
            bundle.putSerializable("foodList", selectedFoodList)
            it.findNavController().navigate(R.id.nav_analyse, bundle)
        }

        // 将新的食物添加到搭配中
        val food = arguments?.getParcelable<Food>("food")
        if (food != null) {
            selectViewModel.foodSet.add(food)
            Log.i("TEST", "onActivityCreated: $food")
        }
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.selectView.layoutManager = layoutManager
        val adapter = FoodSelectAdapter(selectList)
        binding.selectView.adapter = adapter
        selectList.clear()
        selectList.addAll(selectViewModel.foodSet)
        adapter.notifyDataSetChanged()
    }

}