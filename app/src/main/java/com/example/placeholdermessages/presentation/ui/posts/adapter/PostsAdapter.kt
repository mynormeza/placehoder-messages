package com.example.placeholdermessages.presentation.ui.posts.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.placeholdermessages.R
import com.example.placeholdermessages.domain.model.Post

class PostsAdapter(
    private val clickListener: ClickListener
) : ListAdapter<Post, PostsAdapter.ViewHolder>(DiffCallback()) {

    interface ClickListener {
        fun onClickItem(post: Post)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(currentList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    private class DiffCallback : DiffUtil.ItemCallback<Post>() {

        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(post: Post, clickListener: ClickListener) {
            itemView.setOnClickListener {
                clickListener.onClickItem(post)
            }
            val listName = itemView.findViewById(R.id.tv_title) as TextView
            val isReadView = itemView.findViewById(R.id.v_unread_dot) as View
            val isFavoriteView = itemView.findViewById(R.id.iv_favorite) as ImageView
            isReadView.visibility = if (post.isRead) {
                View.GONE
            } else {
                View.VISIBLE
            }

            isFavoriteView.visibility = if (post.isFavorite) {
                View.VISIBLE
            } else {
                View.GONE
            }
            listName.text = post.title
        }
    }
}