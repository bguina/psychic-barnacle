package com.bguina.eurosport.test.data.http

import com.bguina.eurosport.test.data.IArticlesDataSource
import com.bguina.eurosport.test.data.model.StoryEntity
import com.bguina.eurosport.test.data.model.VideoEntity
import com.bguina.eurosport.test.domain.model.Article
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class ArticlesHttpDataSource @Inject constructor(

) : IArticlesDataSource {

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    override suspend fun listStories(): List<StoryEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun listVideos(): List<VideoEntity> {
        TODO("Not yet implemented")
    }
}
