package com.example.movievio.utils.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.movievio.databinding.MovieListItemBinding
import com.example.movievio.databinding.SimilarMoviesItemBinding
import com.example.movievio.model.MovieItem

sealed class SealedViewHolder(binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(item: Any, clickListener: Any)

    class MovieListViewHolder private constructor(private val binding: MovieListItemBinding) :
        SealedViewHolder(binding) {

        override fun bind(item: Any, clickListener: Any) {
            binding.movieItem = item as MovieItem
            binding.clickListener = clickListener as ItemClickListener
        }

        companion object {
            fun from(parent: ViewGroup): MovieListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieListItemBinding.inflate(layoutInflater, parent, false)
                return MovieListViewHolder(binding)
            }
        }

    }

    class SimilarMoviesViewHolder private constructor(private val binding: SimilarMoviesItemBinding) :
        SealedViewHolder(binding) {

        override fun bind(item: Any, clickListener: Any) {
            binding.movieItem = item as MovieItem
            binding.clickListener = clickListener as ItemClickListener
        }

        companion object {
            fun from(parent: ViewGroup): SimilarMoviesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SimilarMoviesItemBinding.inflate(layoutInflater, parent, false)
                return SimilarMoviesViewHolder(binding)
            }
        }

    }

}
