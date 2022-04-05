package ru.androidschool.intensiv.presentation.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.dagger.di.AppModule
import ru.androidschool.intensiv.dagger.di.DaggerMovieDetailsViewModelComponent
import ru.androidschool.intensiv.dagger.di.view_model_factory.ViewModelFactory
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.presentation.feed.FeedFragment
import ru.androidschool.intensiv.util.extensions.doOnChange
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {
    private lateinit var binding: MovieDetailsFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieDetailsViewModel

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)

        DaggerMovieDetailsViewModelComponent.builder()
            .appModule(AppModule(requireActivity().application))
            .build()
            .inject(this)
        viewModel  = ViewModelProvider(this, viewModelFactory)[MovieDetailsViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.actors.adapter = adapter
        viewModel.movieId =  arguments?.getInt(FeedFragment.KEY_ID)
        binding.filmDetailsFavorites.setOnCheckedChangeListener { _, isFavorite->
            viewModel.movieId?.let {
                viewModel.setFavoriteStatus(it, isFavorite)
            }
        }
        observeViewModel()
        viewModel.getMovieDetails()
    }

    private fun observeViewModel() {
        viewModel.isFavorite.doOnChange(this) {
            binding.filmDetailsFavorites.isChecked = it
        }

        viewModel.movieDetails.doOnChange(this) { md ->
            binding.apply {
                movieDetailsFilmName.text = md.title
                movieYear.text = md.year
                studio.text = md.productionCompanies
                genre.text = md.genres
                overview.text = md.overview
                rating.rating = md.voteAverage
            }

            Picasso.get()
                .load(md.posterPath)
                .into(binding.image)
        }

        viewModel.actors.doOnChange(this) { actorsList ->
            val actorsItems = actorsList.map { actor -> ActorItem(actor) {} }
            adapter.apply { addAll(actorsItems) }
        }


        viewModel.errorMessage.doOnChange(this) {
            showToast(it)
        }

    }
    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}
