package com.abhinav.mmdb.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.TrendingItem
import com.abhinav.mmdb.displayName
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_trending_item.view.*
import com.bumptech.glide.load.resource.bitmap.CenterCrop



class TrendingItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(trendingItem: TrendingItem) = with(itemView) {
        tv_movie_name.text = trendingItem.displayName()

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(25))
        Glide.with(iv_movie_poster).load(CacheManager.configurations?.images?.baseUrl
                + CacheManager.configurations?.images?.backdropSizes?.get(2) + trendingItem.backdropPath)
            .apply(requestOptions)
            .into(iv_movie_poster)

        tv_rating.text = String.format("Rating: %.1f",trendingItem.voteAverage)

    }

}
