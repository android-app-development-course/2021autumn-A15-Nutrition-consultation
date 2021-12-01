package com.lnm011223.foods_secret.ui.home

import android.os.Bundle
import android.text.Html
import android.text.Html.fromHtml
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val news = "1.输入食物名称查询食物的营养信息\n" +
                "\n" +
                "2.选择食物分类查看该分类下的食物数据\n" +
                "\n" +
                "3.选择食物搭配查看营养是否均衡"
        binding.homeText.apply {
            maxLines = 5
            text = news
        }

    }
}