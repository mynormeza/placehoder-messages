package com.example.placeholdermessages.presentation.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import com.example.placeholdermessages.R
import com.example.placeholdermessages.core.Failure
import com.example.placeholdermessages.databinding.HomeFragmentBinding
import com.example.placeholdermessages.presentation.base.BaseFragment
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
import com.example.placeholdermessages.presentation.ui.home.adapter.PostFragmentsAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val viewModel: HomeViewModel by viewModels()

    private var _binding: HomeFragmentBinding? = null

    private val binding get() = _binding!!
    private lateinit var adapter: PostFragmentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        adapter = PostFragmentsAdapter(this)
        binding.vpPosts.adapter = adapter
        TabLayoutMediator(binding.tlPostsTabs, binding.vpPosts) { tab, position ->
            tab.text = when (FilterPosts.values()[position]) {
                FilterPosts.All -> getString(R.string.all_posts)
                FilterPosts.FAVORITE -> getString(R.string.favorite_posts)
            }
        }.attach()
        viewModel.loadPosts()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showProgress()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vpPosts.isUserInputEnabled = false

        binding.fab.setOnClickListener { _ ->
            viewModel.deleteAllPosts()
        }

        with(viewModel) {

            loadSuccessful.observe(viewLifecycleOwner) {
                if (it) {
                    binding.vpPosts.visibility = View.VISIBLE
                    binding.tlPostsTabs.visibility = View.VISIBLE

                    hideProgress()
                }
            }

            failure.observe(viewLifecycleOwner) {
                if (it == Failure.NetworkConnection) {
                    binding.tlPostsTabs.visibility = View.GONE
                    binding.vpPosts.visibility = View.GONE
                    binding.tvNoNetwork.visibility = View.VISIBLE
                    hideProgress()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_reload -> {
                binding.tlPostsTabs.visibility = View.GONE
                binding.vpPosts.visibility = View.GONE
                showProgress()
                viewModel.loadPosts(true)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
