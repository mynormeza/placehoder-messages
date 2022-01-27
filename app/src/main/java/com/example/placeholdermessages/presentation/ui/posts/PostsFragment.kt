package com.example.placeholdermessages.presentation.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.placeholdermessages.databinding.PostsFragmentBinding
import com.example.placeholdermessages.presentation.base.BaseFragment
import com.example.placeholdermessages.presentation.myToast
import com.example.placeholdermessages.presentation.ui.home.adapter.FilterPosts
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

    private var _binding: PostsFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PostsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            getPosts()

            posts.observe(viewLifecycleOwner) {
                requireContext().myToast("Size: ${it.size}")
            }

            failure.observe(viewLifecycleOwner) {
                hideProgress()
                showError(it)
            }
        }
    }



}