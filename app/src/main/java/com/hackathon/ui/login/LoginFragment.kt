package com.hackathon.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hackathon.databinding.LoginFragmentBinding
import com.hackathon.di.ILogger
import com.hackathon.ui.base.BaseFragment
import com.hackathon.ui.splash.SplashFragmentDirections
import org.koin.android.ext.android.inject


class LoginFragment : BaseFragment<LoginViewModel>(LoginViewModel::class) {
    private val logger: ILogger by inject()
    private lateinit var dataBinding: LoginFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Base Fragment on create view, calls view model on screen created
        super.onCreateView(inflater, container, savedInstanceState)

        // Bind View Model to the layout
        dataBinding = LoginFragmentBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = this

        dataBinding.username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.username = s.toString()
            }

        })
        dataBinding.password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.password = s.toString()
            }

        })
        dataBinding.loginButton.setOnClickListener {
            viewModel.isLoggedIn()
            dataBinding.progressLayout.visibility = View.VISIBLE
        }

        return dataBinding.root
    }

    override fun bindCommands() {
        super.bindCommands()

        viewModel.onLoggedIn.runWhenFinished(this,
                onSuccess = {
                    navigateToHome()
                },
                onError = {
                    dataBinding.progressLayout.visibility = View.GONE
                    showErrorDialog(it)
                })
    }

    private fun navigateToHome() {
        navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }
}
