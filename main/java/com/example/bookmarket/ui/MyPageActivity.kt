package com.example.bookmarket.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmarket.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. 툴바 설정 및 뒤로가기(왼쪽 화살표) 버튼 활성화
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "마이페이지"
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 👈 뒤로가기 버튼 표시 활성화

        // SharedPreferences 저장소 초기화
        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        // 자동 로그인 상태 체크 (이전 로그인한 기록이 남아있는지 확인)
        val savedUser = sharedPref.getString("USERNAME", null)
        if (savedUser != null) {
            showUserInfo(savedUser)
        } else {
            showLoginLayout()
        }

        // 2. 로그인 버튼 클릭 이벤트 (비밀번호 예외 처리 포함)
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            // 예외 처리 구조 설계
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

            // 모든 조건 만족 시 로컬 SharedPreferences에 이름 저장 및 화면 전환
            sharedPref.edit().putString("USERNAME", username).apply()
            showUserInfo(username)
            Toast.makeText(this, "${username}님 로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
        }

        // 3. 로그아웃 버튼 클릭 이벤트
        binding.btnLogout.setOnClickListener {
            sharedPref.edit().remove("USERNAME").apply()
            showLoginLayout()
            Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // 로그인 완료 레이아웃 표시 함수
    private fun showUserInfo(username: String) {
        binding.layoutLogin.visibility = View.GONE
        binding.layoutUserInfo.visibility = View.VISIBLE
        binding.txtWelcomeUser.text = "${username}님, 환영합니다!"
    }

    // 비로그인(로그인 폼 입력창) 레이아웃 표시 함수
    private fun showLoginLayout() {
        binding.layoutLogin.visibility = View.VISIBLE
        binding.layoutUserInfo.visibility = View.GONE
        binding.edtUsername.text.clear()
        binding.edtPassword.text.clear() // 비밀번호 창도 비워주기
    }

    // 👈 상단 툴바의 뒤로가기(왼쪽 화살표) 버튼을 클릭했을 때 동작 정의
    override fun onSupportNavigateUp(): Boolean {
        finish() // 현재 마이페이지 화면을 종료하고 이전 홈 화면(MainActivity)으로 전환
        return true
    }
}