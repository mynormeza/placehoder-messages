package com.example.placeholdermessages.di

import com.example.placeholdermessages.data.PostRepository
import com.example.placeholdermessages.domain.repositories.IPostRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindPlayRepository(postsRepository: PostRepository): IPostRepository

}
