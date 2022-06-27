package com.example.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gitapp.R
import com.example.gitapp.databinding.ItemUserBinding
import com.example.gitapp.ui.users.domain.model.User

class UsersAdapter : ListAdapter<User, UsersAdapter.UsersViewHolder>(USERS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    class UsersViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.loginTextView.text = user.login
            binding.loginTextView.isVisible = user.login.isNotBlank()
            binding.avatarImageView.load(user.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.bg_avatar_placeholder)
            }
        }
    }
}


private val USERS_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
}