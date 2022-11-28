package com.vanphuc.pagingdata3vp.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {
    lateinit var viewBinding: VB
    abstract val viewModel: VM

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (::viewBinding.isInitialized.not()) {
            viewBinding = DataBindingUtil.setContentView(this, layoutId)
        }
        initViews()
        initHandleObserver()
    }

    abstract fun initViews()

    open fun initHandleObserver() {
        viewModel.errorMessage.observe(this) {

        }
    }
}