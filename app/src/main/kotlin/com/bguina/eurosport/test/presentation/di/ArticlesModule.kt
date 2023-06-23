package com.bguina.eurosport.test.presentation.di

import com.bguina.eurosport.test.data.ArticlesRepository
import com.bguina.eurosport.test.data.IArticlesDataSource
import com.bguina.eurosport.test.data.http.ArticlesHttpDataSource
import com.bguina.eurosport.test.domain.IArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ArticlesModule.BindsModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
class ArticlesModule {

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {
        @Binds
        fun provideArticlesRepository(
            impl: ArticlesRepository
        ): IArticlesRepository

        @Binds
        fun provideArticlesHttpDataSource(
            impl: ArticlesHttpDataSource
        ): IArticlesDataSource
    }
}
