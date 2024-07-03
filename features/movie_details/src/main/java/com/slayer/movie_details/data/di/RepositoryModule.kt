package com.slayer.movie_details.data.di

import com.slayer.movie_details.data.repositories.MovieDetailsRepoImpl
import com.slayer.movie_details.domain.repositories.MovieDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDiscoverRepository(repoImpl: MovieDetailsRepoImpl) : MovieDetailsRepository
}