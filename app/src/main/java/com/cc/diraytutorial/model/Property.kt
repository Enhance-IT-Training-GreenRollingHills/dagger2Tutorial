package com.cc.diraytutorial.model

import com.google.gson.annotations.SerializedName

data class Property(
    @SerializedName("*")
    val star: String,
    val name: String
)