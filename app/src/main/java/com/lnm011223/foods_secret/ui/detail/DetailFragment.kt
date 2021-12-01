package com.lnm011223.foods_secret.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.DetailFragmentBinding
import com.lnm011223.foods_secret.databinding.FragmentHomeBinding
import com.lnm011223.foods_secret.ui.home.HomeViewModel

class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel
    private var _binding: DetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel =
            ViewModelProvider(this).get(DetailViewModel::class.java)

        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}