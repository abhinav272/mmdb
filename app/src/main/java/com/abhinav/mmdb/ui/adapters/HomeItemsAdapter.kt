package com.abhinav.mmdb.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.model.NowPlaying
import com.abhinav.mmdb.data.model.TrendingItem
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.adapters.viewholders.HomeItemsViewHolder
import com.abhinav.mmdb.ui.adapters.viewholders.TrendingListViewHolder

class HomeItemsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TRENDING_ITEMS = 101
    val NOW_PLAYING = 102
    val UPCOMING = 103
    val SUGGESTION = 104

    private val trendingItems = ArrayList<TrendingItem>()
    private val nowPlayingItems = ArrayList<NowPlaying>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TRENDING_ITEMS -> createTrendingItemsViewHolder(parent)
            else -> createNowPlayingItemsViewHolder(parent)
        }
    }

    private fun createNowPlayingItemsViewHolder(parent: ViewGroup): HomeItemsViewHolder {
        val itemView = parent.inflate(R.layout.layout_now_playing)
        return HomeItemsViewHolder(itemView)
    }

    private fun createTrendingItemsViewHolder(parent: ViewGroup): TrendingListViewHolder {
        val itemView = parent.inflate(R.layout.layout_trending_list)
//        val trendingListViewHolder = TrendingListViewHolder(itemView)
        return TrendingListViewHolder(itemView)
    }

    override fun getItemCount() = nowPlayingItems.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TrendingListViewHolder -> {
                holder.bind(trendingItems)
            }
            is HomeItemsViewHolder -> {
                holder.bind(getNowPlayingItem(position))
            }
        }
    }

    private fun getNowPlayingItem(position: Int): NowPlaying {
        return nowPlayingItems[position-1]
    }

    override fun getItemViewType(position: Int) = when (position) {
        0 -> TRENDING_ITEMS
        else -> NOW_PLAYING
    }

    fun onTrendingItemsLoaded(trendingItems: List<TrendingItem>?) {
        this.trendingItems.clear()
        trendingItems?.let { this.trendingItems.addAll(it) }

    }

    fun onNowPlayingItemsLoaded(nowPlayingList: List<NowPlaying>?) {
        nowPlayingList?.let {
            this.nowPlayingItems.apply {
                clear()
                addAll(nowPlayingList)
            }
//            notifyItemRangeInserted(1, nowPlayingList.size)
            notifyDataSetChanged()
        }

    }
}
