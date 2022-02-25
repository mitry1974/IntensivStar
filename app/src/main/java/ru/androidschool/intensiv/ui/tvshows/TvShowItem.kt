package ru.androidschool.intensiv.ui.tvshows


import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Movie
import ru.androidschool.intensiv.databinding.ItemTvShowBinding
import ru.androidschool.intensiv.databinding.ItemWithTextBinding

class TvShowItem(
    private val content: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemTvShowBinding>() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(view: ItemTvShowBinding, p1: Int) {
        view.description.text = content.title
        view.content.setOnClickListener {
            onClick.invoke(content)
        }
        // TODO Получать из модели
        Picasso.get()
            .load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")
            .into(view.imagePreview)
    }

    override fun initializeViewBinding(view: View): ItemTvShowBinding = ItemTvShowBinding.bind(view)
}