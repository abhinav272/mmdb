package com.abhinav.mmdb.ui.adapters.viewholders

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.data.model.TrendingItem
import com.abhinav.mmdb.ui.adapters.TrendingItemsAdapter
import com.abhinav.mmdb.utils.StartSnapHelper
import kotlinx.android.synthetic.main.layout_trending_list.view.*

class TrendingListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(trendingItems: List<TrendingItem>) = with(itemView) {
        rv_trending_items.apply {
            layoutManager = LinearLayoutManager(rv_trending_items.context, LinearLayout.HORIZONTAL, false)
            adapter = TrendingItemsAdapter().apply { updateItems(trendingItems) }
            onFlingListener = null
        }

        val snapHelper = StartSnapHelper()
        snapHelper.attachToRecyclerView(rv_trending_items)
    }
}