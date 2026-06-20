package com.example.bookmarket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmarket.databinding.ItemCartBinding
import com.example.bookmarket.model.CartItem

class CartAdapter(private val cartList: List<CartItem>) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            binding.txtCartTitle.text = cartItem.book.title
            binding.txtCartPrice.text = cartItem.book.price
            binding.txtCartQuantity.text = "${cartItem.quantity}개"
            binding.imgCartCover.setImageResource(cartItem.book.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cartList[position])
    }

    override fun getItemCount(): Int = cartList.size
}