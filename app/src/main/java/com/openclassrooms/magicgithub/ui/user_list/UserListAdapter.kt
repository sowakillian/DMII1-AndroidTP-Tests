package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.databinding.ItemListUsersBinding
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.utils.UserDiffCallback

class UserListAdapter(users: List<User>, private val callback: Listener) : RecyclerView.Adapter<ListUserViewHolder>() {
    // FOR DATA ---
    private val users: MutableList<User> = ArrayList(users)
    private lateinit var binding: ItemListUsersBinding

    interface Listener {
        fun onClickDelete(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        binding = ItemListUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(users[position], callback)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    // PUBLIC API ---
    fun updateList(newList: List<User>) {
        println("**i update list")
        println("**" + users.count())
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(newList, users))
        println("**$users")
        users.clear()
        users.addAll(ArrayList(newList))
        println("**" + users.count())
        diffResult.dispatchUpdatesTo(this)
    }
}