package ru.androidschool.intensiv.presentation.feed

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.FeedFragmentBinding
import ru.androidschool.intensiv.databinding.FeedHeaderBinding
import ru.androidschool.intensiv.domain.interactors.MoviesInteractor
import ru.androidschool.intensiv.domain.models.Movie
import ru.androidschool.intensiv.presentation.afterTextChanged
import ru.androidschool.intensiv.util.extensions.doOnChange
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private var _binding: FeedFragmentBinding? = null
    private var _searchBinding: FeedHeaderBinding? = null

    private lateinit var viewModel: FeedViewModel

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
        viewModel = ViewModelProvider(
            this, FeedViewModelFactory(
                MoviesInteractor.build(requireActivity().application)
            )
        )[FeedViewModel::class.java]

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

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isLoading.doOnChange(this) {
            if (it) showLoader() else hideLoader()
        }

        viewModel.error.doOnChange(this) {
            showToast(it)
        }

        viewModel.feedData.doOnChange(this) { lists ->
            lists[R.string.recommended]?.let {
                applyMovieList(R.string.recommended, it)
            }

            lists[R.string.popular]?.let {
                applyMovieList(R.string.popular, it)
            }

            lists[R.string.upcoming]?.let {
                applyMovieList(R.string.upcoming, it)
            }
        }
    }

    private fun applyMovieList(containerName: Int, movieList: List<Movie>) {
        val movieItemList = movieList.map { mv ->
            MovieItem(mv) { movie -> openMovieDetails(movie) }
        }
        adapter.apply {
            addAll(
                listOf(
                    MainCardContainer(containerName, movieItemList)
                )
            )
        }
    }

    private fun showLoader() {
        binding.feedProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.feedProgressBar.visibility = View.GONE
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
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
