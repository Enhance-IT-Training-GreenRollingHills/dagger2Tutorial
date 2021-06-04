package com.cc.diraytutorial.model

import com.google.gson.annotations.SerializedName

data class Template(
    @SerializedName("*")
    val star: String,
    val exists: String,
    val ns: Int
)