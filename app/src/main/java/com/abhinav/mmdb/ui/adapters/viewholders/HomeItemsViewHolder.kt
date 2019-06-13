package com.abhinav.mmdb.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.NowPlaying
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_now_playing.view.*

class HomeItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(nowPlayingItem: NowPlaying) = with(itemView) {
        tv_movie_name.text = nowPlayingItem.originalTitle
        tv_movie_rating.text = String.format("Rating: %.1f", nowPlayingItem.voteAverage)

        Glide.with(iv_movie_poster).load(
            CacheManager.configurations?.images?.baseUrl
                    + CacheManager.configurations?.images?.posterSizes?.last() + nowPlayingItem.posterPath
        )
            .centerCrop().into(iv_movie_poster)
    }

}
