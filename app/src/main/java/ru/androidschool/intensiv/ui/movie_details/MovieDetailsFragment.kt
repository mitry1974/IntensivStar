package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.launch
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.entity.Actor
import ru.androidschool.intensiv.data.entity.MovieDetails
import ru.androidschool.intensiv.data.repository.movies.MoviesRepository
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.ui.feed.FeedFragment

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {
    private lateinit var binding: MovieDetailsFragmentBinding

    private val repository by lazy { MoviesRepository.getRepository() }

    private lateinit var movieDetails: MovieDetails
    private lateinit var actors: List<Actor>



    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val movieId by lazy {
        arguments?.getInt(FeedFragment.KEY_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actors.adapter = adapter

        initData()
    }

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            movieId?.let {
                movieDetails = repository.getMovieDetails(it)
                actors = repository.getCredits(it)
            }
            updateView()
        }
    }

    private fun updateView() {


        movieDetails?.let { md ->
            val actorsItems = actors.map { actor -> ActorItem(actor) {} }
            adapter.apply { addAll(actorsItems) }

            binding.apply {
                movieDetailsFilmName.text = md.title
                movieYear.text = md.year
                studio.text = md.productionCompanies
                genre.text = md.genre
                overview.text = md.overview
                rating.rating = md.voteAverage / 2

                Picasso.get()
                    .load(movieDetails.posterPath)
                    .into(image)
            }

        }
    }
}

