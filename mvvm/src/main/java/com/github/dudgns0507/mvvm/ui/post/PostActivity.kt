package com.github.dudgns0507.mvvm.ui.post

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.core.util.ext.observe
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.data.model.ResponsePost
import com.github.dudgns0507.mvvm.databinding.ActivityPostBinding
import com.github.dudgns0507.mvvm.ui.post.edit.PostEditActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : BaseActivity<ActivityPostBinding, PostBundle, PostViewModel>() {
    override val layoutResId = R.layout.activity_post
    override val viewModel: PostViewModel by viewModels()

    private lateinit var dialog: Dialog

    private lateinit var commentAdapter: CommentAdapter

    companion object {
        fun callingIntent(context: Context, post: ResponsePost): Intent {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra(BUNDLE_KEY, Gson().toJson(post))
            return intent
        }
    }

    override fun viewBinding() {
        binding.apply {
            viewModel.bundle = Gson().fromJson(intent.getStringExtra(BUNDLE_KEY), ResponsePost::class.java)

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
                            viewModel.deletePost(it.id)
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
                commentAdapter.addAll(it)
            }

            observe(deletePost) {
                Toast.makeText(this@PostActivity, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                finish()
            }
        }
    }

    override fun loadData() {
        viewModel.apply {
            bundle?.let {
                requestPost(it.id)
                requestComments(it.id)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}