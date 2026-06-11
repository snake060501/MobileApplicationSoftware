package com.example.bookmarket.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmarket.R
import com.example.bookmarket.databinding.ActivityBookDetailBinding
import com.example.bookmarket.model.Book
import com.example.bookmarket.model.CartManager

class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Intent로부터 Book 객체 받아오기 (Null-Safety 적용)
        val book = intent.getSerializableExtra("SELECTED_BOOK") as? Book

        book?.let {
            // ViewBinding을 사용해 데이터 반영
            binding.imgBookCover.setImageResource(it.imageResId)
            binding.txtBookTitle.text = it.title
            binding.txtBookAuthor.text = "${getString(R.string.label_author)}${it.author}"
            binding.txtBookPrice.text = "${getString(R.string.label_price)}${it.price}"
            binding.txtBookPublisher.text = "${getString(R.string.label_publisher)}${it.publisherDate}"
        }

        binding.btnAddToCart.setOnClickListener {
            book?.let { selectedBook ->
                CartManager.addBook(selectedBook) // 싱글톤 매니저에 저장

                // 가산점 요소: 스낵바 또는 알림창 활용 조항 만족을 위해 AlertDialog 처리
                AlertDialog.Builder(this)
                    .setTitle("장바구니 알림")
                    .setMessage("${selectedBook.title} 제품이 장바구니에 추가되었습니다. 장바구니로 이동하시겠습니까?")
                    .setCancelable(true)
                    .setPositiveButton("이동") { _, _ ->
                        val intent = Intent(this, CartActivity::class.java)
                        startActivity(intent)
                    }
                    .setNegativeButton("쇼핑 계속하기", null)
                    .show()
            }
        }

        binding.btnBack.setOnClickListener {
            finish() // 뒤로가기 버튼 기능
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
