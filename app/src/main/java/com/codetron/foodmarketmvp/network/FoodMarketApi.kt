package com.codetron.foodmarketmvp.network

import com.codetron.foodmarketmvp.model.response.base.Wrapper
import com.codetron.foodmarketmvp.model.response.checkout.CheckoutResponse
import com.codetron.foodmarketmvp.model.response.food.FoodBaseResponse
import com.codetron.foodmarketmvp.model.response.food.FoodResponse
import com.codetron.foodmarketmvp.model.response.login.LoginResponse
import com.codetron.foodmarketmvp.model.response.register.RegisterResponse
import com.codetron.foodmarketmvp.model.response.user.UserResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*

interface FoodMarketApi {

    @FormUrlEncoded
    @POST(PATH_LOGIN)
    fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<Wrapper<LoginResponse>>

    @FormUrlEncoded
    @POST(PATH_REGISTER)
    fun userRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") passwordConfirmation: String,
        @Field("address") address: String,
        @Field("city") city: String,
        @Field("houseNumber") houseNumber: String,
        @Field("phoneNumber") phoneNumber: String,
    ): Observable<Wrapper<RegisterResponse>>

    @Multipart
    @POST(PATH_PHOTO)
    fun photoRegister(
        @Header("Authorization") token: String,
        @Part profileImage: MultipartBody.Part
    ): Observable<Wrapper<List<String>>>

    @GET(PATH_FOOD)
    fun getAllFood(
        @Query("limit") limit: Int = 10,
        @Query("types") types: String? = null
    ): Observable<Wrapper<FoodBaseResponse>>

    @GET(PATH_FOOD)
    fun getFoodById(
        @Query("id") id: Int
    ): Observable<Wrapper<FoodResponse>>

    @GET(PATH_USER)
    fun getUser(
        @Header("Authorization") token: String,
    ): Observable<Wrapper<UserResponse>>

    @FormUrlEncoded
    @POST(PATH_CHECKOUT)
    fun foodCheckout(
        @Field("food_id") foodId: Int,
        @Field("user_id") userId: Int,
        @Field("quantity") quantity: Int,
        @Field("total") total: Int,
        @Field("status") status: String
    ): Observable<Wrapper<CheckoutResponse>>


    @POST(PATH_LOGOUT)
    fun userLogout(
        @Header("Authorization") token: String,
    ): Observable<Wrapper<Boolean>>

}