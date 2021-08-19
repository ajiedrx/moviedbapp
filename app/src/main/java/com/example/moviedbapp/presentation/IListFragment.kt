package com.example.moviedbapp.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.core.domain.model.Film
import com.example.moviedbapp.databinding.FilmListFragmentBinding

interface IListFragment {
    fun showLoading(itemRowBinding: FilmListFragmentBinding, state: Boolean) {
        if (state) {
            itemRowBinding.progressBar.visibility = View.VISIBLE
        } else {
            itemRowBinding.progressBar.visibility = View.GONE
        }
    }

    fun selectedItem(data: Film, view: View, actionId: Int) {
        val bundle = Bundle()
        bundle.putParcelable(Film.FILM, data)
        view.findNavController().navigate(actionId, bundle)
    }
}