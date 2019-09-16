package com.abhinav.mmdb.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.BuildConfig
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.NowPlaying
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_now_playing.view.*

open class NowPlayingItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    open fun bind(
        item: NowPlaying,
        onNowPlayingItemClick: (NowPlaying, Int, View) -> Unit
    ) {
        with(itemView) {
            setOnClickListener {
                onNowPlayingItemClick(item, adapterPosition, iv_movie_poster)
            }
            tv_movie_name.text = item.originalTitle
            tv_movie_rating.text = String.format("Rating: %.1f", item.voteAverage)
            iv_movie_poster.apply {
                transitionName = item.id.toString().plus(adapterPosition)
                var requestOptions = RequestOptions()
                requestOptions = requestOptions
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .transforms(CenterCrop(), RoundedCorners(25))
                Glide.with(this).load(
                    BuildConfig.IMAGE_BASE_URL
                            + CacheManager.configurations?.images?.posterSizes?.last() + item.posterPath
                ).thumbnail(Glide.with(this).load(BuildConfig.IMAGE_BASE_URL
                        + CacheManager.configurations?.images?.posterSizes?.first() + item.posterPath).apply(requestOptions))
                    .placeholder(R.drawable.bg_image_placeholder)
                    .error(R.drawable.bg_image_error_placeholder)
                    .apply(requestOptions)
                    .into(this)
            }
        }
    }

    fun getSharedView() = with(itemView) {
        iv_movie_poster
    }

}
