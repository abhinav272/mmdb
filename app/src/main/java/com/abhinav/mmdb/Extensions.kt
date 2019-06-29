package com.abhinav.mmdb

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abhinav.mmdb.data.model.ErrorBody
import com.abhinav.mmdb.data.model.TrendingItem
import com.abhinav.mmdb.ui.BaseActivity
import com.google.gson.Gson
import retrofit2.Response
import java.text.SimpleDateFormat

fun BaseActivity.showToastMsg(msg: String) {
    Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
}

fun <T> Response<T>.parseError(): ErrorBody {
    return Gson().fromJson(this.errorBody()?.string(), ErrorBody::class.java)
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun TrendingItem.displayName() = when {
    !name.isNullOrEmpty() && name.isNotEmpty() -> name
    !originalTitle.isNullOrEmpty() && originalTitle.isNotEmpty() -> originalTitle
    else -> "N/A"
}

@SuppressLint("SimpleDateFormat")
fun String.formatDate(): String {
    try {
        var dateFormat = SimpleDateFormat("yyyy-mm-dd")
        var finalDateFormat = SimpleDateFormat("dd MMM yyyy")
        val parse = dateFormat.parse(this)
        return finalDateFormat.format(parse)
    } catch (e: Exception) {
        Log.e("Extentions", e.toString(), e)
    }

    return this
}