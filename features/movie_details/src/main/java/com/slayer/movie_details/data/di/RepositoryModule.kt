package com.slayer.movie_details.data.di

import com.slayer.movie_details.data.repositories.MovieDetailsRepoImpl
import com.slayer.movie_details.domain.repositories.MovieDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
fun interface RepositoryModule {
    @Binds
    fun bindDiscoverRepository(repoImpl: MovieDetailsRepoImpl) : MovieDetailsRepository
}