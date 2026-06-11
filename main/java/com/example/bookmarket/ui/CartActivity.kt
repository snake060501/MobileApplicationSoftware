package com.example.bookmarket.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmarket.adapter.CartAdapter
import com.example.bookmarket.databinding.ActivityCartBinding
import com.example.bookmarket.model.CartManager

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. 툴바 설정 및 뒤로가기 화살표 버튼 활성화
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "장바구니"
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 👈 뒤로가기 버튼 표시 활성화

        // 2. 장바구니 리스트 데이터 가져오기
        val cartItems = CartManager.getCartItems()

        // 3. 어댑터 연결하여 책 목록 화면에 띄우기
        cartAdapter = CartAdapter(cartItems)
        binding.recyclerViewCart.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            adapter = cartAdapter
        }

        // 4. 총 결제 금액 표시 업데이트
        updateTotalPrice()

        // 5. 주문하기 버튼 클릭 이벤트
        binding.btnOrder.setOnClickListener {
            if (CartManager.getCartItems().isEmpty()) {
                Toast.makeText(this, "장바구니가 비어 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "주문이 완료되었습니다!", Toast.LENGTH_LONG).show()
                CartManager.clearCart() // 장바구니 비우기

                // 화면 갱신
                cartAdapter.notifyDataSetChanged()
                updateTotalPrice()
            }
        }
    }

    // 총 금액을 텍스트뷰에 반영하는 함수
    private fun updateTotalPrice() {
        binding.txtTotalPrice.text = "${CartManager.getTotalPrice()}원"
    }

    // 👈 상단 툴바의 뒤로가기(왼쪽 화살표) 버튼을 눌렀을 때 작동하는 함수
    override fun onSupportNavigateUp(): Boolean {
        finish() // 현재 장바구니 화면을 종료하고 이전 메인 화면으로 돌아감
        return true
    }
}