package com.bguina.eurosport.test.data.http.model

import com.squareup.moshi.Json

data class StoryDto(
    @Json(name = "date")
    val date: Double? = null,
    @Json(name = "image")
    val image: String? = null,
    @Json(name = "author")
    val author: String? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "sport")
    val sport: SportDto? = null,
    @Json(name = "teaser")
    val teaser: String? = null,
)
