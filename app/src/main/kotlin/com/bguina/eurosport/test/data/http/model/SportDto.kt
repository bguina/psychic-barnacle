package com.bguina.eurosport.test.data.http.model

import com.squareup.moshi.Json

data class SportDto(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "id")
    val id: Int? = null
)
