package com.hackathon.ui.home

import android.os.Bundle
import android.view.*
import com.hackathon.R
import com.hackathon.databinding.HomeFragmentBinding
import com.hackathon.di.ILogger
import com.hackathon.ui.base.BaseFragment
import org.koin.android.ext.android.inject


class HomeFragment : BaseFragment<HomeViewModel>(HomeViewModel::class) {
    private val logger: ILogger by inject()
    private lateinit var dataBinding: HomeFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Base Fragment on create view, calls view model on screen created
        super.onCreateView(inflater, container, savedInstanceState)

        // Bind View Model to the layout
        dataBinding = HomeFragmentBinding.inflate(inflater, container, false)
        dataBinding.lifecycleOwner = this
        setHasOptionsMenu(true)

        dataBinding.progressLayout.visibility = View.GONE

        dataBinding.cameraButton.setOnClickListener {
            navigateToCamera()
        }


        return dataBinding.root
    }

    override fun bindCommands() {
        super.bindCommands()

        viewModel.onLoggedOut.runWhenFinished(this,
                onSuccess = {
                    navigateToLogin()
                },
                onError = {
                    navigateToLogin()
                })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_menu_logout -> {
                viewModel.logout(requireContext())
                true
            }
            else -> false
        }
    }

    private fun navigateToCamera() {
        navigate(HomeFragmentDirections.actionHomeFragmentToCameraFragment())
    }

    private fun navigateToLogin() {
        navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
    }
}
