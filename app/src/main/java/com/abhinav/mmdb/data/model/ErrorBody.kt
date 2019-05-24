package com.abhinav.mmdb.data.model

import com.google.gson.annotations.SerializedName

data class ErrorBody(@SerializedName("status_message")
                     val statusMessage: String = "",
                     @SerializedName("status_code")
                     val statusCode: Int = 0)