package com.example.gitapp.ui.users

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gitapp.app
import com.example.gitapp.domain.model.User
import com.example.gitapp.R
import com.example.gitapp.databinding.FragmentUsersBinding
import com.example.gitapp.domain.IUserRepository

class UsersFragment : Fragment(R.layout.fragment_users) {

    private val binding: FragmentUsersBinding by viewBinding()
    private var adapter = UsersAdapter(::showUserPageInBrowser)
    private val viewModel by lazy {
        ViewModelProvider(this, UsersViewModelFactory(app.userRepository))[UsersViewModel::class.java]
    }

    class UsersViewModelFactory(private val userRepository: IUserRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass == UsersViewModel::class.java) {
                return UsersViewModel(userRepository) as T
            }
            throw IllegalArgumentException ("Unknown ViewModel")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStartScreenState()
        setupUserSwipeRefresh()
        setupUserRecyclerView()
        setupRefreshButton()
        observeViewState()
        if (savedInstanceState == null) {
            viewModel.onRefresh() // uploading data only for the first time
        }
    }

    private fun observeViewState() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state) {
                UsersViewState.Loading -> showLoading()
                is UsersViewState.Success -> showUsers(state.users)
                is UsersViewState.Error -> showError(state.error)
            }
        }
    }

    private fun setupStartScreenState() {
        binding.usersRecyclerView.isVisible = false
        binding.errorScreen.errorLayout.isVisible = false
        binding.usersSwipeRefresh.isRefreshing = false
    }

    private fun setupRefreshButton() =
        binding.errorScreen.refreshButton.setOnClickListener {
            viewModel.onRefresh()
        }

    private fun setupUserSwipeRefresh() =
        binding.usersSwipeRefresh.setOnRefreshListener {
            viewModel.onRefresh()
        }

    private fun setupUserRecyclerView() {
        binding.usersRecyclerView.adapter = adapter
    }

    private fun showUsers(users: List<User>) {
        adapter.submitList(users)
        binding.usersSwipeRefresh.isRefreshing = false
        binding.errorScreen.errorLayout.isVisible = false
        binding.usersRecyclerView.isVisible = true
    }

    private fun showError(error: Throwable) {
        binding.usersSwipeRefresh.isRefreshing = false
        binding.errorScreen.errorLayout.isVisible = true
    }

    private fun showLoading() {
        binding.usersSwipeRefresh.isRefreshing = true
    }

    private fun showUserPageInBrowser(user: User) =
        Intent(Intent.ACTION_VIEW, Uri.parse(user.githubUrl)).also {
            startActivity(it)
        }

}