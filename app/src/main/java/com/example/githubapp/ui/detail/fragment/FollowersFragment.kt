package com.example.githubapp.ui.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.adapter.UserAdapter
import com.example.githubapp.databinding.FragmentFollowBinding
import com.example.githubapp.ui.detail.DetailUserActivity
import com.example.githubapp.ui.detail.DetailUserViewModel

class FollowersFragment: Fragment() {
     private var binding: FragmentFollowBinding? = null

    lateinit var viewModel: FollowersViewModel
    lateinit var adapter: UserAdapter
    lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()

        binding = FragmentFollowBinding.inflate(inflater, container, false)


        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        binding?.apply {
            recViewFollowers.setHasFixedSize(true)
            recViewFollowers.layoutManager = LinearLayoutManager(context)
            recViewFollowers.adapter = adapter
        }

        showLoading(true)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
        return binding?.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            binding?.progressBarFollowers?.visibility = View.VISIBLE
        } else {
            binding?.progressBarFollowers?.visibility = View.GONE
        }
    }

}