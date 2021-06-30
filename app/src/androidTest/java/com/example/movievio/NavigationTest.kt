package com.example.movievio

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.movievio.main.MainActivity
import com.example.movievio.model.MovieItem
import com.example.movievio.utils.recyclerview.GenericAdapter
import com.example.movievio.utils.recyclerview.ItemClickListener
import com.example.movievio.utils.recyclerview.SealedViewHolder
import com.example.movievio.utils.recyclerview.Types
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    private val movies = listOf(
        MovieItem(
            1,
            null,
            "first movie"
        )
    )

    private lateinit var diffCallback: DiffUtil.ItemCallback<MovieItem>
    private lateinit var genericAdapter: GenericAdapter<MovieItem>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)

        diffCallback = object : DiffUtil.ItemCallback<MovieItem>() {
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

        genericAdapter = GenericAdapter(
            diffCallback,
            ItemClickListener {},
            Types.MovieList.viewType
        )

    }

    @Test
    fun testNavigation() {
        onView(withId(R.id.linLayoutMovieListContainer)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerViwMovieList)).check(matches(isDisplayed()))

        val pagingData = PagingData.from(movies)

        onView(withId(R.id.recyclerViwMovieList)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))

            recyclerView.adapter = genericAdapter
            lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

            genericAdapter.submitData(lifecycle, pagingData)
            assertEquals(1, genericAdapter.itemCount)
        }.perform(
            RecyclerViewActions.actionOnItemAtPosition<SealedViewHolder.MovieListViewHolder>(
                0,
                click()
            )
        )
    }

}