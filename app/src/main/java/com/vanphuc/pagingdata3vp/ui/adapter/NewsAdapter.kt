package com.vanphuc.pagingdata3vp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vanphuc.pagingdata3vp.BR
import com.vanphuc.pagingdata3vp.R
import com.vanphuc.pagingdata3vp.data.model.News
import com.vanphuc.pagingdata3vp.databinding.ItemNewsBinding

class NewsAdapter :
    PagingDataAdapter<News, NewsAdapter.NewsViewHolder<News>>(diffCallback = DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder<News> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemNewsBinding>(layoutInflater, R.layout.item_news, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder<News>, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class NewsViewHolder<T>(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
            oldItem.title == newItem.title

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
            oldItem == newItem
    }
}
