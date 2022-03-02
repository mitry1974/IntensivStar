package ru.androidschool.intensiv.ui.feed

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.launch
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.entity.Movie
import ru.androidschool.intensiv.data.repository.movies.MoviesRepository
import ru.androidschool.intensiv.databinding.FeedFragmentBinding
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private var _binding: FeedFragmentBinding? = null
    private var _searchBinding: FeedHeaderBinding? = null
    private val repository by lazy {  MoviesRepository.getRepository() }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val searchBinding get() = _searchBinding!!

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FeedFragmentBinding.inflate(inflater, container, false)
        _searchBinding = FeedHeaderBinding.bind(binding.root)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        searchBinding.searchToolbar.binding.searchEditText.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }

        binding.moviesRecyclerView.adapter = adapter
        initData()
    }

    private fun updateView() {
        val nowPlayingList = repository.nowPlaying.map {
            MovieItem(it.value) { movie ->
                openMovieDetails(
                    movie
                )
            }
        }
        adapter.apply { addAll(listOf(MainCardContainer(R.string.recommended, nowPlayingList))) }


        val upcomingList = repository.upcoming.map {
            MovieItem(it.value) { movie ->
                openMovieDetails(
                    movie
                )
            }
        }
        adapter.apply { addAll(listOf(MainCardContainer(R.string.upcoming, upcomingList))) }

        val popularList = repository.popular.map {
            MovieItem(it.value) { movie ->
                openMovieDetails(
                    movie
                )
            }
        }
        adapter.apply { listOf(MainCardContainer(R.string.popular, popularList)) }
    }

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repository.getNowPlaying()
            repository.getPopular()
            repository.getUpcoming()

            updateView()
        }
    }

    private fun openMovieDetails(movie: Movie) {
        val bundle = Bundle()
        bundle.putInt(KEY_ID, movie.id)
        findNavController().navigate(R.id.movie_details_fragment, bundle, options)
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        searchBinding.searchToolbar.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _searchBinding = null
    }

    companion object {
        const val MIN_LENGTH = 3
        const val KEY_ID = "id"
        const val KEY_SEARCH = "search"
    }
}
