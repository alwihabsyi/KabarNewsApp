package com.alwihabsyi.newsapp.di

import android.app.Application
import androidx.room.Room
import com.alwihabsyi.newsapp.data.local.NewsDao
import com.alwihabsyi.newsapp.data.local.NewsDatabase
import com.alwihabsyi.newsapp.data.local.NewsTypeConverter
import com.alwihabsyi.newsapp.data.manager.LocalUserManagerImpl
import com.alwihabsyi.newsapp.data.remote.NewsApi
import com.alwihabsyi.newsapp.data.repository.NewsRepositoryImpl
import com.alwihabsyi.newsapp.domain.manager.LocalUserManager
import com.alwihabsyi.newsapp.domain.repository.NewsRepository
import com.alwihabsyi.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.alwihabsyi.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.alwihabsyi.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.alwihabsyi.newsapp.domain.usecases.news.DeleteArticle
import com.alwihabsyi.newsapp.domain.usecases.news.SelectArticle
import com.alwihabsyi.newsapp.domain.usecases.news.GetNews
import com.alwihabsyi.newsapp.domain.usecases.news.NewsUseCases
import com.alwihabsyi.newsapp.domain.usecases.news.SearchNews
import com.alwihabsyi.newsapp.domain.usecases.news.SelectArticles
import com.alwihabsyi.newsapp.domain.usecases.news.UpsertArticle
import com.alwihabsyi.newsapp.util.Constants.BASE_URL
import com.alwihabsyi.newsapp.util.Constants.NEWS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}