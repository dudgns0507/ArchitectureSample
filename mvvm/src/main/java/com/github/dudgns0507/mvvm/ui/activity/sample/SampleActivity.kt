package com.github.dudgns0507.mvvm.ui.activity.sample

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.databinding.ActivitySampleBinding
import com.github.dudgns0507.mvvm.ui.activity.navigation.NavigationActivity
import com.github.dudgns0507.mvvm.ui.activity.posts.PostsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleActivity : BaseActivity<ActivitySampleBinding, SampleViewModel>() {
    override val layoutResId = R.layout.activity_sample
    override val viewModel: SampleViewModel by viewModels()

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, SampleActivity::class.java)
        }
    }

    override fun initBinding() {
        binding.apply {
            btnPosts.setOnClickListener {
                startActivity(PostsActivity.callingIntent(this@SampleActivity))
            }
            btnNavigation.setOnClickListener {
                startActivity(NavigationActivity.callingIntent(this@SampleActivity))
            }
        }
    }

    override fun initObserve() {
        viewModel.apply {
            lifecycleScope.launchWhenCreated {
            }
        }
    }

    override fun afterBinding() {
    }
}