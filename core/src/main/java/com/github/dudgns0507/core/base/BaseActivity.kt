package com.github.dudgns0507.core.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import java.lang.Exception

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {
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

    private var _binding: T? = null
    val binding get() = _binding!!

    abstract val layoutResId: Int
    abstract val viewModel: V

    val ctx: Context by lazy { this }

    fun<B> initBundle(): B? {
        return try {
            intent.getParcelableExtra(BUNDLE_KEY)
        } catch (e: Exception) {
            null
        }
    }

    /**
     * initBinding - For view initialize
     * initObserve - Set Observe for liveData or stateFlow
     * afterBinding - After view binding ex. loading data with viewModel
     */

    abstract fun initBinding()
    abstract fun initObserve()
    abstract fun afterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
    }

    private fun initDataBinding() {
        _binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.apply {
            lifecycleOwner = this@BaseActivity

            initBinding()
        }
        initObserve()
        afterBinding()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}