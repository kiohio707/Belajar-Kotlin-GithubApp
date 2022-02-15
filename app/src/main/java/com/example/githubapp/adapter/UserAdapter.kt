package com.example.githubapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.githubapp.databinding.AdapterUserBinding
import com.example.githubapp.model.UserModel

class UserAdapter: RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val list = ArrayList<UserModel>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(userModels: ArrayList<UserModel>) {
        list.clear()
        list.addAll(userModels)
        notifyDataSetChanged()
    }

    inner class ViewHolder (val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemCLicked(userModel)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(userModel.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(ivUserAdapter)
                tvUser.text = userModel.login
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AdapterUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickCallback {
        fun onItemCLicked(data: UserModel)
    }
}