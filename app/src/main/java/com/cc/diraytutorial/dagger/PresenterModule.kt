package com.cc.diraytutorial.dagger

import com.cc.diraytutorial.network.Repository
import com.cc.diraytutorial.ui.homepage.HomepagePresenter
import com.cc.diraytutorial.ui.homepage.HomepagePresenterImpl
import com.cc.diraytutorial.ui.search.EntryPresenter
import com.cc.diraytutorial.ui.search.EntryPresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {

    @Provides
    @Singleton
    //fun provideHomepagePresenter(homepage:HomePage): HomepagePresenter = HomepagePresenterImpl(homepage)
    fun provideHomepagePresenter(repo: Repository): HomepagePresenter = HomepagePresenterImpl(repo)

    @Provides
    @Singleton
    //fun provideEntryPresenter(wiki:Wiki): EntryPresenter = EntryPresenterImpl(wiki)
    fun provideEntryPresenter(repo:Repository): EntryPresenter = EntryPresenterImpl(repo)

}