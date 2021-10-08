package com.github.dudgns0507.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.BaseAdapter
import com.github.dudgns0507.core.base.BaseDiffAdapter
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.mvvm.databinding.PostItemBinding
import com.github.dudgns0507.mvvm.ui.holder.PostViewHolder

class PostAdapter : BaseAdapter<Post, PostViewHolder>() {
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
        holder.bind(position, getItem(position))
    }
}