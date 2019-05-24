package com.abhinav.mmdb

import android.widget.Toast
import com.abhinav.mmdb.data.model.ErrorBody
import com.abhinav.mmdb.ui.BaseActivity
import com.google.gson.Gson
import retrofit2.Response

fun BaseActivity.showToastMsg(msg: String){
    Toast.makeText(applicationContext,  msg, Toast.LENGTH_LONG).show()
}

fun <T> Response<T>.parseError() : ErrorBody{
    return Gson().fromJson(this.errorBody()?.string(), ErrorBody::class.java)
}