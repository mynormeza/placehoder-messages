package com.example.placeholdermessages.presentation.ui.postdetails

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.placeholdermessages.R
import com.example.placeholdermessages.databinding.PostDetailsFragmentBinding
import com.example.placeholdermessages.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment : BaseFragment() {
    private var _binding: PostDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostDetailsViewModel by viewModels()
    private val args: PostDetailsFragmentArgs by navArgs()
    private lateinit var stringAdapter: ArrayAdapter<String>
    private var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = PostDetailsFragmentBinding.inflate(inflater, container, false)
        viewModel.getPost(args.postId)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            post.observe(viewLifecycleOwner) {
                setFavoriteIcon(it.isFavorite)
                binding.tvDescription.text = it.body
                binding.tvUsername.text = getString(R.string.username, it.username)
                binding.tvPhone.text = getString(R.string.user_phone, it.phone)
                binding.tvEmail.text = getString(R.string.user_email, it.email)
                binding.tvWebsite.text = getString(R.string.user_website, it.website)
                binding.rvComments.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, it.comments.map { c -> c.body })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_details, menu)

        this.menu = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                viewModel.toggle()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavoriteIcon(isFavorite: Boolean) {
        val resIcon = if (isFavorite) {
            R.drawable.ic_baseline_star_24
        } else {
            R.drawable.ic_baseline_star_outline_24
        }
        menu?.findItem(R.id.action_favorite)?.setIcon(resIcon)
    }
}
