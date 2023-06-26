package com.bguina.eurosport.test.domain.model

sealed class Article(
    val id: Long,
    val date: Long,
    val sport: String = "",
    val title: String = "",
) {
    class Story(
        id: Long = 0,
        date: Long = 0,
        sport: String = "",
        title: String = "",
        val content: String = "",
        val imageUrl: String = "",
        val author: String = "",
    ) : Article(id = id, date = date, sport = sport, title = title)

    class Video(
        id: Long = 0,
        date: Long = 0,
        sport: String = "",
        title: String = "",
        val videoUrl: String = "",
        val viewCount: Long = 0,
    ) : Article(id = id, date = date, sport = sport, title = title)
}
