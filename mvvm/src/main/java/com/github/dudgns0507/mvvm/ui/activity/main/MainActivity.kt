package com.github.dudgns0507.mvvm.ui.activity.main

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.databinding.ActivityMainBinding
import com.github.dudgns0507.mvvm.ui.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResId = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private lateinit var postAdapter: PostAdapter

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun initBinding() {
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
                    if (!rvPost.canScrollVertically(1)) {
                        val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                        val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                        if (lastVisibleItemPosition == itemTotalCount) {
                            postAdapter.hideLoading()
                            viewModel.onEvent(MainPostsEvent.ReadMore)
                        }
                    }
                }
            })
        }
    }

    private fun openDetail(item: Post) {
    }

    /**
     *
     * StateFlow and LiveData is very similar.
     * Also both are almost same position in architecture.
     * So you can replace LiveData to StateFlow very easy.
     *
     * - Difference -
     * StateFlow need default value but LiveData doesn't.
     * LiveData is android library but StateFlow is kotlin library. So you can use in domain layer.
     * StateFlow can use coroutine to get more performance.
     * You can use StateFlow with other flow api.
     *
     * Observe StateFlow
     *
     * lifecycleScope.launchWhenCreated {
     *      postStates.collect { it ->
     *          update ui with it
     *      }
     * }
     *
     * Observe LiveData
     *
     * postData.observe(this, { it ->
     *      update ui with it
     * })
     *
     */

    override fun initObserve() {
        viewModel.apply {
            lifecycleScope.launchWhenCreated {
                postStates.collect { state ->
                    Log.w("Debug", "start : ${state.start}, isLoadFinish : ${state.isLoadFinish}")
                    if(state.posts.size > postAdapter.listSize) {
                        postAdapter.updateList(state.posts)
                        if(state.isLoadFinish) {
                            postAdapter.hideLoading()
                        } else {
                            postAdapter.showLoading()
                        }
                    }
                }
            }
        }
    }

    override fun afterBinding() {
    }
}