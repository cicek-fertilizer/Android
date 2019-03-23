package com.hackathon.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hackathon.databinding.SplashFragmentBinding
import com.hackathon.di.ILogger
import com.hackathon.ui.base.BaseFragment
import com.hackathon.ui.splash.SplashViewModel
import org.koin.android.ext.android.inject


class SplashFragment: BaseFragment<SplashViewModel>(SplashViewModel::class) {
    private val logger: ILogger by inject()
    private lateinit var dataBinding: SplashFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Base Fragment on create view, calls view model on screen created
        super.onCreateView(inflater, container, savedInstanceState)

        // Bind View Model to the layout
        dataBinding = SplashFragmentBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = this

        viewModel.isLoggedIn(requireContext())

        return dataBinding.root
    }

    override fun bindCommands() {
        super.bindCommands()

        viewModel.onAuthResult.runWhenFinished(this,
                onSuccess = {
                    if(it)
                        navigateToHome()
                    else
                        navigateToLogin()
                },
                onError = {
                    navigateToLogin()
                })
    }

    private fun navigateToHome() {
        navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }

    private fun navigateToLogin() {
        navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }
}
