package com.lnm011223.foods_secret.ui.tag

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.TagFragmentBinding

class TagFragment : Fragment() {

    private lateinit var tagViewModel: FoodCategoryViewModel
    private var _binding: TagFragmentBinding? = null

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

        binding.card3.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.nav_detail, null))
    }
}