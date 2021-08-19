package com.example.core.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.core.data.entities.FilmEntity
import com.example.core.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {
    fun getMovieDetail(id: Int): Flow<Film>
    fun getTvShowDetail(id: Int): Flow<Film>
    fun insertFavorite(data: Film)
    fun deleteFavorite(filmId: Int)
    fun getFilmCount(filmId: Int): Int
    fun getAllFavorite(): List<Film>

    fun getTopRatedMovies(): Flow<List<Film>>
    fun getTopRatedTvShows(): Flow<List<Film>>

    fun getFavoriteMovies(): Flow<List<Film>>
    fun getFavoriteTvShows(): Flow<List<Film>>

    fun getPagedTopRatedMovies(): LiveData<PagingData<FilmEntity>>
    fun getPagedTopRatedTvShows(): LiveData<PagingData<FilmEntity>>
    fun getPagedFavoriteMovies(): LiveData<PagingData<FilmEntity>>
    fun getPagedFavoriteTvShow(): LiveData<PagingData<FilmEntity>>
}