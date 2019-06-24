package com.abhinav.mmdb.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.model.NowPlaying
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.adapters.viewholders.NowPlayingItemViewHolder
import com.abhinav.mmdb.ui.adapters.viewholders.NowPlayingViewMoreItemViewHolder

class NowPlayingItemsAdapter(var onNowPlayingItemClick: (NowPlaying, Int) -> Unit) : RecyclerView.Adapter<NowPlayingItemViewHolder>() {

    companion object {
        private const val ITEM = 101
        private const val VIEW_MORE_ITEM = 102
    }

    private var nowPlayingItems = ArrayList<NowPlaying>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingItemViewHolder =
        when (viewType) {
            ITEM -> createNowPlayingItemViewHolder(parent)
            else -> createViewMoreItemViewHolder(parent)
        }

    private fun createViewMoreItemViewHolder(parent: ViewGroup): NowPlayingViewMoreItemViewHolder {
        val itemView = parent.inflate(R.layout.layout_now_playing_view_more)
        return NowPlayingViewMoreItemViewHolder(itemView)
    }

    private fun createNowPlayingItemViewHolder(parent: ViewGroup): NowPlayingItemViewHolder {
        val itemView = parent.inflate(R.layout.layout_now_playing)
        return NowPlayingItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return when (nowPlayingItems.size < 6) {
            true -> nowPlayingItems.size
            else -> 6
        }
    }

    override fun onBindViewHolder(holder: NowPlayingItemViewHolder, position: Int) {
        holder.bind(getItem(position), onNowPlayingItemClick)
    }

    private fun getItem(position: Int): NowPlaying =
        nowPlayingItems[position]

    override fun getItemViewType(position: Int) = when (position) {
        in 0..4 -> ITEM
        else -> VIEW_MORE_ITEM
    }

    fun updateItems(list: List<NowPlaying>) {
        nowPlayingItems.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    fun setListener(onNowPlayingItemClick: (NowPlaying, Int) -> Unit) {

    }
}