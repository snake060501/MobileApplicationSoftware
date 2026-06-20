package com.example.bookmarket.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.bookmarket.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Edge-to-Edge 설정
        enableEdgeToEdge()
        
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. 툴바 상단 여백 확보 (상태바와 겹침 방지)
        ViewCompat.setOnApplyWindowInsetsListener(binding.toolbar) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updatePadding(top = systemBars.top)
            insets
        }

        // 툴바 설정 및 뒤로가기 버튼 활성화
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "마이페이지"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        val savedUser = sharedPref.getString("USERNAME", null)
        if (savedUser != null) {
            showUserInfo(savedUser)
        } else {
            showLoginLayout()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (username.isEmpty()) {
                Toast.makeText(this, "이름을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 4) {
                Toast.makeText(this, "비밀번호는 최소 4자리 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sharedPref.edit().putString("USERNAME", username).apply()
            showUserInfo(username)
            Toast.makeText(this, "${username}님 로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.btnLogout.setOnClickListener {
            sharedPref.edit().remove("USERNAME").apply()
            showLoginLayout()
            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showUserInfo(username: String) {
        binding.layoutLogin.visibility = View.GONE
        binding.layoutUserInfo.visibility = View.VISIBLE
        binding.txtWelcomeUser.text = "${username}님, 환영합니다!"
    }

    private fun showLoginLayout() {
        binding.layoutLogin.visibility = View.VISIBLE
        binding.layoutUserInfo.visibility = View.GONE
        binding.edtUsername.text.clear()
        binding.edtPassword.text.clear()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}