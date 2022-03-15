package ru.androidschool.intensiv.ui.tvshows


import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.local.database.entity.TvShow
import ru.androidschool.intensiv.databinding.ItemTvShowBinding

class TvShowItem(
    private val data: TvShow,
    private val onClick: (tvShow: TvShow) -> Unit
) : BindableItem<ItemTvShowBinding>() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(view: ItemTvShowBinding, p1: Int) {
        view.apply {
            description.text = data.name
            rating.rating = data.voteAverage
            content.setOnClickListener {
                onClick.invoke(data)
            }
            // TODO Получать из модели
            Picasso.get()
                .load(data.posterPath)
                .into(view.imagePreview)
        }
    }

    override fun initializeViewBinding(view: View): ItemTvShowBinding = ItemTvShowBinding.bind(view)
}