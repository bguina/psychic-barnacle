package com.bguina.eurosport.test.data.http

import com.bguina.eurosport.test.data.IArticlesDataSource
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


    override suspend fun listArticles(): List<Article> {
        TODO("Not yet implemented")
    }
}
