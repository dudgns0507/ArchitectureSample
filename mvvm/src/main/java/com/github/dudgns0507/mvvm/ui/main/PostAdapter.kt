package com.github.dudgns0507.mvvm.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.mvvm.data.model.ResponsePost
import com.github.dudgns0507.mvvm.databinding.PostItemBinding

class PostAdapter(
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<PostViewHolder>() {
    private val posts = arrayListOf<ResponsePost>()

    private val onItemClickListener: OnItemClickListener<ResponsePost> = object : OnItemClickListener<ResponsePost> {
        override fun onItemClicked(position: Int, item: ResponsePost) {
            viewModel.openPostDetail(item)
        }
    }

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

    fun addAll(p: List<ResponsePost>) {
        posts.addAll(p)
        notifyDataSetChanged()
    }
}