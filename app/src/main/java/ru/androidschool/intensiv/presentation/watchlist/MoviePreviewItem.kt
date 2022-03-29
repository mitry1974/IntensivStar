package ru.androidschool.intensiv.presentation.watchlist

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.domain.models.Movie
import ru.androidschool.intensiv.databinding.ItemSmallBinding

class MoviePreviewItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemSmallBinding>() {

    override fun getLayout() = R.layout.item_small

    override fun bind(view: ItemSmallBinding, position: Int) {
        view.imagePreview.setOnClickListener {
            onClick.invoke(content)
        }

        Picasso.get()
            .load(content.posterPath)
            .into(view.imagePreview)
    }

    override fun initializeViewBinding(v: View): ItemSmallBinding = ItemSmallBinding.bind(v)
}
