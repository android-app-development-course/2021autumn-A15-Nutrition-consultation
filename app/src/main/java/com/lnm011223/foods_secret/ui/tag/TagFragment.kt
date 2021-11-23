package com.lnm011223.foods_secret.ui.tag

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.TagFragmentBinding

class TagFragment : Fragment() {

    private lateinit var tagViewModel: TagViewModel
    private var _binding: TagFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tagViewModel =
            ViewModelProvider(this).get(TagViewModel::class.java)

        _binding = TagFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}