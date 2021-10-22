package com.github.dudgns0507.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.BaseDiffAdapter
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.mvvm.databinding.LoadingItemBinding
import com.github.dudgns0507.mvvm.databinding.PostItemBinding
import com.github.dudgns0507.mvvm.ui.holder.LoadingViewHolder
import com.github.dudgns0507.mvvm.ui.holder.PostViewHolder

/**
 * BaseAdapter
 *
 * Default RecyclerView adapter.
 *
 * getItem(position: Int) - get item with position
 * updateList(list: List<T>) - change adapter list to parameter list
 * addList(list: List<T>) - append parameter list to adapter list
 * clearList() - clear adapter list
 *
 *
 * BaseDiffAdapter
 *
 * DiffUtil only refreshes modified data.
 * Use DiffUtil to increase loading performance.
 *
 *
 * Default methods are identical to BaseAdapter's.
 *
 * isNewItem(oldItem: T, newItem: T): Boolean
 * isNewContent(oldItem: T, newItem: T): Boolean
 *
 * The above two methods must be overridden for DiffUtil to function.
 *
 */

class PostAdapter : BaseDiffAdapter<Post, PostViewHolder>() {
    companion object {
        private const val POST_VIEW = 0
        private const val LOADING_VIEW = 1
    }

    private var isLoading = false
    lateinit var onItemClickListener: OnItemClickListener<Post>
    val listSize get() = super.getItemCount()

    /**
     * You can use multiple ViewHolders with ViewType
     * Define condition in getItemViewType ex. item id or position
     *
     * I create loading view and put in last position
     */

    override fun getItemViewType(position: Int): Int {
        return when {
            position < listSize -> POST_VIEW
            else -> LOADING_VIEW
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    fun showLoading() {
        isLoading = true
        notifyItemChanged(itemCount - 1)
    }

    fun hideLoading() {
        isLoading = false
        notifyItemChanged(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            POST_VIEW -> PostViewHolder(
                PostItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onItemClickListener
            )
            else -> LoadingViewHolder(
                LoadingItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostViewHolder -> {
                holder.bind(position, getItem(position))
            }
            is LoadingViewHolder -> {
                holder.bind(position, isLoading)
            }
        }
    }

    override fun isNewItem(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun isNewContent(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}
