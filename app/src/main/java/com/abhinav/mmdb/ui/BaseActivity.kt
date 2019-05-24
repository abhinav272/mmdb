package com.abhinav.mmdb.ui

import androidx.appcompat.app.AppCompatActivity
import com.abhinav.mmdb.data.model.Result
import com.abhinav.mmdb.showToastMsg
import com.abhinav.mmdb.utils.Event

open class BaseActivity : AppCompatActivity() {

    fun onFailure(event: Event<Result.Failure>?) {
        if (!event?.isAlreadyHandled!!) {
            val content = event.getContent()
            content?.let { showToastMsg(it.message) }
        }
    }
}