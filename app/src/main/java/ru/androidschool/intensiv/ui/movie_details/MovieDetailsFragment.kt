package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.repository.mock.MockRepository
import ru.androidschool.intensiv.data.entity.Movie
import ru.androidschool.intensiv.data.repository.movies.MoviesRepository
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.ui.feed.FeedFragment

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {
    private lateinit var binding: MovieDetailsFragmentBinding

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val movieId by lazy {
        arguments?.getInt(FeedFragment.KEY_ID)
    }

//    private fun getMovieById(movieTitle: String?): Movie? = ""
//        //MockRepository.getMovieByTitle(movieTitle)

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

        updateView()
    }

    private fun updateView() {
        val repository = MoviesRepository.getRepository()
//        val movie = repositorygetMovieByTitle(movieTitle)
//        movie?.let { it ->
//            val actorsItems = it.actors.map { actor -> ActorItem(actor) {} }
//            adapter.apply { addAll(actorsItems) }
//
//            binding.apply {
//                movieDetailsFilmName.text = movie.title
//                movieYear.text = movie.year.toString()
//                studio.text = movie.studio
//                genre.text = movie.genres.joinToString(", ")
//                rating.rating = movie.rating
//            }
//
//        }
    }
}

