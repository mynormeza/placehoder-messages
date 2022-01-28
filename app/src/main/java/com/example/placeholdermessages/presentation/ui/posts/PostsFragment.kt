package com.example.placeholdermessages.presentation.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placeholdermessages.databinding.PostsFragmentBinding
import com.example.placeholdermessages.domain.model.Post
import com.example.placeholdermessages.presentation.base.BaseFragment
import com.example.placeholdermessages.presentation.ui.home.HomeFragmentDirections
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
import com.example.placeholdermessages.presentation.ui.posts.adapter.PostsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : BaseFragment() {
    companion object {
        const val POST_FILTER_EXTRA = "post_filter_extra"

        @JvmStatic
        fun newInstance(filter: FilterPosts) =
            PostsFragment().apply {
                arguments = Bundle().apply {
                    putInt(POST_FILTER_EXTRA, filter.value)
                }
            }
    }

    private val viewModel by viewModels<PostsViewModel>()
    private lateinit var postsAdapter: PostsAdapter
    private var _binding: PostsFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PostsFragmentBinding.inflate(inflater, container, false)
        postsAdapter = PostsAdapter(object : PostsAdapter.ClickListener {
            override fun onClickItem(post: Post) {
                val action = HomeFragmentDirections.actionHomeFragmentToPostDetailsFragment2(post.id)
                findNavController().navigate(action)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        val filterVal = requireArguments().getInt(POST_FILTER_EXTRA)
        val filter = FilterPosts.values().find { it.value == filterVal } ?: FilterPosts.All
        with(viewModel) {
            getPosts(filter)

            posts.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    binding.rvPosts.visibility = View.GONE
                    binding.tvEmptyMsg.visibility = View.VISIBLE
                } else {
                    postsAdapter.submitList(it)
                    binding.rvPosts.visibility = View.VISIBLE
                    binding.tvEmptyMsg.visibility = View.GONE
                }
            }

            failure.observe(viewLifecycleOwner) {
                hideProgress()
                showError(it)
            }
        }
    }

    private fun setupViews() {
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPosts.adapter = postsAdapter

        val swipeGesture = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = postsAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deletePost(item.id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(binding.rvPosts)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
