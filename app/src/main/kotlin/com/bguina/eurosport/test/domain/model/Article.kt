package com.bguina.eurosport.test.domain.model

sealed class Article(
    val date: Long,
    val sport: String = "",
    val title: String = "",
) {
    class Story(
        date: Long = 0,
        sport: String = "",
        title: String = "",
        val content: String = "",
        val imageUrl: String = "",
        val author: String = "",
    ) : Article(date = date, sport = sport, title = title)

    class Video(
        date: Long = 0,
        sport: String = "",
        title: String = "",
        val videoUrl: String = "",
        val viewCount: Long = 0,
    ) : Article(date = date, sport = sport, title = title)
}
