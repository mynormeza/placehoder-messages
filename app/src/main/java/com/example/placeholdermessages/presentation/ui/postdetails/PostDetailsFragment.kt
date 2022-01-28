package com.example.placeholdermessages.presentation.ui.postdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.placeholdermessages.R
import com.example.placeholdermessages.databinding.HomeFragmentBinding
import com.example.placeholdermessages.presentation.base.BaseFragment

class PostDetailsFragment : BaseFragment() {
    private var _binding: HomeFragmentBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PostDetailsFragment()
    }

    private val viewModel: PostDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
