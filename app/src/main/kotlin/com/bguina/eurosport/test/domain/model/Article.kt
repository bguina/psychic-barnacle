package com.bguina.eurosport.test.domain.model

sealed class Article(
    val date: Long,
) {
    class Story(
        date: Long = 0,
        val content: String = "",
        val imageUrl: String = "",
        val author: String = "",
    ) : Article(date)

    class Video(
        date: Long = 0,
        val videoUrl: String = "",
        val viewCount: Long = 0,
    ) : Article(date)
}
