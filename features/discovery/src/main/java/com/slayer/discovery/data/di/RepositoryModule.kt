package com.slayer.discovery.data.di

import com.slayer.discovery.data.repositories.DiscoverRepoImpl
import com.slayer.discovery.domain.repositories.DiscoverRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
fun interface RepositoryModule {
    @Binds
    abstract fun bindDiscoverRepository(repoImpl: DiscoverRepoImpl) : DiscoverRepository
}