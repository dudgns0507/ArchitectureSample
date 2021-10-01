package com.github.dudgns0507.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    companion object {
        protected val TAG: String by lazy {
            val name = this::class.java.simpleName
            name.substring(name.lastIndexOf(".") + 1)
                .apply {
                    if (length > 23) {
                        replace("Fragment", "")
                    }
                }
        }
    }

    lateinit var binding: T
    lateinit var act: AppCompatActivity
    lateinit var baseAct: BaseActivity<*, *, *>

    abstract val layoutResId: Int
    abstract val viewModel: BaseViewModel

    abstract fun initObserve()
    abstract fun initAfterBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        act = requireActivity() as AppCompatActivity

        try {
            baseAct = requireActivity() as BaseActivity<*, *, *>
        } catch (e: Exception) {
            
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initDataBinding(inflater, container)
        initAfterBinding()
        initObserve()

        return binding.root
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.apply {
            lifecycleOwner = this@BaseFragment
        }
    }
}