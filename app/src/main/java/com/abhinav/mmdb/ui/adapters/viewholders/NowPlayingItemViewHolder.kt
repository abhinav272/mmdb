package com.abhinav.mmdb.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.NowPlaying
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_now_playing.view.*

open class NowPlayingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    open fun bind(item: NowPlaying) {
        with(itemView) {
            tv_movie_name.text = item.originalTitle
            tv_movie_rating.text = String.format("Rating: %.1f", item.voteAverage)
            iv_movie_poster.apply {
                Glide.with(this).load(
                    CacheManager.configurations?.images?.baseUrl
                            + CacheManager.configurations?.images?.posterSizes?.last() + item.posterPath
                ).centerCrop().into(this)
            }
        }
    }

}
