package com.lnm011223.foods_secret.ui.select

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.SearchFragmentBinding
import com.lnm011223.foods_secret.databinding.SelectFragmentBinding
import com.lnm011223.foods_secret.ui.search.SearchViewModel

class SelectFragment : Fragment() {
    private lateinit var selectViewModel: SelectViewModel
    private var _binding: SelectFragmentBinding? = null

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

}