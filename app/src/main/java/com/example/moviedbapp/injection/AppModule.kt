package com.example.moviedbapp.injection

import com.example.core.domain.usecase.FilmInteractor
import com.example.core.domain.usecase.FilmUseCase
import com.example.moviedbapp.presentation.detail.DetailViewModel
import com.example.moviedbapp.presentation.main.movie.MovieListViewModel
import com.example.moviedbapp.presentation.main.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieListViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}