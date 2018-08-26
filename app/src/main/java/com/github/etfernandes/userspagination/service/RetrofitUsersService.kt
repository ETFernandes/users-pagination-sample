package com.github.etfernandes.userspagination.service

import com.github.etfernandes.userspagination.model.UsersResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val ENDPOINT = "https://randomuser.me/"
private const val SEED = "a38261a8ef85503a"

class RetrofitUsersService : UsersService {

    private val api: Api

    init {
        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
            .baseUrl(ENDPOINT)
            .build()
            .create(Api::class.java)
    }

    override fun getUsers(page: Int, pageSize: Int) = api.getUsers(page, pageSize)

    interface Api {

        @GET("api/1.2?inc=name&nat=gb&seed=$SEED")
        fun getUsers(@Query("page") page: Int, @Query("results") pageSize: Int): Call<UsersResponse>
    }
}
