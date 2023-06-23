package com.bguina.eurosport.test.data

import com.bguina.eurosport.test.data.model.StoryEntity
import com.bguina.eurosport.test.data.model.VideoEntity

interface IArticlesDataSource {
    suspend fun listStories() : List<StoryEntity>

    suspend fun listVideos() : List<VideoEntity>
}
