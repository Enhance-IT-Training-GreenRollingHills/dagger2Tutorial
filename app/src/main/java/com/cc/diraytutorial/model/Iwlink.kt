package com.cc.diraytutorial.model

import com.google.gson.annotations.SerializedName

data class Iwlink(
    @SerializedName("*")
    val start: String,
    val prefix: String,
    val url: String
)