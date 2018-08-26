package com.github.etfernandes.userspagination.service

import com.github.etfernandes.userspagination.model.UsersResponse
import retrofit2.Call

interface UsersService {

    fun getUsers(page: Int, pageSize: Int): Call<UsersResponse>
}
