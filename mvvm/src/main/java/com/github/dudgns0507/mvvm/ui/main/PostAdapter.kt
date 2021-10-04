package com.github.dudgns0507.mvvm.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.mvvm.databinding.PostItemBinding

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {
    private val posts = arrayListOf<Post>()

    lateinit var onItemClickListener: OnItemClickListener<Post>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(position, posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun addAll(p: List<Post>) {
        posts.addAll(p)
        notifyDataSetChanged()
    }
}