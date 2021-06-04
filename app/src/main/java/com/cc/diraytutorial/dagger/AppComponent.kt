package com.cc.diraytutorial.dagger

import android.net.Network
import com.cc.diraytutorial.ui.homepage.HomepageActivity
import com.cc.diraytutorial.ui.search.SearchActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetworkModule::class, WikiModule::class])
interface AppComponent {
    fun inject(target: HomepageActivity)
    fun inject(target: SearchActivity)

}
