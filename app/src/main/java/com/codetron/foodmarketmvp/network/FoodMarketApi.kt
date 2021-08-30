package com.codetron.foodmarketmvp.network

import com.codetron.foodmarketmvp.model.response.base.Wrapper
import com.codetron.foodmarketmvp.model.response.login.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FoodMarketApi {

    @POST(LOGIN)
    @FormUrlEncoded
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<Wrapper<LoginResponse>>

}