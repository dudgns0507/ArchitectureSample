package com.github.dudgns0507.mvvm.ui.fragment.first

import androidx.fragment.app.viewModels
import com.github.dudgns0507.core.base.BaseFragment
import com.github.dudgns0507.mvvm.R
import com.github.dudgns0507.mvvm.databinding.FragmentFirstBinding

class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_first
    override val viewModel: FirstViewModel by viewModels()

    override fun initObserve() {
    }

    override fun initAfterBinding() {
    }
}