package com.example.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.ViewModel.MainViewModel
import com.example.githubapp.adapter.UserAdapter
import com.example.githubapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get((MainViewModel::class.java))

        binding.apply {
            recViewUser.layoutManager = LinearLayoutManager(this@MainActivity)
            recViewUser.setHasFixedSize(true)
            recViewUser.adapter = adapter

            ivSearch.setOnClickListener {
                searchUser()
            }

            edtSearch.setOnKeyListener { view, keyCode, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }

        }

        viewModel.getSearchUsers().observe(this) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }

    private fun searchUser() {
        binding.apply {
            val query = edtSearch.text.toString()

            if (query!!.isEmpty()) return

            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }
    private fun showLoading(state: Boolean) {
        if(state) {
            binding.progressBarMain.visibility = View.VISIBLE
        } else {
            binding.progressBarMain.visibility = View.GONE
        }
    }
}