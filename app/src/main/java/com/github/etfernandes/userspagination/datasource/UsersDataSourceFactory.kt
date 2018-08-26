package com.github.etfernandes.userspagination.datasource

import androidx.paging.DataSource
import com.github.etfernandes.userspagination.model.User
import com.github.etfernandes.userspagination.service.UsersService

class UsersDataSourceFactory(private val usersService: UsersService) : DataSource.Factory<Int, User>() {

    override fun create() = UsersPagedDataSource(usersService)
}
