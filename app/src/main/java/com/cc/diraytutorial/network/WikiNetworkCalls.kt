package com.cc.diraytutorial.network

import com.cc.diraytutorial.model.WikiResponse
import com.cc.diraytutorial.model.search.WikiSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiNetworkCalls {

    @GET
    fun getStuff() : String

    @GET("api.php")
    suspend fun getHomepage (@Query("action") action:String = "parse", @Query("page") page:String = "Main Page",
    @Query("format") format:String = "json") : WikiResponse


    @GET("api.php")
    suspend fun search (
        @Query ("srsearch") srsearch:String,
        @Query("action") action: String = "query",
    @Query("list") list:String = "search",
    @Query("format") format: String = "json")
     : WikiSearchResponse
}