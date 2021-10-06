package com.github.dudgns0507.mvvm.ui.activity.samle_list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.github.dudgns0507.core.base.BaseActivity
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.databinding.ActivitySampleListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleListActivity : BaseActivity<ActivitySampleListBinding, SampleListViewModel>() {
    override val layoutResId = R.layout.activity_sample_list
    override val viewModel: SampleListViewModel by viewModels()

    companion object {
        fun callingIntent(context: Context): Intent {
            return Intent(context, SampleListActivity::class.java)
        }
    }

    override fun viewBinding() {
        binding.apply {
        }
    }

    override fun registObserve() {
        viewModel.apply {
            lifecycleScope.launchWhenCreated {
            }
        }
    }

    override fun loadData() {
    }
}