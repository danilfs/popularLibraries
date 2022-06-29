package com.example.gitapp.ui.users

import com.example.gitapp.domain.model.User


interface UsersContract {

    interface View {
        fun showLoading()
        fun showUsers(users: List<User>)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
    }

}