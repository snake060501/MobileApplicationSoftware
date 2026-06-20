package com.example.bookmarket.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmarket.R
import com.example.bookmarket.adapter.BookAdapter
import com.example.bookmarket.databinding.ActivityBookListBinding
import com.example.bookmarket.model.Book

class BookListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookListBinding
    private lateinit var bookAdapter: BookAdapter
    private lateinit var originalList: List<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // 1. Edge-to-Edge 활성화 및 시스템 바 여백 해결
        enableEdgeToEdge()
        
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. 최상위 뷰(Root)에 시스템 바 영역만큼 패딩 적용 (터치 씹힘 방지)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "도서 목록"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        originalList = listOf(
            Book(1, "혼자 공부하는 바이브 코딩 with 클로드 코드", "조태호", "27,000원", "2025-12-16", R.drawable.books_book1),
            Book(2, "이게 되네? 제미나이 완전 미친 활용법 81제", "오힘찬", "21,600원", "2026-02-10", R.drawable.books_book2),
            Book(3, "클로드 코드로 시작하는 실전 에이전틱 코딩", "Goos Kim", "29,700원", "2026-05-21", R.drawable.books_book3),
            Book(4, "바로바로 챗GPT X 덕테이프 X 코덱스", "박현규", "21,600원", "2026-06-09", R.drawable.books_book4)
        )

        bookAdapter = BookAdapter(originalList)
        binding.recyclerViewBooks.apply {
            layoutManager = LinearLayoutManager(this@BookListActivity)
            adapter = bookAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.book_list_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView
        searchView?.queryHint = "도서 제목 또는 저자 검색"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
    private fun filter(text: String?) {
        val query = text.orEmpty().trim().lowercase()
        if (query.isEmpty()) {
            bookAdapter.filterList(originalList)
        } else {
            val filteredList = originalList.filter {
                it.title.lowercase().contains(query) || it.author.lowercase().contains(query)
            }
            bookAdapter.filterList(filteredList)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
