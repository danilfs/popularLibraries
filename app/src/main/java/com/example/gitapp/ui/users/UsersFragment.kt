package com.example.gitapp.ui.users

import android.content.Context
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
import com.example.gitapp.ui.ViewModelFactory
import com.example.gitapp.ui.ViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class UsersFragment : Fragment(R.layout.fragment_users) {

    private val binding: FragmentUsersBinding by viewBinding()
    private var adapter = UsersAdapter(::onUserClick)
    private var controller: Controller? = null
    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(app.userRepository))[UsersViewModel::class.java]
    }

    private val disposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controller = context as? Controller
    }

    override fun onDetach() {
        super.onDetach()
        controller = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUserSwipeRefresh()
        setupUserRecyclerView()
        setupRefreshButton()
        observeViewState()
    }

    private fun observeViewState() {
        disposable.add(viewModel.viewState.subscribe { renderViewState(it) })
    }

    private fun setupRefreshButton() = binding.errorScreen.refreshButton.clicks
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy(
            onNext = { viewModel.requestUsers() }
        )

    private fun setupUserSwipeRefresh() =
        binding.usersSwipeRefresh.setOnRefreshListener {
            viewModel.requestUsers()
        }

    private fun setupUserRecyclerView() {
        binding.usersRecyclerView.adapter = adapter
    }

    private fun renderViewState(viewState: ViewState<List<User>>) {
        binding.usersSwipeRefresh.isRefreshing = viewState == ViewState.Loading
        binding.usersRecyclerView.isVisible = viewState is ViewState.Success
        binding.errorScreen.errorLayout.isVisible = viewState is ViewState.Error
        if (viewState is ViewState.Success) {
            adapter.submitList(viewState.data)
        }
    }

    private fun onUserClick(user: User) =
        controller?.navigateToUserDetails(user.id)


    interface Controller {
        fun navigateToUserDetails(userId: Int)
    }

}