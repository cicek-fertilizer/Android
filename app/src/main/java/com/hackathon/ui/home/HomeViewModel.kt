package com.hackathon.ui.home

import android.content.Context
import com.hackathon.R
import com.hackathon.data.error.ServerError
import com.hackathon.di.ILogger
import com.hackathon.di.module.SchedulersModule
import com.hackathon.domain.auth.AuthTask
import com.hackathon.lib.event.ObservableResult
import com.hackathon.lib.typing.Err
import com.hackathon.lib.typing.Ok
import com.hackathon.ui.base.BaseViewModel

class HomeViewModel(
        private val logger: ILogger,
        schedulersModule: SchedulersModule,
        private val authTask: AuthTask
) : BaseViewModel(schedulersModule) {
    val onLoggedOut = ObservableResult<Unit>()

    fun logout(context: Context) {
        authTask.logout(context)
        onLoggedOut.trigger(Ok(Unit))
    }
}
