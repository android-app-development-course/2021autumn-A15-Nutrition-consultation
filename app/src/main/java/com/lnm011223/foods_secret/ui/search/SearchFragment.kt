package com.lnm011223.foods_secret.ui.search

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.transition.Visibility
import com.lnm011223.foods_secret.R

import com.lnm011223.foods_secret.databinding.SearchFragmentBinding
import com.lnm011223.foods_secret.ui.food.FoodViewModel
import com.lnm011223.foods_secret.ui.select.SelectFragment
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
        binding.searchButton.setOnClickListener {
            binding.searchImage.visibility = View.GONE
            binding.addButton.visibility = View.VISIBLE
            binding.textList.visibility = View.VISIBLE

        }
        binding.addButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_select, null))
    }


}