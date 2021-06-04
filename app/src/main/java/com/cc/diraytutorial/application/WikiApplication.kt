package com.cc.diraytutorial.application

import android.app.Application
import com.cc.diraytutorial.dagger.AppComponent
import com.cc.diraytutorial.dagger.AppModule
import com.cc.diraytutorial.dagger.DaggerAppComponent

class WikiApplication : Application() {

    lateinit var wikiComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        wikiComponent = initDagger(this)
    }

    private fun initDagger(app: WikiApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()


}