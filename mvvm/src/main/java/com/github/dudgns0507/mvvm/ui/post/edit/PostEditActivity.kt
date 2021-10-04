package com.github.dudgns0507.mvvm.ui.post.edit

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.core.util.ext.observe
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.data.model.ResponsePost
import com.github.dudgns0507.mvvm.data.model.RequestPostEdit
import com.github.dudgns0507.mvvm.databinding.ActivityPostEditBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostEditActivity : BaseActivity<ActivityPostEditBinding, PostEditBundle, PostEditViewModel>() {
    override val layoutResId = R.layout.activity_post_edit
    override val viewModel: PostEditViewModel by viewModels()

    companion object {
        fun callingIntent(context: Context, post: ResponsePost): Intent {
            val intent = Intent(context, PostEditActivity::class.java)
            intent.putExtra(BUNDLE_KEY, Gson().toJson(post))
            return intent
        }
    }

    override fun viewBinding() {
        binding.apply {
            bundle?.let {
                etTitle.setText(it.title)
                etBody.setText(it.body)
            }

            btSave.setOnClickListener {
                bundle?.let {
                    viewModel.patchPost(
                        it.id, RequestPostEdit(
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