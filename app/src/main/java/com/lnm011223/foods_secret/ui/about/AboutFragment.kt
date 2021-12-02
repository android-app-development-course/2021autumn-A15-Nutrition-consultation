package com.lnm011223.foods_secret.ui.about

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.AboutFragmentBinding
import com.lnm011223.foods_secret.databinding.SearchFragmentBinding
import com.lnm011223.foods_secret.ui.search.SearchViewModel

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel
    private var _binding: AboutFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProvider(this).get(AboutViewModel::class.java)

        _binding = AboutFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.githubUrl.text = "Github项目地址：\n\n"+"https://github.com/android-app-development-course/2021autumn-A15-Nutrition-consultation"
        binding.groupName.text = "陈霖：\n\n"+"梁诺明：\n\n"+"黄景泰：\n\n"+"钱伯韬：\n\n"
    }
}