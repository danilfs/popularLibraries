package com.example.gitapp.ui.users

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gitapp.app
import com.example.gitapp.domain.model.User
import com.example.gitapp.R
import com.example.gitapp.databinding.FragmentUsersBinding

class UsersFragment : Fragment(R.layout.fragment_users), UsersContract.View {

    private val binding: FragmentUsersBinding by viewBinding()
    private var adapter = UsersAdapter(::showUserPageInBrowser)
    private val presenter by lazy { app.usersPresenter }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStartScreenState()
        setupUserSwipeRefresh()
        setupUserRecyclerView()
        setupRefreshButton()
        presenter.attach(this)
        if (savedInstanceState == null) {
            presenter.onRefresh() // uploading data only for the first time
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detach()
    }

    private fun setupStartScreenState() {
        binding.usersRecyclerView.isVisible = false
        binding.errorScreen.errorLayout.isVisible = false
        binding.usersSwipeRefresh.isRefreshing = false
    }

    private fun setupRefreshButton() =
        binding.errorScreen.refreshButton.setOnClickListener {
            presenter.onRefresh()
        }

    private fun setupUserSwipeRefresh() =
        binding.usersSwipeRefresh.setOnRefreshListener {
            presenter.onRefresh()
        }

    private fun setupUserRecyclerView() {
        binding.usersRecyclerView.adapter = adapter
    }

    override fun showUsers(users: List<User>) {
        adapter.submitList(users)
        binding.usersSwipeRefresh.isRefreshing = false
        binding.errorScreen.errorLayout.isVisible = false
        binding.usersRecyclerView.isVisible = true
    }

    override fun showError(error: Throwable) {
        binding.usersSwipeRefresh.isRefreshing = false
        binding.errorScreen.errorLayout.isVisible = true
    }

    override fun showLoading() {
        binding.usersSwipeRefresh.isRefreshing = true
    }

    private fun showUserPageInBrowser(user: User) =
        Intent(Intent.ACTION_VIEW, Uri.parse(user.githubUrl)).also {
            startActivity(it)
        }

}