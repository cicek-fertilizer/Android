package com.hackathon.data.api

import com.hackathon.data.error.BaseError
import com.hackathon.lib.typing.Result
import io.reactivex.Maybe
import io.reactivex.Single

typealias ApiResult<T> = Single<out Result<T, BaseError>>
typealias ApiMaybe<T> = Maybe<out Result<T, BaseError>>