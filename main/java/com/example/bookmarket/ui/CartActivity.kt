package com.example.bookmarket.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmarket.adapter.CartAdapter
import com.example.bookmarket.databinding.ActivityCartBinding
import com.example.bookmarket.model.CartManager

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Edge-to-Edge 설정
        enableEdgeToEdge()
        
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. 툴바 상단 여백 확보 (상태바와 겹침 방지)
        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top)
            insets
        }

        // 툴바 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "장바구니"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 장바구니 리스트 설정
        val cartItems = CartManager.getCartItems()
        cartAdapter = CartAdapter(cartItems)
        binding.recyclerViewCart.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }

        updateTotalPrice()

        binding.btnOrder.setOnClickListener {
            if (CartManager.getCartItems().isEmpty()) {
                Toast.makeText(this, "장바구니가 비어 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "주문이 완료되었습니다!", Toast.LENGTH_LONG).show()
                CartManager.clearCart()
                cartAdapter.notifyDataSetChanged()
                updateTotalPrice()
            }
        }
    }

    private fun updateTotalPrice() {
        binding.txtTotalPrice.text = "${CartManager.getTotalPrice()}원"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}