package com.example.bookmarket.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmarket.R
import com.example.bookmarket.databinding.ActivityBookDetailBinding
import com.example.bookmarket.model.Book

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

        binding.btnBack.setOnClickListener {
            finish() // 뒤로가기 버튼 기능
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
