package com.example.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Film
import com.example.core.domain.usecase.FilmUseCase

class FavoriteMovieListViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {
    fun getFavoritedMovie(): LiveData<ArrayList<Film>> {
        return filmUseCase.getFavoriteMovies().asLiveData() as LiveData<ArrayList<Film>>
    }
}