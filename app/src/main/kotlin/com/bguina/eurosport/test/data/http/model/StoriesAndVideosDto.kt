package com.bguina.eurosport.test.data.http.model

import com.squareup.moshi.Json

data class StoriesAndVideosDto(
    @Json(name = "stories")
    val stories: List<StoryDto>? = null,
    @Json(name = "videos")
    val videos: List<VideoDto>? = null,
)
