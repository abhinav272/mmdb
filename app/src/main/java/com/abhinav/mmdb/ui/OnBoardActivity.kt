package com.abhinav.mmdb.ui

import android.content.Intent
import android.os.Bundle
import com.abhinav.mmdb.R
import com.abhinav.mmdb.ui.home.HomeActivity
import kotlinx.coroutines.*

class OnBoardActivity : BaseActivity() {

    var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
    }

    override fun onResume() {
        super.onResume()
        job = GlobalScope.launch {
            delay(2000)
            withContext(Dispatchers.Main) {
                showHomeActivity().apply { finish() }
            }
        }
    }

    private fun showHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }
}