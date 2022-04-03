package ru.androidschool.intensiv.presentation.movie_details

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.data.repository.details.MovieDetailsRepository
import ru.androidschool.intensiv.data.repository.favorites.FavoritesRepository
import ru.androidschool.intensiv.domain.models.Actor
import ru.androidschool.intensiv.domain.models.MovieDetails
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    var movieId: Int? = 0

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails = _movieDetails

    private val _actors = MutableLiveData<List<Actor>>()
    val actors = _actors

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _posterImage = MutableLiveData<Bitmap>()
    val posterImage = _posterImage

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage

    @SuppressLint("CheckResult")
    fun getMovieDetails() {
        movieId?.let {
            movieDetailsRepository.getMovieDetails(it)
                .doOnError { e ->
                    println(e)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { md ->
                    _movieDetails.postValue(md)
                    Picasso.get()
                        .load(md.posterPath)
                        .into(object : Target {
                            override fun onBitmapLoaded(
                                bitmap: Bitmap?,
                                from: Picasso.LoadedFrom?
                            ) {
                                _posterImage.postValue(bitmap)
                            }

                            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) =
                                _errorMessage.postValue("Error loading movie details poster")

                            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
                        })
                }

            movieDetailsRepository.getActors(it)
                .doOnError { e ->
                    println(e)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { actorsList -> _actors.postValue(actorsList) }

            getFavoriteStatus(it)
        }
    }

    @SuppressLint("CheckResult")
    fun getFavoriteStatus(movieId: Int) {
        favoritesRepository.getFavoriteByMovieId(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _isFavorite.postValue(true)
            }

    }

    fun setFavoriteStatus(movieId: Int, isFavorite: Boolean) {
        favoritesRepository.updateFavoriteStatus(movieId, isFavorite)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            )
    }


}