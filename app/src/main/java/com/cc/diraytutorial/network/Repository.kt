package com.cc.diraytutorial.network

import com.cc.diraytutorial.model.WikiResponse
import com.cc.diraytutorial.model.search.WikiSearchResponse
import com.cc.diraytutorial.util.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {

    private val service = buildRetrofit().create(WikiNetworkCalls::class.java)


    private fun buildRetrofit () : Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    /*fun getStuff () {
        service.getStuff()
    }*/

    suspend fun getHomepage () : WikiResponse {
        return service.getHomepage()
    }

    suspend fun search (query:String) : WikiSearchResponse {
        return service.search(query)
    }
}