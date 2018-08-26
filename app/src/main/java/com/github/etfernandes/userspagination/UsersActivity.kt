package com.github.etfernandes.userspagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_users.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersActivity : AppCompatActivity() {

    private val viewModel: UsersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val adapter = UsersAdapter()
        usersRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        usersRecyclerView.adapter = adapter

        viewModel.users.observe(this, Observer(adapter::submitList))
    }
}
