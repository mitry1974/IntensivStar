package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.launch
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.entity.TvShow
import ru.androidschool.intensiv.data.repository.movies.tvShows.TvShowsRepository
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {
    private lateinit var binding: TvShowsFragmentBinding

    private val repository by lazy { TvShowsRepository() }

    private lateinit var tvShowsList: List<TvShow>

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

    private fun initData() {
        viewLifecycleOwner.lifecycleScope.launch {
            tvShowsList = repository.getTvShows()
            updateView()
        }
    }

    private fun updateView() {

        val tvShowItems = tvShowsList.map {
            TvShowItem(it) {}
        }
        adapter.apply { addAll(tvShowItems) }
    }
}