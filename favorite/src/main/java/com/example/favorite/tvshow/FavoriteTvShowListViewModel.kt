package com.example.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.Film
import com.example.core.domain.usecase.FilmUseCase

class FavoriteTvShowListViewModel(private val filmUseCase: FilmUseCase) : ViewModel() {
    fun getFavoriteTvShows(): LiveData<ArrayList<Film>> {
        return filmUseCase.getFavoriteTvShows().asLiveData() as LiveData<ArrayList<Film>>
    }
}