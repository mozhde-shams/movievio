package com.example.movievio.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movievio.R
import com.example.movievio.databinding.FragmentMovieDetailsBinding
import com.example.movievio.di.ViewModelFactory
import com.example.movievio.model.MovieItem
import com.example.movievio.movieList.MovieListFragmentDirections
import com.example.movievio.utils.recyclerview.GenericAdapter
import com.example.movievio.utils.recyclerview.ItemClickListener
import com.example.movievio.utils.recyclerview.Types
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MovieDetailViewModel by viewModels { viewModelFactory }
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentMovieDetailsBinding
    private lateinit var similarMoviesAdapter: GenericAdapter<MovieItem>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieId(args.movieId)
        initRecyclerView()
        subscribeObservers()
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie_details,
            container,
            false
        )
        binding.movieDetailViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun getMovieId(movieId: Int) {
        viewModel.onMovieIdReceived(movieId)
    }

    private fun initRecyclerView() {
        val linLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
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

        similarMoviesAdapter =
            GenericAdapter(diffCallback, ItemClickListener { movieItem ->
                viewModel.onMovieItemClicked(movieItem as MovieItem)
            }, Types.SimilarMovies.viewType)

        binding.recyclerViwSimilarMovies.apply {
            layoutManager = linLayoutManager
            adapter = similarMoviesAdapter
        }
    }

    private fun subscribeObservers() {

        viewModel.similarMovies.observe(viewLifecycleOwner, Observer {
            similarMoviesAdapter.submitData(lifecycle, it)
        })

    }


}