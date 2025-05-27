package com.example.weathertoday.di

import com.example.weathertoday.data.remote.api.WeatherApiService
import com.example.weathertoday.data.repository.WeatherRepoImpl
import com.example.weathertoday.domain.repository.WeatherRepo
import com.example.weathertoday.domain.useCases.GetWeatherUseCase
import com.example.weathertoday.utils.Constants
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
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApiService(retrofit: Retrofit): WeatherApiService{
        return retrofit.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepo(api: WeatherApiService): WeatherRepo {
        return WeatherRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideWeatherUseCases(repo: WeatherRepo): GetWeatherUseCase{
        return GetWeatherUseCase(repo)
    }

}