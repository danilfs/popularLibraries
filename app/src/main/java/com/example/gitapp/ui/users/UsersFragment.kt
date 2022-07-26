package com.example.gitapp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gitapp.databinding.FragmentUsersBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import ru.gidural.mykoin.viewModel

class UsersFragment : Fragment(R.layout.fragment_users) {

    private val binding: FragmentUsersBinding by viewBinding()
    private var adapter = UsersAdapter(::onUserClick)
    private var navController: INavController? = null
    private val viewModel: UsersViewModel by viewModel()
    private val disposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = context as? INavController
    }

    override fun onDetach() {
        super.onDetach()
        navController = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUserSwipeRefresh()
        setupUserRecyclerView()
        setupRefreshButton()
        observeViewState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }

    private fun observeViewState() {
        disposable.add(viewModel.viewState.subscribe { renderViewState(it) })
    }

    private fun setupRefreshButton() = binding.errorScreen.refreshButton.clicks
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeBy(
            onNext = {
                refreshScreen()
            }
        )

    private fun setupUserSwipeRefresh() =
        binding.usersSwipeRefresh.setOnRefreshListener {
            refreshScreen()
        }

    private fun refreshScreen() {
        viewModel.requestUsers()
        adapter.notifyDataSetChanged()
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
        navController?.navigateToUserDetails(user.id)

}