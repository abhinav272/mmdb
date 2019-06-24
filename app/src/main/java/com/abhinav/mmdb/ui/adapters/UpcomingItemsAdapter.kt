package com.abhinav.mmdb.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.model.NowPlaying
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.adapters.viewholders.NowPlayingItemViewHolder
import com.abhinav.mmdb.ui.adapters.viewholders.NowPlayingViewMoreItemViewHolder

class UpcomingItemsAdapter : RecyclerView.Adapter<NowPlayingItemViewHolder>() {

    companion object {
        private const val ITEM = 101
        private const val VIEW_MORE_ITEM = 102
    }

    private var upcomingItems = ArrayList<NowPlaying>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingItemViewHolder =
        when (viewType) {
            ITEM -> createNowPlayingItemViewHolder(parent)
            else -> createViewMoreItemViewHolder(parent)
        }


    private fun createViewMoreItemViewHolder(parent: ViewGroup): NowPlayingViewMoreItemViewHolder {
        val itemView = parent.inflate(R.layout.layout_upcoming_view_more)
        return NowPlayingViewMoreItemViewHolder(itemView)
    }

    private fun createNowPlayingItemViewHolder(parent: ViewGroup): NowPlayingItemViewHolder {
        val itemView = parent.inflate(R.layout.layout_upcoming)
        return NowPlayingItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return when (upcomingItems.size < 9) {
            true -> upcomingItems.size
            else -> 9
        }
    }

    override fun onBindViewHolder(holder: NowPlayingItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): NowPlaying =
        upcomingItems[position]

    override fun getItemViewType(position: Int) = when (position) {
        in 0..7 -> ITEM
        else -> VIEW_MORE_ITEM
    }

    fun updateItems(list: List<NowPlaying>) {
        upcomingItems.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }
}
