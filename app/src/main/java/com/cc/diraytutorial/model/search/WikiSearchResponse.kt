package com.cc.diraytutorial.model.search

data class WikiSearchResponse(
    val batchcomplete: String,
    val `continue`: Continue,
    val query: Query
)