package com.abhinav.mmdb.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.abhinav.mmdb.data.model.Result
import com.abhinav.mmdb.showToastMsg
import com.abhinav.mmdb.utils.Event

open class BaseActivity : AppCompatActivity() {

    fun addFragment(containerId: Int, fragment: BaseFragment) {
        supportFragmentManager.commit {
            addToBackStack(fragment::class.java.simpleName)
            add(containerId, fragment, fragment::class.java.simpleName)
        }
    }

    fun onFailure(event: Event<Result.Failure>?) {
        if (!event?.isAlreadyHandled!!) {
            val content = event.getContent()
            content?.let { showToastMsg(it.message) }
        }
    }
}