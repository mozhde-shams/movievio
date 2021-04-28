package com.example.movievio.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movievio.R
import com.example.movievio.databinding.FragmentMovieListBinding
import com.example.movievio.di.ViewModelFactory
import com.example.movievio.model.MovieItem
import com.example.movievio.utils.recyclerview.GenericAdapter
import com.example.movievio.utils.recyclerview.ItemClickListener
import com.example.movievio.utils.recyclerview.Types
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModelMovieList: MovieListViewModel by viewModels { viewModelFactory }
    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieListAdapter: GenericAdapter<MovieItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        subscribeObservers()
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_list,
            container,
            false
        )
        binding.movieListViewModel = viewModelMovieList
        binding.lifecycleOwner = this
    }

    private fun initRecyclerView() {
        val gridLayoutManager = GridLayoutManager(
            context,
            2
        )

        val diffCallback = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(
                oldItem: MovieItem,
                newItem: MovieItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieItem,
                newItem: MovieItem
            ): Boolean {
                return oldItem == newItem
            }
        }

        movieListAdapter = GenericAdapter(diffCallback, ItemClickListener { movieItem ->
            viewModelMovieList.onMovieItemClicked(movieItem as MovieItem)
        }, Types.MovieList.viewType)

        binding.recyclerViwMovieList.apply {
            layoutManager = gridLayoutManager
            adapter = movieListAdapter
        }

    }

    private fun subscribeObservers() {
        viewModelMovieList.movies.observe(viewLifecycleOwner, Observer {
            movieListAdapter.submitData(lifecycle, it)
        })

        viewModelMovieList.navigateToDetails.observe(viewLifecycleOwner, Observer { movieEvent ->
            movieEvent.getContentIfNotHandled()
                ?.let { // Only proceed if the event has never been handled
                    val action =
                        MovieListFragmentDirections.actionMovieListToMovieDetailsFragment(
                            movieEvent.peekContent()
                        )
                    findNavController().navigate(action)
                }

        })

    }

}