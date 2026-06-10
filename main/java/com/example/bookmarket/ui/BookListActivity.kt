package com.example.bookmarket.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmarket.R
import com.example.bookmarket.adapter.BookAdapter
import com.example.bookmarket.databinding.ActivityBookListBinding
import com.example.bookmarket.model.Book

class BookListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookListBinding
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 설정 및 뒤로가기 버튼 활성화
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 최소 4개의 나만의 도서 더미 데이터 생성
        val dummyBooks = listOf(
            Book(1, "디자인 패턴의 이해", "홍길동", "28,000원", "2026-01-10", R.drawable.book_cover1),
            Book(2, "Kotlin 실전 가이드", "이순신", "32,000원", "2026-03-15", R.drawable.book_cover2),
            Book(3, "안드로이드 아키텍처", "강감찬", "35,000원", "2025-11-20", R.drawable.book_cover3),
            Book(4, "모바일 UI/UX 입문", "유관순", "24,000원", "2026-05-01", R.drawable.book_cover4)
        )

        // 어댑터 초기화 및 클릭 이벤트 구현 (상세 화면으로 데이터 전달)
        bookAdapter = BookAdapter(dummyBooks) { selectedBook ->
            val intent = Intent(this, BookDetailActivity::class.java).apply {
                putExtra("SELECTED_BOOK", selectedBook) // Intent로 데이터 전달
            }
            startActivity(intent)
        }

        // 레이아웃 매니저 및 어댑터 연결
        binding.recyclerViewBooks.apply {
            layoutManager = LinearLayoutManager(this@BookListActivity)
            adapter = bookAdapter
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish() // 툴바 뒤로가기 클릭 시 화면 종료
        return true
    }
}
