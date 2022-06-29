package com.example.gitapp.ui.users

import com.example.gitapp.domain.IUserRepository

class UsersPresenter(private val userRepository: IUserRepository) : UsersContract.Presenter {

    private var view: UsersContract.View? = null
    private var state: UsersViewState = UsersViewState.Loading

    override fun attach(view: UsersContract.View) {
        this.view = view
        renderState(state)
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        updateState(UsersViewState.Loading)
        userRepository.getUsers(
            onSuccess = { updateState(UsersViewState.Success(it)) },
            onError = {updateState(UsersViewState.Error(it))}
        )
    }

    private fun updateState(state: UsersViewState) {
        this.state = state
        renderState(state)
    }

    private fun renderState(state: UsersViewState) = when (state) {
        UsersViewState.Loading -> view?.showLoading()
        is UsersViewState.Success -> view?.showUsers(state.users)
        is UsersViewState.Error -> view?.showError(state.error)
    }

}