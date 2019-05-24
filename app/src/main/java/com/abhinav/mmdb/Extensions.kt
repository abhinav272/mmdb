package com.abhinav.mmdb

import android.widget.Toast
import com.abhinav.mmdb.ui.BaseActivity

fun BaseActivity.showToastMsg(msg: String){
    Toast.makeText(applicationContext,  msg, Toast.LENGTH_SHORT).show()
}