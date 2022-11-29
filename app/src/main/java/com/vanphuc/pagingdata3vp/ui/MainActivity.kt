package com.vanphuc.pagingdata3vp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.room.Room
import com.vanphuc.pagingdata3vp.R
import com.vanphuc.pagingdata3vp.data.datasource.local.AppDatabase
import com.vanphuc.pagingdata3vp.databinding.ActivityMainBinding
import com.vanphuc.pagingdata3vp.ui.adapter.ExampleLoadStateAdapter
import com.vanphuc.pagingdata3vp.ui.adapter.NewsAdapter
import com.vanphuc.pagingdata3vp.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()
    override val layoutId: Int = R.layout.activity_main

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getNews("apple")
    }

    override fun initViews() {
        viewBinding.apply {
            recyclerNews.adapter = newsAdapter.withLoadStateHeaderAndFooter(
                header = ExampleLoadStateAdapter(newsAdapter::retry),
                footer = ExampleLoadStateAdapter(newsAdapter::retry),
            )
            pullToRequest.setOnRefreshListener {
                newsAdapter.refresh()
            }
        }
    }

    override fun initHandleObserver() {
        super.initHandleObserver()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.apply {
                    userItemsUiStates?.collectLatest {
                        newsAdapter.submitData(it)
                        viewBinding.pullToRequest.isRefreshing = false
                    }
                }
            }
        }
    }
}