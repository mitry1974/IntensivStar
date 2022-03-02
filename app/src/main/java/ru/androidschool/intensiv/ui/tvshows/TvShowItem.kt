package ru.androidschool.intensiv.ui.tvshows


import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Movie
import ru.androidschool.intensiv.databinding.ItemTvShowBinding

class TvShowItem(
    private val data: Movie,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemTvShowBinding>() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(view: ItemTvShowBinding, p1: Int) {
        view.apply {
            description.text = data.title
            content.setOnClickListener {
                onClick.invoke(data)
            }
            // TODO Получать из модели
            Picasso.get()
                .load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")
                .into(view.imagePreview)
        }
    }

    override fun initializeViewBinding(view: View): ItemTvShowBinding = ItemTvShowBinding.bind(view)
}