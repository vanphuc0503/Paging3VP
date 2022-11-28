package com.vanphuc.pagingdata3vp.di

import com.vanphuc.pagingdata3vp.data.repository.NewsRepository
import com.vanphuc.pagingdata3vp.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideNew(repositoryImpl: NewsRepositoryImpl): NewsRepository
}