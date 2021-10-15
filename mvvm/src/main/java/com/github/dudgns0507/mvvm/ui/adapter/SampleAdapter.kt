package com.github.dudgns0507.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.BaseAdapter
import com.github.dudgns0507.core.base.BaseViewHolder
import com.github.dudgns0507.mvvm.databinding.PostItemBinding
import com.github.dudgns0507.mvvm.ui.adapter.Type.AA
import com.github.dudgns0507.mvvm.ui.adapter.Type.BB

data class A(
    var data: String
)

data class B(
    var data: String
)

sealed class Type {
    class AA(val data: A) : Type()
    class BB(val data: B) : Type()
}

class AAViewHolder(
    private val binding: PostItemBinding,
) : BaseViewHolder<PostItemBinding, AA>(binding) {

    override fun bind(position: Int, item: AA) = with(binding) {
    }
}

class BBViewHolder(
    private val binding: PostItemBinding,
) : BaseViewHolder<PostItemBinding, BB>(binding) {

    override fun bind(position: Int, item: BB) = with(binding) {
    }
}

class SampleAdapter : BaseAdapter<Type, RecyclerView.ViewHolder>() {
    companion object {
        private const val A_VIEW = 0
        private const val B_VIEW = 1
    }

    val listSize get() = super.getItemCount()

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is AA -> A_VIEW
            else -> B_VIEW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            A_VIEW -> AAViewHolder(
                PostItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> BBViewHolder(
                PostItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AAViewHolder -> {
                holder.bind(position, getItem(position) as AA)
            }
            is BBViewHolder -> {
                holder.bind(position, getItem(position) as BB)
            }
        }
    }
}