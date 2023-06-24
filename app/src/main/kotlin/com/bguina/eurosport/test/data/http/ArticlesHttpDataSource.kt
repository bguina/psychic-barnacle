package com.bguina.eurosport.test.data.http

import com.bguina.eurosport.test.data.IArticlesDataSource
import com.bguina.eurosport.test.data.http.model.StoryDto
import com.bguina.eurosport.test.data.http.model.VideoDto
import com.bguina.eurosport.test.data.model.StoryEntity
import com.bguina.eurosport.test.data.model.VideoEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class ArticlesHttpDataSource @Inject constructor(
) : IArticlesDataSource {

    private val moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    private val service: ArticlesService = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl("https://extendsclass.com/api/")
        .client(client)
        .build()
        .create(ArticlesService::class.java)

    override suspend fun listStories(
    ): List<StoryEntity> = service.listStoriesAndVideos().stories.orEmpty()
        .map { it.toEntity() }

    override suspend fun listVideos(
    ): List<VideoEntity> = service.listStoriesAndVideos().videos.orEmpty()
        .map { it.toEntity() }

    private fun StoryDto.toEntity(): StoryEntity = StoryEntity(
        date = this.date?.toLong(),
        content = this.teaser,
        imageUrl = this.image,
        author = this.author,
    )

    private fun VideoDto.toEntity(): VideoEntity = VideoEntity(
        date = this.date?.toLong(),
        url = this.url,
        viewCount = this.views,
    )
}
