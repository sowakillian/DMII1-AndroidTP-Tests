package com.openclassrooms.magicgithub.ui.user_list

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.model.User
import javax.swing.text.View
import javax.swing.text.html.ImageView

// TODO Utiliser le view binding
class ListUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // FOR DESIGN ---
    private val avatar: ImageView
    private val username: TextView
    private val deleteButton: ImageButton
    fun bind(user: User, callback: UserListAdapter.Listener) {
        Glide.with(itemView.context)
            .load(user.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(avatar)
        username.text = user.login
        deleteButton.setOnClickListener { view: View? -> callback.onClickDelete(user) }
    }

    init {
        avatar = itemView.findViewById(R.id.item_list_user_avatar)
        username = itemView.findViewById(R.id.item_list_user_username)
        deleteButton = itemView.findViewById(R.id.item_list_user_delete_button)
    }
}
