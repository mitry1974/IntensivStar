package ru.androidschool.intensiv.ui.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.local.database.MoviesDatabase
import ru.androidschool.intensiv.data.repository.details.MovieDetailsRepository
import ru.androidschool.intensiv.data.repository.favorites.FavoritesRepository
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.ui.feed.FeedFragment

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {
    private lateinit var binding: MovieDetailsFragmentBinding

    private val movieDetailsRepository by lazy { MovieDetailsRepository() }
    private val favoritesRepository by lazy {
        FavoritesRepository(
            MoviesDatabase.buildDatabase(
                requireActivity().application
            )
        )
    }

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
        binding.filmDetailsFavorites.setOnCheckedChangeListener { _, isChecked ->
            movieId?.let {
                favoritesRepository.updateFavoriteStatus(it, isChecked)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                    )
            }
        }
        updateView()
    }

    @SuppressLint("CheckResult")
    private fun updateView() {
        movieId?.let {
            movieDetailsRepository.getMovieDetails(it)
                .doOnError { e ->
                    println(e)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { md ->
                    binding.apply {
                        movieDetailsFilmName.text = md.title
                        movieYear.text = md.year
                        studio.text = md.productionCompanies
                        genre.text = md.genres
                        overview.text = md.overview
                        rating.rating = md.voteAverage

                        favoritesRepository.getFavoriteByMovieId(it)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { filmDetailsFavorites.isChecked = true }

                        Picasso.get()
                            .load(md.posterPath)
                            .into(image)
                    }
                }
            movieDetailsRepository.getActors(it)
                .doOnError { e ->
                    println(e)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { actorsList ->
                    val actorsItems = actorsList.map { actor -> ActorItem(actor) {} }
                    adapter.apply { addAll(actorsItems) }
                }
        }
    }
}
