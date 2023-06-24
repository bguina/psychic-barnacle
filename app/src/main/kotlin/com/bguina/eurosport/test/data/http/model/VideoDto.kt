package com.bguina.eurosport.test.data.http.model

import com.squareup.moshi.Json

data class VideoDto(
    @Json(name = "date")
    val date: Double? = null,
    @Json(name = "thumb")
    val thumb: String? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "sport")
    val sport: SportDto? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "views")
    val views: Long? = null
)
