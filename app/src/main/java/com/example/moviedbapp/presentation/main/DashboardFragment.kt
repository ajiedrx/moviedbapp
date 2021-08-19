package com.example.moviedbapp.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.moviedbapp.R
import com.example.moviedbapp.databinding.FilmTabsFragmentBinding
import com.example.moviedbapp.presentation.adapters.SectionPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DashboardFragment : Fragment() {

    private var binding: FilmTabsFragmentBinding? = null

    companion object {
        val TAB_TITLES = arrayListOf(
            "Movies",
            "TV Show"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val actionBar = (activity as AppCompatActivity?)?.supportActionBar
        actionBar?.title = getString(R.string.dashboard_title)
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FilmTabsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.favorites) {
            try {
                view?.findNavController()
                    ?.navigate(R.id.action_dashboardFragment_to_favoriteFilmFragment)
            } catch (e: Exception) {
                Toast.makeText(requireActivity(), "Feature not Found", Toast.LENGTH_SHORT).show()
                Log.e("DashboardFragment", "Feature not Found")
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSectionPager()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(
            R.menu.dashboard_menu,
            menu
        )
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initSectionPager() {
        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2? = binding?.ffViewpager
        viewPager?.adapter = sectionPagerAdapter
        val tabs: TabLayout? = binding?.tabs
        if (tabs != null) {
            if (viewPager != null) {
                TabLayoutMediator(tabs, viewPager) { tab, position ->
                    tab.text = TAB_TITLES[position]
                }.attach()
            }
        }
    }

}