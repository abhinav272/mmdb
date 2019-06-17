package com.abhinav.mmdb.ui.adapters.viewholders

import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.model.NowPlaying
import kotlinx.android.synthetic.main.layout_now_playing_view_more.view.*

class NowPlayingViewMoreItemViewHolder(itemView: View) : NowPlayingItemViewHolder(itemView) {
    override fun bind(item: NowPlaying) {
        super.bind(item)
        with(itemView) {
            tv_view_more.visibility = View.VISIBLE
            iv_movie_poster.setColorFilter(ContextCompat.getColor(context, R.color.black_60_alpha), PorterDuff.Mode.SRC_OVER)
        }
    }
}
