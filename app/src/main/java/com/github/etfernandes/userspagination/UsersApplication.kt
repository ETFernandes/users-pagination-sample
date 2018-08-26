package com.github.etfernandes.userspagination

import android.app.Application
import androidx.paging.DataSource
import com.github.etfernandes.userspagination.datasource.UsersDataSourceFactory
import com.github.etfernandes.userspagination.model.User
import com.github.etfernandes.userspagination.service.RetrofitUsersService
import com.github.etfernandes.userspagination.service.UsersService
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class UsersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val module = module {
            single { RetrofitUsersService() as UsersService }
            single { UsersDataSourceFactory(get()) as DataSource.Factory<Int, User> }

            viewModel { UsersViewModel(get()) }
        }

        startKoin(this, listOf(module))
    }
}
