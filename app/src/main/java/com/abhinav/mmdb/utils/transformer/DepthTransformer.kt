package com.abhinav.mmdb.utils.transformer

import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs
import kotlin.math.max


class DepthTransformation : ViewPager.PageTransformer {
    private val MIN_SCALE = 0.90f
    private val MIN_ALPHA = 0.2f

    override fun transformPage(page: View, position: Float) {

        when {
            position < -1 -> // [-Infinity,-1)
                // This page is way off-screen to the left.
                page.alpha = 0f
            position <= 0 -> {    // [-1,0]
                page.alpha = 1f
                page.translationX = 0f
                page.scaleX = 1f
                page.scaleY = 1f

            }
            position <= 1 -> {    // (0,1]
                page.scaleX = max(MIN_SCALE, 1 - abs(position))
                page.scaleY = max(MIN_SCALE, 1 - abs(position))
                page.alpha = max(MIN_ALPHA, 1 - abs(position))

            }
            else -> // (1,+Infinity]
                // This page is way off-screen to the right.
                page.alpha = 0f
        }


    }
}