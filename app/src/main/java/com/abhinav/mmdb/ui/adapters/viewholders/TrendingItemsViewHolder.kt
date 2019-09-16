package com.abhinav.mmdb.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.TrendingItem
import com.abhinav.mmdb.displayName
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_trending_item.view.*


class TrendingItemsViewHolder(
    itemView: View,
    val onTrendingItemClick: (TrendingItem, Int, View) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    fun bind(trendingItem: TrendingItem) = with(itemView) {
        iv_movie_poster.transitionName = trendingItem.id.toString().plus("trending")
        setOnClickListener { onTrendingItemClick.invoke(trendingItem, adapterPosition, iv_movie_poster) }
        tv_movie_name.text = trendingItem.displayName()

        var requestOptions = RequestOptions()
        requestOptions = requestOptions
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .transforms(CenterCrop(), RoundedCorners(25))
        Glide.with(iv_movie_poster).load(
            CacheManager.configurations?.images?.baseUrl
                    + CacheManager.configurations?.images?.backdropSizes?.get(1) + trendingItem.backdropPath
        )
            .apply(requestOptions)
            .into(iv_movie_poster)

        tv_item_rating.text = String.format("%.1f", trendingItem.voteAverage)
        tv_genre.text = ""
        var textGen = ""
        trendingItem.genreIds?.take(3)?.forEach {
            val genre = String.format("%s|", CacheManager.genreMap?.get(it))
            textGen = String.format("%s%s", textGen, genre)
        }
        tv_genre.text = textGen.dropLast(1)

    }

}
