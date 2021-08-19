package com.example.moviedbapp.presentation.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Film
import com.example.core.domain.usecase.FilmUseCase

class MovieListViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {
    fun getTopRatedMovies(): LiveData<ArrayList<Film>> =
        filmUseCase.getTopRatedMovies().asLiveData() as LiveData<ArrayList<Film>>
}