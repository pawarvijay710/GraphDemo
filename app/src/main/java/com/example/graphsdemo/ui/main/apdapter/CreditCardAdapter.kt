package com.example.graphsdemo.ui.main.apdapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graphsdemo.data.model.credit.CreditCard
import com.example.graphsdemo.data.model.product.Product
import com.example.graphsdemo.databinding.ItemLayoutBinding


class CreditCardAdapter: RecyclerView.Adapter<CreditCardAdapter.UserViewHolder>() {

    var data: ArrayList<CreditCard> = ArrayList()
    private var itemLayoutBinding: ItemLayoutBinding? = null

    class UserViewHolder(private val itemLayoutBindingView: ItemLayoutBinding?) :
        RecyclerView.ViewHolder(itemLayoutBindingView?.root!!){
        fun bind(creditCard: CreditCard){
            itemLayoutBindingView?.textViewUserName?.text = creditCard.owner
            itemLayoutBindingView?.textViewUserEmail?.text = creditCard.number
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

    fun addData(data: ArrayList<CreditCard>) {
        this.data = data
    }
}