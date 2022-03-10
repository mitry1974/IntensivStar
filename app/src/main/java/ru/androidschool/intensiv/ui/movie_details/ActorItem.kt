package ru.androidschool.intensiv.ui.movie_details

import android.view.View
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.entity.Actor
import ru.androidschool.intensiv.data.entity.Movie
import ru.androidschool.intensiv.databinding.ItemActorBinding

class ActorItem(
    private val content: Actor,
    private val onClick: (movie: Movie) -> Unit
) : BindableItem<ItemActorBinding>() {
    override fun bind(view: ItemActorBinding, p1: Int) {
        view.actorName.text = content.name.split(" ").joinToString("\n")
        Picasso.get()
            .load(content.profilePath)
            .into(view.actorImage)
    }

    override fun getLayout(): Int = R.layout.item_actor

    override fun initializeViewBinding(view: View) = ItemActorBinding.bind(view)

}