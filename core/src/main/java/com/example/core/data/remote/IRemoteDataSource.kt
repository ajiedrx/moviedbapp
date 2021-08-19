package com.example.core.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.core.data.entities.FilmEntity
import com.example.core.data.entities.movieDetailResponse.MovieDetailResponse
import com.example.core.data.entities.topRatedMoviesResponse.MovieResultsItem
import com.example.core.data.entities.topRatedTvShowResponse.TvResultsItem
import com.example.core.data.entities.tvShowDetailResponse.TvShowDetailResponse
import kotlinx.coroutines.flow.Flow


interface IRemoteDataSource {
    fun getPagedTopRatedMovies(): LiveData<PagingData<FilmEntity>>

    fun getPagedTopRatedTvShows(): LiveData<PagingData<FilmEntity>>

    fun getTopRatedMovies(): Flow<List<MovieResultsItem>>

    fun getTopRatedTvShows(): Flow<List<TvResultsItem>>

    fun getMovieDetail(id: Int): Flow<MovieDetailResponse>

    fun getTvShowDetail(id: Int): Flow<TvShowDetailResponse>
}