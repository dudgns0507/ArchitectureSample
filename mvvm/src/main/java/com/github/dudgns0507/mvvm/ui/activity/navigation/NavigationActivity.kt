package com.github.dudgns0507.mvvm.ui.activity.navigation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.databinding.ActivityNavigationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : BaseActivity<ActivityNavigationBinding, NavigationViewModel>() {
    override val layoutResId = R.layout.activity_navigation
    override val viewModel: NavigationViewModel by viewModels()

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, NavigationActivity::class.java)
        }
    }

    override fun initBinding() {
        binding.apply {
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