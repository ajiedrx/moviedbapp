package com.example.moviedbapp.injection

import androidx.room.Room
import com.example.core.data.FilmRepository
import com.example.core.data.local.AppDatabase
import com.example.core.data.local.LocalDataSource
import com.example.core.data.remote.RemoteDataSource
import com.example.core.data.remote.api.ApiService
import com.example.core.domain.repository.IFilmRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<AppDatabase>().getDao() }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "appdb")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}

private val baseUrl = "https://api.themoviedb.org/"
val networkModule = module {
    single {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
                RxJava2CallAdapterFactory.create())
            .build()

        retrofit.create(
            ApiService::class.java
        )
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IFilmRepository> { FilmRepository(get(), get()) }
}

