package com.github.dudgns0507.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.BaseAdapter
import com.github.dudgns0507.core.base.BaseViewHolder
import com.github.dudgns0507.mvvm.databinding.PostItemBinding
import com.github.dudgns0507.mvvm.ui.adapter.Type.AA
import com.github.dudgns0507.mvvm.ui.adapter.Type.BB

data class A2(
    var data: String
)

data class B2(
    var data: String
)

sealed class Type2 {
    class AA(val data: A2) : Type2()
    class BB(val data: B2) : Type2()
}

class AA2ViewHolder(
    private val binding: PostItemBinding,
) : BaseViewHolder<PostItemBinding, Type2>(binding) {

    override fun bind(position: Int, item: Type2) = with(binding) {
        when(item) {
            is Type2.AA -> {
                // AA일때 뷰 세팅
            }
            else -> {
                // BB일때 뷰 세팅
            }
        }
    }
}

class Sample2Adapter : BaseAdapter<Type2, AA2ViewHolder>() {
    val listSize get() = super.getItemCount()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AA2ViewHolder {
        return AA2ViewHolder(
            PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AA2ViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }
}