package com.example.placeholdermessages.presentation.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.placeholdermessages.presentation.ui.posts.PostsFragment

class PostFragmentsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = FilterPosts.values().size

    override fun createFragment(position: Int) = PostsFragment.newInstance(FilterPosts.values()[position])
}
