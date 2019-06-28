package com.abhinav.mmdb.data.model

data class NavRequest(var navId: Int) {
    companion object{
        const val HOME_FRAGMENT = 1
        const val NOW_PLAYING_EXPLORE_FRAGMENT = 2
    }
}