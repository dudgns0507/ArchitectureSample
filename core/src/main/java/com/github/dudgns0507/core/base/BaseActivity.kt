package com.github.dudgns0507.core.base

import android.content.ComponentCallbacks
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.hardware.display.DisplayManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.github.dudgns0507.core.widget.LoadingDialog
import com.github.dudgns0507.core.util.ext.observe
import java.text.ParseException

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<out BaseEvent>> : AppCompatActivity() {
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
    private var loadingDialog: LoadingDialog? = null

    fun <B> initBundle(intent: Intent? = null): B? {
        return try {
            intent?.let {
                intent.getParcelableExtra(BUNDLE_KEY)
            } ?: kotlin.run {
                this.intent.getParcelableExtra(BUNDLE_KEY)
            }
        } catch (e: ParseException) {
            null
        }
    }

    /**
     * initBinding - For view initialize
     * initObserve - Set Observe for liveData or stateFlow
     * initListener - Set Listener for views
     * afterBinding - After view binding ex. loading data with viewModel
     */

    open fun beforeBinding() {}
    abstract fun initBinding()
    abstract fun initListener()
    abstract fun initObserve()
    abstract fun afterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beforeBinding()
        initDataBinding()
        initFoldListener()
    }

    private fun initFoldListener() {
        applicationContext?.registerComponentCallbacks(object : ComponentCallbacks {
            override fun onConfigurationChanged(newConfig: Configuration) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val dm =
                        applicationContext.getSystemService(Context.DISPLAY_SERVICE) as? DisplayManager
                    dm?.displays?.forEach {
                        it.mode.modeId
                        // mode: 1 펼친 상태
                        // mode: 2 접힌 상태
                    }
                }
            }

            override fun onLowMemory() {
            }
        })
    }

    private fun initDataBinding() {
        _binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.apply {
            lifecycleOwner = this@BaseActivity

            initBinding()
            initListener()
        }
        initObserve()
        initLoading()
        initError()
        afterBinding()
    }

    private fun initError() {
        observe(viewModel.error) {
            when (it) {
                CommonError.NetworkError -> error(BaseError.CODE_000)
                is CommonError.DataError -> error(BaseError.CODE_001)
                is CommonError.UnknownError -> error(BaseError.CODE_999)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    open fun onSignInResult(target: String) {}

    private fun initLoading() {
//        observe(viewModel.loading) {
//            when (it) {
//                true -> showLoading()
//                false -> hideLoading()
//            }
//        }
    }

    fun loading(state: Boolean) {
        viewModel.loading(state)
    }

//    private fun showLoading() {
//        if (isFinishing) {
//            return
//        }
//
//        if (loadingDialog != null && loadingDialog?.isAdded == true) {
//            supportFragmentManager.beginTransaction().remove(loadingDialog!!)
//        }
//        loadingDialog = LoadingDialog()
//        loadingDialog!!.show(supportFragmentManager, null)
//    }
//
//    private fun hideLoading() {
//        loadingDialog?.dismiss()
//    }
}