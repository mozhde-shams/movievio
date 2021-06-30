package com.example.movievio.utils.recyclerview

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.movievio.R

class GenericAdapter<itemView : Any>(
    diffCallback: DiffUtil.ItemCallback<itemView>,
    private val clickListener: ItemClickListener,
    private val viewType: Int
) : PagingDataAdapter<itemView, SealedViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: SealedViewHolder, position: Int) {
        getItem(position)?.let { itemView ->
            holder.bind(itemView, clickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SealedViewHolder {
        return if (viewType == R.layout.movie_list_item)
            SealedViewHolder.MovieListViewHolder.from(parent)
        else
            SealedViewHolder.SimilarMoviesViewHolder.from(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return if (viewType == Types.MovieList.viewType)
            R.layout.movie_list_item
        else
            R.layout.similar_movies_item
    }

}

class ItemClickListener(val clickListener: (item: Any) -> Unit) {
    fun onClick(T: Any) = clickListener(T)
}

enum class Types(val viewType: Int) {
    MovieList(0),
    SimilarMovies(1)
}