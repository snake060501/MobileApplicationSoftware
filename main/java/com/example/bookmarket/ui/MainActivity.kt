package com.example.bookmarket.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmarket.R
import com.example.bookmarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState: Bundle?)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 툴바 설정 (앱바)
        setSupportActionBar(binding.toolbar)

        // 도서 목록 보러가기 버튼 이벤트 (예시)
        binding.btnGoToMarket.setOnClickListener {
            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
        }
    }

    // 툴바에 옵션 메뉴 아이콘 배치 (최소 2개 항목)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    // 옵션 메뉴 클릭 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_home -> {
                Toast.makeText(this, getString(R.string.menu_home), Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_mypage -> {
                // 가상의 마이페이지 토스트 처리 또는 이벤트를 여기에 작성
                Toast.makeText(this, getString(R.string.menu_mypage), Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}