package com.bguina.eurosport.test.data.http

import com.bguina.eurosport.test.data.http.model.StoriesAndVideosDto
import retrofit2.http.*

interface ArticlesService {
    @GET("json-storage/bin/edfefba")
    suspend fun listStoriesAndVideos(
    ): StoriesAndVideosDto
}
