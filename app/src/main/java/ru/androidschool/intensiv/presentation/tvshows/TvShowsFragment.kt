package ru.androidschool.intensiv.presentation.tvshows

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.repository.tvShows.TvShowsRepository
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {
    private lateinit var binding: TvShowsFragmentBinding

    private val repository by lazy { TvShowsRepository() }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TvShowsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvShowsRecyclerView.adapter = adapter

        initData()
    }

    @SuppressLint("CheckResult")
    private fun initData() {
        repository.getTvShows().map {
            it.map { tsi -> TvShowItem(tsi) {} }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { binding.tvshowsProgressBar.visibility = View.VISIBLE }
            .doFinally { binding.tvshowsProgressBar.visibility = View.GONE }
            .subscribe { list -> adapter.apply { addAll(list) } }
    }
}
