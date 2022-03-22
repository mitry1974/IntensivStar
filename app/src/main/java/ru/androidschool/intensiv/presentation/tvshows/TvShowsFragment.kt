package ru.androidschool.intensiv.presentation.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.api.TMDBInterface
import ru.androidschool.intensiv.data.repository.tvShows.TvShowsRemoteRepository
import ru.androidschool.intensiv.data.repository.tvShows.TvShowsRepository
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.domain.models.TvShow

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment), TvShowsPresenter.TvShowsView {
    private lateinit var binding: TvShowsFragmentBinding

    private val presenter by lazy {
        TvShowsPresenter(
            TvShowsRepository(
                TvShowsRemoteRepository(TMDBInterface.apiClient)
            )
        )
    }

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.detach()
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

        presenter.attach(this)

        binding.tvShowsRecyclerView.adapter = adapter

        presenter.loadTvShows()
    }

    override fun showTvShows(tvShows: List<TvShow>) {
        adapter.apply { addAll(tvShows.map { TvShowItem(it) {} }) }
    }

    override fun showLoader() {
        binding.tvshowsProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        binding.tvshowsProgressBar.visibility = View.GONE
    }

    override fun showToast(message: String, duration: Int) {
        Toast.makeText(context, message, duration).show()
    }
}
