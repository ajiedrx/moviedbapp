package com.example.moviedbapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviedbapp.presentation.main.movie.MovieListFragment
import com.example.moviedbapp.presentation.main.tvshow.TvShowFragment

class SectionPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                MovieListFragment()
            1 -> fragment =
                TvShowFragment()
        }
        return fragment as Fragment
    }
}