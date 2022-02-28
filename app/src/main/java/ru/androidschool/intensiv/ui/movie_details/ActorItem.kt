package ru.androidschool.intensiv.ui.movie_details

import android.view.View
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.Actor
import ru.androidschool.intensiv.data.Movie
import ru.androidschool.intensiv.databinding.ItemActorBinding
import ru.androidschool.intensiv.databinding.ItemTvShowBinding

class ActorItem(
    private val content: Actor,
    private val onClick: (movie: Movie) -> Unit
): BindableItem<ItemActorBinding>() {
    override fun bind(view: ItemActorBinding, p1: Int) {
        view.actorName.text = content.name
    }

    override fun getLayout(): Int = R.layout.item_actor

    override fun initializeViewBinding(view: View)= ItemActorBinding.bind(view)

}