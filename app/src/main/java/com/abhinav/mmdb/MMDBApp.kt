package com.abhinav.mmdb

import android.app.Application
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MMDBApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Glide.get(applicationContext).clearMemory()
        CoroutineScope(Dispatchers.IO).launch {
            Glide.get(applicationContext).clearDiskCache()
        }

    }
}