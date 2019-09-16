package com.abhinav.mmdb.ui.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.abhinav.mmdb.BuildConfig
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.NowPlaying
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.layout_now_playing_view_more.view.*


class ExploreNowPlayingViewPagerAdapter(var fragment: BaseFragment, var nowPlayingItems: List<NowPlaying>) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = container.inflate(R.layout.layout_single_explore_now_playing)
        container.addView(view)
        val item = nowPlayingItems[position]
        with(view) {
            iv_movie_poster.apply {
                transitionName = item.id.toString().plus(position)
                Log.e("Setting", "$transitionName for ${item.originalTitle}")
                var requestOptions = RequestOptions()
                requestOptions = requestOptions
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .transforms(CenterCrop(), RoundedCorners(25))
                Glide.with(this).load(
                    BuildConfig.IMAGE_BASE_URL
                            + CacheManager.configurations?.images?.posterSizes?.last() + item.posterPath
                )
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            fragment.startPostponedEnterTransition()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            fragment.startPostponedEnterTransition()
                            return false
                        }

                    })
                    .apply(requestOptions)
                    .into(this)
            }
        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return 6
    }

    override fun getPageWidth(position: Int): Float {
        return 0.65f
    }


}
