package ru.androidschool.intensiv.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.MockRepository
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import ru.androidschool.intensiv.ui.feed.MovieItem

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {
    private lateinit var binding: TvShowsFragmentBinding

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
        binding.tvShowsRecyclerView.layoutManager = LinearLayoutManager(context)

        val moviesList =
            MockRepository.getMovies().map {
                TvShowItem(it) {
                }
            }.toList()


        adapter.apply { addAll(moviesList) }
    }
}