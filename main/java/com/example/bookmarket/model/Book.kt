package com.example.bookmarket.model

import java.io.Serializable

// 데이터 모델은 model 패키지에 위치시킵니다.
data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val price: String,
    val publisherDate: String,
    val imageResId: Int
) : Serializable