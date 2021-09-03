package com.codetron.foodmarketmvp.base

import com.codetron.foodmarketmvp.model.response.base.Wrapper
import com.codetron.foodmarketmvp.model.response.error.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException

interface BasePresenterContract {

    fun subscribe()

    fun unSubscribe()

    fun handleException(error: Throwable): String {
        var message: String = error.message.toString()

        if (error is HttpException) {
            val errorResponse = error.response()?.errorBody()?.string()
            if (errorResponse != null) {
                message = try {
                    Gson().fromJson(errorResponse, Wrapper::class.java).meta?.message.toString()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Gson().fromJson(errorResponse, ErrorResponse::class.java).message.toString()
                }
            }
        }

        return message
    }
}