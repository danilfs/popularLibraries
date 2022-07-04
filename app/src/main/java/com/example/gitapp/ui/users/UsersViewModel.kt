package com.example.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitapp.domain.IUserRepository

class UsersViewModel(private val userRepository: IUserRepository) : ViewModel() {

    private val _state = MutableLiveData<UsersViewState>(UsersViewState.Loading)
    val state: LiveData<UsersViewState> get() = _state

    fun onRefresh() {
        _state.postValue(UsersViewState.Loading)
        userRepository.getUsers(
            onSuccess = { _state.postValue(UsersViewState.Success(it)) },
            onError = { _state.postValue(UsersViewState.Error(it)) }
        )
    }

}