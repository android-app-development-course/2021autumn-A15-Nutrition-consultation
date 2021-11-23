package com.lnm011223.foods_secret.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import com.lnm011223.foods_secret.R
import com.lnm011223.foods_secret.databinding.FragmentGalleryBinding
import com.lnm011223.foods_secret.databinding.SearchFragmentBinding
import com.lnm011223.foods_secret.ui.gallery.GalleryViewModel

class SearchFragment : Fragment() {
    private lateinit var searchViewModel: SearchViewModel
    private var _binding: SearchFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}