package com.example.bookmarket.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.bookmarket.R
import com.example.bookmarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. 툴바 설정 (앱바 구현)
        setSupportActionBar(binding.toolbar)

        // 2. 내비게이션 드로어 토글 설정 (툴바와 드로어Layout 연동)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.menu_home,
            R.string.menu_mypage
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // 3. 내비게이션 드로어 메뉴 아이템 클릭 연동 (+3점 가산점 항목)
        binding.navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "이미 홈 화면입니다.", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_cart -> {
                    // 장바구니 화면(CartActivity)으로 이동
                    val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_mypage -> {
                    // 마이페이지 화면(MyPageActivity)으로 이동
                    val intent = Intent(this, MyPageActivity::class.java)
                    startActivity(intent)
                }
            }
            // 메뉴 클릭 후 드로어 자동으로 닫기
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // 4. 도서 목록 화면 전환 버튼 이벤트
        binding.btnGoToMarket.setOnClickListener {
            val intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
        }
    }

    // 5. 상단 우측 옵션 메뉴 생성 기틀 (안내서 필수 조건)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    // 6. 우측 툴바 옵션 메뉴 클릭 시 화면 이동 연동
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 드로어 햄버거 토글 버튼이 클릭된 경우 우선 처리
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return when (item.itemId) {
            R.id.menu_home -> {
                Toast.makeText(this, "이미 홈 화면입니다.", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_mypage -> {
                // 우측 메뉴 클릭 시에도 마이페이지 화면으로 이동
                val intent = Intent(this, MyPageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // 스마트폰 하단 '뒤로가기' 버튼 클릭 시 드로어가 열려있으면 먼저 닫히도록 처리
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
