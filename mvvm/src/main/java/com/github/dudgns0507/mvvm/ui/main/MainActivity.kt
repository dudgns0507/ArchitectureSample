package com.github.dudgns0507.mvvm.ui.main

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.core.util.ext.observe
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.databinding.ActivityMainBinding
import com.github.dudgns0507.mvvm.ui.post.PostActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainBundle, MainViewModel>() {
    override val layoutResId = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private lateinit var postAdapter: PostAdapter

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun viewBinding() {
        binding.apply {
            postAdapter = PostAdapter().apply {
                onItemClickListener = object : OnItemClickListener<Post> {
                    override fun onItemClicked(position: Int, item: Post) {
                        openDetail(item)
                    }
                }
            }

            val layoutManager = LinearLayoutManager(this@MainActivity)
            rvPost.layoutManager = layoutManager
            rvPost.setHasFixedSize(true)
            rvPost.adapter = postAdapter
            rvPost.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                    if (layoutManager.itemCount <= lastVisibleItem + 2) {
                        viewModel.onEvent(PostsEvent.ReadMore)
                    }
                }
            })
        }
    }

    private fun openDetail(item: Post) {
        startActivity(PostActivity.callingIntent(this, item))
    }

    override fun registObserve() {
        viewModel.apply {
            observe(state) { posts ->
                postAdapter.addAll(posts.posts)
            }
        }
    }

    override fun loadData() {
    }

    override fun onResume() {
        super.onResume()

        viewModel.onEvent(PostsEvent.ReadFirst)
    }
}