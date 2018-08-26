package com.github.etfernandes.userspagination

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.etfernandes.userspagination.model.User

private const val PAGE_SIZE = 10

class UsersViewModel(dataSourceFactory: DataSource.Factory<Int, User>) : ViewModel() {

    val users: LiveData<PagedList<User>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()

        users = LivePagedListBuilder(dataSourceFactory, config).build()
    }
}
