package com.example.core.data.remote.api

import com.example.core.data.entities.movieDetailResponse.MovieDetailResponse
import com.example.core.data.entities.topRatedMoviesResponse.TopRatedMoviesResponse
import com.example.core.data.entities.topRatedTvShowResponse.TopRatedTvResponse
import com.example.core.data.entities.tvShowDetailResponse.TvShowDetailResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("/3/movie/top_rated")
    suspend fun getPagedTopRatedMovies(
        @Query("page") page: Int,
        @Query("api_key") appKey: String = API.API_KEY,
    ): Response<TopRatedMoviesResponse>

    @GET("/3/tv/top_rated")
    suspend fun getPagedTopRatedTvShow(
        @Query("page") page: Int,
        @Query("api_key") appKey: String = API.API_KEY,
    ): Response<TopRatedTvResponse>

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") appKey: String = API.API_KEY,
    ): TopRatedMoviesResponse

    @GET("/3/tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("api_key") appKey: String = API.API_KEY,
    ): TopRatedTvResponse

    @GET("/3/tv/{id}?api_key=" + API.API_KEY)
    suspend fun getTvShowDetail(
        @Path("id") id: Int,
    ): TvShowDetailResponse

    @GET("/3/movie/{id}?api_key=" + API.API_KEY)
    suspend fun getMovieDetail(
        @Path("id") id: Int,
    ): MovieDetailResponse
}