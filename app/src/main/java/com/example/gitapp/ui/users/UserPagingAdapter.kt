package com.example.gitapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gitapp.R
import com.example.gitapp.databinding.ItemUserBinding
import com.example.gitapp.ui.users.domain.model.User

class UserPagingAdapter :
    PagingDataAdapter<User, UserPagingAdapter.UserViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    override fun onViewRecycled(holder: UserViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User?) {
            if (user == null) {
                clear()
            } else {
                binding.loginTextView.text = user.id.toString()
                binding.loginTextView.isVisible = user.login.isNotBlank()
                binding.avatarImageView.load(user.avatarUrl) {
                    crossfade(true)
                    placeholder(R.drawable.bg_avatar_placeholder)
                }
            }
        }

        fun clear() {
            binding.avatarImageView.setImageResource(R.drawable.bg_avatar_placeholder)
            binding.loginTextView.isVisible = false
        }
    }
}


private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = oldItem == newItem
}