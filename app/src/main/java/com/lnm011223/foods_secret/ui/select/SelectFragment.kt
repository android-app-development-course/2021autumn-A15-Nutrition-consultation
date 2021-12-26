package com.lnm011223.foods_secret.ui.select

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.SelectFragmentBinding
import com.lnm011223.foods_secret.logic.model.Food


class SelectFragment : Fragment() {
    private lateinit var selectViewModel: SelectViewModel
    private var _binding: SelectFragmentBinding? = null
    // 食物搭配集合
    private var foodMap:HashSet<Food> = HashSet()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        selectViewModel =
            ViewModelProvider(this).get(SelectViewModel::class.java)

        _binding = SelectFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.analyseButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_analyse, null))

        // 将新的食物添加到搭配中
        val food = arguments?.getParcelable<Food>("food")
        if (food != null) {
            foodMap.add(food)
            Log.i("TEST", "onActivityCreated: $food")
        }
    }

}