package com.abhinav.mmdb.data.model

import com.google.gson.annotations.SerializedName

data class Configurations(@SerializedName("images")
                          val images: Images,
                          @SerializedName("change_keys")
                          val changeKeys: List<String>?)