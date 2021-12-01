package com.example.graphsdemo.ui.main.apdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graphsdemo.data.model.product.Product
import com.example.graphsdemo.databinding.ItemLayoutBinding


class ApiAdapter : RecyclerView.Adapter<ApiAdapter.UserViewHolder>() {

    var data: ArrayList<Product> = ArrayList()
    private var itemLayoutBinding: ItemLayoutBinding? = null

    class UserViewHolder(private val itemLayoutBindingView: ItemLayoutBinding?) :
        RecyclerView.ViewHolder(itemLayoutBindingView?.root!!){
            fun bind(product: Product){
                itemLayoutBindingView?.textViewUserName?.text = product.name
                itemLayoutBindingView?.textViewUserEmail?.text = product.description
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        itemLayoutBinding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun addData(data: ArrayList<Product>) {
        this.data = data
    }
}