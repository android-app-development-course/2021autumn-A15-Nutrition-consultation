package com.lnm011223.foods_secret.ui.select

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lnm011223.foods_secret.FoodAdapter
import com.lnm011223.foods_secret.FoodSelectAdapter
import com.lnm011223.foods_secret.MainActivity
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.SelectFragmentBinding
import com.lnm011223.foods_secret.logic.model.Food


class SelectFragment : Fragment() {
    private lateinit var selectViewModel: SelectViewModel
    private var _binding: SelectFragmentBinding? = null
    // 食物搭配集合
    private var foodMap:HashSet<Food> = HashSet()
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
        binding.analyseButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_analyse, null))

        // 将新的食物添加到搭配中
        val food = arguments?.getParcelable<Food>("food")
        if (food != null) {
            selectViewModel.foodMap_vm.add(food)
            Log.i("TEST", "onActivityCreated: $food")
        }
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        binding.selectView.layoutManager = layoutManager
        val adapter = FoodSelectAdapter(selectList)
        binding.selectView.adapter = adapter
        selectList.addAll(selectViewModel.foodMap_vm)
        adapter.notifyDataSetChanged()
    }

}