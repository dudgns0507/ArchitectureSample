package com.github.dudgns0507.mvvm.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.mvvm.data.model.ResponseComment
import com.github.dudgns0507.mvvm.databinding.CommentItemBinding

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {
    private val comments = arrayListOf<ResponseComment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            CommentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(position, comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun addAll(c: List<ResponseComment>) {
        comments.clear()
        comments.addAll(c)
        notifyDataSetChanged()
    }
}
