package com.github.dudgns0507.mvvm.ui.post.edit

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.core.util.ext.observe
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.databinding.ActivityPostEditBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostEditActivity : BaseActivity<ActivityPostEditBinding, PostEditViewModel>() {
    override val layoutResId = R.layout.activity_post_edit
    override val viewModel: PostEditViewModel by viewModels()
    private var bundle: Post? = null

    companion object {
        fun callingIntent(context: Context, post: Post): Intent {
            val intent = Intent(context, PostEditActivity::class.java)
            intent.putExtra(BUNDLE_KEY, post)
            return intent
        }
    }

    override fun viewBinding() {
        bundle = initBundle<Post>()
        binding.apply {
            bundle?.let {
                etTitle.setText(it.title)
                etBody.setText(it.body)
            }

            btSave.setOnClickListener {
                bundle?.let {
                    viewModel.patchPost(
                        it.id, Post(
                            title = etTitle.text.toString(),
                            body = etBody.text.toString()
                        )
                    )
                }
            }
        }
    }

    override fun registObserve() {
        viewModel.apply {
            observe(editPost) {
                Toast.makeText(this@PostEditActivity, "저장되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun loadData() {
    }
}