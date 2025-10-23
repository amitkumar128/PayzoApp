package com.amit.payzoapp.di

import com.amit.payzoapp.data.DummyPayzoRepository
import com.amit.payzoapp.domain.repository.PayzoRepository
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
    abstract fun bindPayzoRepository( impl: DummyPayzoRepository): PayzoRepository
}