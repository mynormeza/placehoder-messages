package com.example.placeholdermessages.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.placeholdermessages.R
import com.example.placeholdermessages.databinding.HomeFragmentBinding
import com.example.placeholdermessages.presentation.base.BaseFragment
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
import com.example.placeholdermessages.presentation.ui.home.adapter.PostFragmentsAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {


    private  val viewModel: HomeViewModel by viewModels()

    private var _binding: HomeFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showProgress()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PostFragmentsAdapter(this)
        with(viewModel) {
            loadPosts()

            loadSuccessful.observe(viewLifecycleOwner) {
                if (it) {
                    binding.vpPosts.adapter = adapter
                    TabLayoutMediator(binding.tlPostsTabs, binding.vpPosts) { tab, position ->
                        tab.text = when(FilterPosts.values()[position]) {
                            FilterPosts.All -> getString(R.string.all_posts)
                            FilterPosts.FAVORITE -> getString(R.string.favorite_posts)
                        }
                    }.attach()
                    hideProgress()
                }
            }

        }
    }

}