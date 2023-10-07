package com.example.daznapplication.di

import com.example.daznapplication.data.EventApi
import com.example.daznapplication.domain.repository.EventRepository
import com.example.daznapplication.data.EventRepositoryImpl
import com.example.daznapplication.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideEventApi(): EventApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEventRepository(api: EventApi): EventRepository {
        return EventRepositoryImpl(api)
    }


}