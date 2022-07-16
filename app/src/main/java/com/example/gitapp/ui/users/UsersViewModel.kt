package com.example.gitapp.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitapp.domain.IUserRepository
import com.example.gitapp.domain.model.User
import com.example.gitapp.ui.ViewState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class UsersViewModel(private val userRepository: IUserRepository) : ViewModel() {

    private val _viewState = BehaviorSubject.create<ViewState<List<User>>>()
    val viewState: Observable<ViewState<List<User>>> get() = _viewState

    init {
        requestUsers()
    }

    fun requestUsers() {
        _viewState.onNext(ViewState.Loading)
        userRepository.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = { _viewState.onNext(ViewState.Success(it)) },
                onError = { _viewState.onNext(ViewState.Error(it)) }
            )
    }

}