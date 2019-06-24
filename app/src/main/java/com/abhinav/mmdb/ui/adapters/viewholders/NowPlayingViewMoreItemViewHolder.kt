package com.abhinav.mmdb.ui.adapters.viewholders

import android.view.View
import com.abhinav.mmdb.data.model.NowPlaying
import kotlinx.android.synthetic.main.layout_now_playing_view_more.view.*

class NowPlayingViewMoreItemViewHolder(
    itemView: View
) : NowPlayingItemViewHolder(itemView) {
    override fun bind(
        item: NowPlaying,
        onNowPlayingItemClick: (NowPlaying, Int) -> Unit
    ) {
        super.bind(item, onNowPlayingItemClick)
        with(itemView) {
            setOnClickListener {
                onNowPlayingItemClick(item, adapterPosition)
            }
            tv_view_more.visibility = View.VISIBLE
//            iv_movie_poster.setColorFilter(ContextCompat.getColor(context, R.color.black_60_alpha), PorterDuff.Mode.SRC_OVER)
        }
    }
}
