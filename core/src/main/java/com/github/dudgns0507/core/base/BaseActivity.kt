package com.github.dudgns0507.core.base

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, B : Parcelable, V : BaseViewModel> : AppCompatActivity() {
    companion object {
        const val BUNDLE_KEY = "data"
        protected val TAG: String by lazy {
            val name = this::class.java.simpleName
            name.substring(name.lastIndexOf(".") + 1)
                .apply {
                    if (length > 23) {
                        replace("Activity", "")
                    }
                }
        }
    }

    lateinit var binding: T

    abstract val layoutResId: Int
    abstract val viewModel: V

    val ctx: Context by lazy { this }

    val bundle: B? by lazy { initBundle() }

    private fun initBundle(): B? {
        return intent.getParcelableExtra(BUNDLE_KEY)
    }

    abstract fun viewBinding()
    abstract fun registObserve()
    abstract fun loadData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.apply {
            lifecycleOwner = this@BaseActivity

            viewBinding()
        }
        registObserve()
        loadData()
    }
}