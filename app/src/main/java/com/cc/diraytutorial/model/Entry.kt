package com.cc.diraytutorial.model

import com.cc.diraytutorial.util.LogToConsole
import org.json.JSONObject
import retrofit2.Response


data class Entry(val title: String, val snippet: String)

//data class HomepageResult(private val response: Response<String>) {
/*data class HomepageResult(private val response: String) {

    *//*var homepage: WikiHomepage? = null

    init {
        homepage = response.body()?.string()?.let {
            JSONObject(it)
                .getJSONObject("parse")
                .getJSONObject("text")
                .getString("*")
                .let {
                    WikiHomepage(it)
                }
        }
    }*//*

    var homepage:WikiHomepage? = null

    init {

        LogToConsole.log("homePageResult data with : $response")

        homepage = JSONObject(response).getJSONObject("parse")
            .getJSONObject("text")
            .getString("*")
            .let {
                WikiHomepage(it)
            }

        LogToConsole.log("new home page : $homepage")
    }
}*/


data class SearchResult(private val response: Response<String>) {

    /*var list: List<Entry>? = listOf()

    init {
        list = response.body()?.string()?.let {
            JSONObject(it)
                .getJSONObject("query")
                .getJSONArray("search")
                .let { array ->
                    (0 until array.length()).map {
                        array.getJSONObject(it)
                    }.map {
                        Entry(it.getString("title"), it.getString("snippet"))
                    }
                }
        }
    }*/
}


data class WikiHomepage(val htmlContent: String)
