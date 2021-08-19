package com.example.core.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.entities.FilmEntity
import com.example.core.data.entities.topRatedMoviesResponse.MovieResultsItem
import com.example.core.data.remote.api.ApiService


class TopRatedMoviePagingSource(private val apiService: ApiService) :
    PagingSource<Int, FilmEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmEntity> {
        val position = params.key ?: 1
        var nextKey: Int? = null
        val movie = apiService.getPagedTopRatedMovies(position).body()?.results!!
        val domainItems = movie.map { movieResultsItem: MovieResultsItem ->
            FilmEntity(
                film_id = movieResultsItem.id,
                name = movieResultsItem.title,
                poster = movieResultsItem.posterPath,
                rating = movieResultsItem.voteAverage.toString(),
                type = FilmEntity.MOVIE
            )
        }
        nextKey = if (domainItems.isEmpty()) {
            null
        } else {
            position + 1
        }
        return LoadResult.Page(data = domainItems,
            prevKey = if (position == 1) null else position - 1,
            nextKey = nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, FilmEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}