package com.github.dudgns0507.mvvm.ui.post

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.core.util.ext.observe
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.databinding.ActivityPostBinding
import com.github.dudgns0507.mvvm.ui.post.edit.PostEditActivity
import com.github.dudgns0507.mvvm.ui.post.edit.PostEditBundle
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : BaseActivity<ActivityPostBinding, PostViewModel>() {
    override val layoutResId = R.layout.activity_post
    override val viewModel: PostViewModel by viewModels()
    private var bundle: Post? = null

    private lateinit var dialog: Dialog

    private lateinit var commentAdapter: CommentAdapter

    companion object {
        fun callingIntent(context: Context, post: Post): Intent {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra(BUNDLE_KEY, post)
            return intent
        }
    }

    override fun viewBinding() {
        bundle = initBundle<Post>()
        binding.apply {
            viewModel.bundle = bundle

            commentAdapter = CommentAdapter()
            val layoutManager = LinearLayoutManager(this@PostActivity)

            rvComment.layoutManager = layoutManager
            rvComment.setHasFixedSize(true)
            rvComment.adapter = commentAdapter

            btDelete.setOnClickListener {
                dialog = AlertDialog.Builder(this@PostActivity)
                    .setMessage(R.string.check_delete)
                    .setPositiveButton("확인") { _, _ ->
                        viewModel.bundle?.let {
                            viewModel.onEvent(PostDetailEvents.Delete(it.id))
                        }
                    }
                    .setNegativeButton("취소") { d, _ ->
                        d.dismiss()
                    }
                    .create()
                dialog.show()
            }

            btEdit.setOnClickListener {
                viewModel.bundle?.let {
                    startActivity(PostEditActivity.callingIntent(this@PostActivity, it))
                }
            }
        }
    }

    override fun registObserve() {
        viewModel.apply {
            observe(post) {
                binding.apply {
                    tvTitle.text = it.title
                    tvDescription.text = it.body
                }
            }

            observe(comments) {
                commentAdapter.submitList(it)
            }

            observe(deletePost) {
                Toast.makeText(this@PostActivity, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                finish()
            }

            observe(error) {
                Toast.makeText(this@PostActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun loadData() {
        viewModel.apply {
            bundle?.let {
                onEvent(PostDetailEvents.Read(it.id))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}