package com.abhinav.mmdb.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.model.TrendingItem
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.adapters.viewholders.TrendingItemsViewHolder

class TrendingItemsAdapter(val onTrendingItemClick: (TrendingItem, Int, View) -> Unit) : RecyclerView.Adapter<TrendingItemsViewHolder>() {

    private val trendingItems = ArrayList<TrendingItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingItemsViewHolder {
        val view = parent.inflate(R.layout.layout_trending_item)
        return TrendingItemsViewHolder(view, onTrendingItemClick)
    }

    override fun getItemCount() = trendingItems.size

    override fun onBindViewHolder(holder: TrendingItemsViewHolder, position: Int) {
        holder.bind(trendingItems[position])
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun updateItems(trendingItems: List<TrendingItem>) {
        with(this.trendingItems){
            clear()
            addAll(trendingItems)
        }
        notifyDataSetChanged()
    }

}
