package com.lnm011223.foods_secret.ui.tag

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.Tag
import com.lnm011223.foods_secret.TagAdapter
import com.lnm011223.foods_secret.databinding.TagFragmentBinding

class TagFragment : Fragment() {

    private lateinit var tagViewModel: FoodCategoryViewModel
    private var _binding: TagFragmentBinding? = null
    private val tagNameList = ArrayList<Tag>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tagViewModel =
            ViewModelProvider(this).get(FoodCategoryViewModel::class.java)

        _binding = TagFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initTag()
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.tagrecycleview.layoutManager = layoutManager
        val adapter = TagAdapter(tagNameList)
        binding.tagrecycleview.adapter = adapter

    }
    private fun initTag(){
        tagNameList.clear()
        tagNameList.add(Tag("谷类",R.drawable.gulei))
        tagNameList.add(Tag("干豆类",R.drawable.ganguo))
        tagNameList.add(Tag("蔬菜类",R.drawable.shucai))
        tagNameList.add(Tag("水果类",R.drawable.shuiguo))
        tagNameList.add(Tag("畜肉类",R.drawable.churou))
        tagNameList.add(Tag("禽肉类",R.drawable.qinrou))
        tagNameList.add(Tag("乳类",R.drawable.rulei))
        tagNameList.add(Tag("蛋类",R.drawable.dan))
        tagNameList.add(Tag("鱼虾蟹贝类",R.drawable.yuxia))
        tagNameList.add(Tag("油脂类",R.drawable.youzhi))
        tagNameList.add(Tag("薯类淀粉",R.drawable.shulei))
        tagNameList.add(Tag("坚果种子",R.drawable.jianguo))
    }
}