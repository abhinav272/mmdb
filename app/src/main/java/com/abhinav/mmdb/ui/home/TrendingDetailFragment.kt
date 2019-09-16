package com.abhinav.mmdb.ui.home

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.core.os.bundleOf
import androidx.core.view.postDelayed
import com.abhinav.mmdb.R
import com.abhinav.mmdb.data.cache.CacheManager
import com.abhinav.mmdb.data.model.TrendingItem
import com.abhinav.mmdb.inflate
import com.abhinav.mmdb.ui.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_trending_detail.*
import kotlinx.android.synthetic.main.fragment_trending_detail.view.*


class TrendingDetailFragment : BaseFragment() {
    private lateinit var trendingItem: TrendingItem

    companion object {
        private const val ITEM = "item"

        fun getInstance(trendingItem: TrendingItem): TrendingDetailFragment {
            return TrendingDetailFragment().apply {
                arguments = bundleOf(ITEM to trendingItem)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.default_transition)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.default_transition)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        trendingItem = arguments?.getParcelable(ITEM) as TrendingItem
        return container?.inflate(R.layout.fragment_trending_detail)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view) {
            Glide.with(iv_movie_poster).asBitmap().load(
                CacheManager.configurations?.images?.baseUrl
                        + CacheManager.configurations?.images?.backdropSizes?.get(1) + trendingItem.backdropPath
            ).listener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
//                        scaleImage(resource!!, 8000f)
                        iv_movie_poster.postDelayed(200)
                        { iv_movie_poster.setImageBitmap(resizeBitmap(resource!!)) }
                        startPostponedEnterTransition()
                        return false
                    }
                }).into(iv_movie_poster)
        }
    }

    private fun scaleImage(bitmap: Bitmap, boundBoxInDp: Float) {
        // Get the ImageView and its bitmap

        // Get current dimensions
        var width = bitmap.width
        var height = bitmap.height

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        val xScale = boundBoxInDp / width
        val yScale = boundBoxInDp / height
        val scale = if (xScale <= yScale) xScale else yScale

        // Create a matrix for the scaling and add the scaling data
        val matrix = Matrix()
        matrix.postScale(4f, 4f)

        // Create a new bitmap and convert it to a format understood by the ImageView
        val scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, 4*width, 4*height, matrix, true)
        val result = BitmapDrawable(scaledBitmap)
        width = scaledBitmap.width
        height = scaledBitmap.height

        // Apply the scaled bitmap
        iv_movie_poster.setImageDrawable(result)

//        // Now change ImageView's dimensions to match the scaled image
//        val params = iv_movie_poster.layoutParams as ConstraintLayout.LayoutParams
//        params.width = width
//        params.height = height
//        iv_movie_poster.layoutParams = params
    }

    // Method to resize a bitmap programmatically
    private fun resizeBitmap(bitmap:Bitmap):Bitmap{
        /*
            *** reference source developer.android.com ***
            Bitmap createScaledBitmap (Bitmap src, int dstWidth, int dstHeight, boolean filter)
                Creates a new bitmap, scaled from an existing bitmap, when possible. If the specified
                width and height are the same as the current width and height of the source bitmap,
                the source bitmap is returned and no new bitmap is created.

            Parameters
                src Bitmap : The source bitmap.
                    This value must never be null.

            dstWidth int : The new bitmap's desired width.
            dstHeight int : The new bitmap's desired height.
            filter boolean : true if the source should be filtered.

            Returns
                Bitmap : The new scaled bitmap or the source bitmap if no scaling is required.

            Throws
                IllegalArgumentException : if width is <= 0, or height is <= 0
        */
        return Bitmap.createScaledBitmap(bitmap, 400, 400, false)
    }
}
