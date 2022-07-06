package com.example.gitapp.ui.userDetails

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.gitapp.R
import com.example.gitapp.app
import com.example.gitapp.databinding.FragmentUserDetailsBinding
import com.example.gitapp.domain.model.User
import com.example.gitapp.ui.ViewModelFactory
import com.example.gitapp.ui.ViewState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class UserDetailsFragment : Fragment(R.layout.fragment_user_details) {

    private val binding: FragmentUserDetailsBinding by viewBinding()
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(app.userRepository)
        )[UserDetailsViewModel::class.java]
    }

    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()
        if (savedInstanceState == null) {
            requestUserDetails()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }

    private fun observeViewState() {
        disposable.add(viewModel.viewState.subscribe { renderViewState(it) })
    }

    private fun requestUserDetails() {
        val userId = requireArguments().getInt(KEY_USER_ID)
        viewModel.requestUserDetails(userId)
    }

    private fun renderViewState(viewState: ViewState<User>) {
        if (viewState is ViewState.Success) {
            binding.avatarImageView.load(viewState.data.avatarUrl)
            binding.nameTextView.text = viewState.data.login
        }
    }

    companion object {
        private const val KEY_USER_ID = "user_id"

        fun getInstance(userId: Int) = UserDetailsFragment().apply {
            arguments = bundleOf(KEY_USER_ID to userId)
        }
    }

}