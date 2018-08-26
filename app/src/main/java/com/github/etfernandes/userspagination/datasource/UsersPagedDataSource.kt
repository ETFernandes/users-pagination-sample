package com.github.etfernandes.userspagination.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.github.etfernandes.userspagination.model.User
import com.github.etfernandes.userspagination.service.UsersService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersPagedDataSource(private val usersService: UsersService) : PageKeyedDataSource<Int, User>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        usersService.getUsers(1, params.requestedLoadSize).doOnSuccess {
            callback.onResult(it.results, null, it.info.page + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        usersService.getUsers(params.key, params.requestedLoadSize).doOnSuccess {
            callback.onResult(it.results, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        usersService.getUsers(params.key, params.requestedLoadSize).doOnSuccess {
            callback.onResult(it.results, params.key - 1)
        }
    }

    private fun <T> Call<T>.doOnSuccess(onSuccess: (T) -> Unit) {
        enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, throwable: Throwable?) {
                Log.e("UsersPagedDataSource", "Failed to load users", throwable)
            }

            override fun onResponse(call: Call<T>?, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                }
            }
        })
    }
}
