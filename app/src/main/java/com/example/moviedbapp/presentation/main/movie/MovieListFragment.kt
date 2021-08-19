package com.example.moviedbapp.presentation.main.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.Film
import com.example.moviedbapp.R
import com.example.moviedbapp.databinding.FilmListFragmentBinding
import com.example.moviedbapp.presentation.IListFragment
import com.example.moviedbapp.presentation.adapters.ItemListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(), IListFragment {

    private var binding: FilmListFragmentBinding? = null
    private val viewModel: MovieListViewModel by viewModel()
    private lateinit var adapterMovie: ItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapterMovie = ItemListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FilmListFragmentBinding.inflate(inflater, container, false)
        showLoading(binding!!, true)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.rvFilms?.layoutManager = LinearLayoutManager(activity)
        binding?.rvFilms?.adapter = adapterMovie
        adapterMovie.setOnItemClickCallback(object : ItemListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Film) {
                selectedItem(data, view, R.id.action_dashboardFragment_to_detailFragment)
                Log.d("CLICKED", data.toString())
            }
        })

        viewModel.getTopRatedMovies().observe(viewLifecycleOwner, {
            if (it != null) {
                adapterMovie.setData(it)
                showLoading(binding!!, false)
            }
        })
    }
}