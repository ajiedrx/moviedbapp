package com.example.moviedbapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.core.domain.model.Film
import com.example.moviedbapp.databinding.DetailFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var binding: DetailFragmentBinding? = null
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var filmData: Film

    companion object {
        private const val IMAGE_BASE_URL = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.title = filmData.name
        super.onActivityCreated(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmData = arguments?.getParcelable(Film.FILM)!!
        viewModel.getFilmDetail(filmData).observe(viewLifecycleOwner, {
            if (it != null) {
                binding?.filmTitle?.text = it.name
                binding?.filmGenre?.text = it.genre
                binding?.filmDuration?.text = it.duration
                Glide.with(view)
                    .load(
                        IMAGE_BASE_URL + (arguments?.getParcelable<Film>(
                            Film.FILM)!!).poster)
                    .into(binding?.filmPoster!!)
                binding?.filmOverview?.text = it.overview
                binding?.favoriteBtn?.isChecked = viewModel.isFavorite(filmData.film_id!!)
                showLoading(false)
            }
        })
        binding?.favoriteBtn?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                viewModel.addFavoriteFilm(filmData)
            } else {
                viewModel.removeFavoriteFilm(filmData)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        showLoading(true)
        return binding?.root
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.progressCover?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
            binding?.progressCover?.visibility = View.GONE
        }
    }
}