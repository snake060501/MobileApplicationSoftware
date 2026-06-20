package com.example.bookmarket.model

// 장바구니에 담긴 아이템을 표현하는 data class
data class CartItem(
    val book: Book,
    var quantity: Int
)

// 앱 전체에서 공유되는 장바구니 저장소 (메모리 기반 데이터 관리)
object CartManager {
    private val cartList = mutableListOf<CartItem>()

    // 장바구니에 도서 추가
    fun addBook(book: Book) {
        val existingItem = cartList.find { it.book.id == book.id }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            cartList.add(CartItem(book, 1))
        }
    }

    // 장바구니 목록 반환
    fun getCartItems(): List<CartItem> = cartList

    // 장바구니 비우기
    fun clearCart() {
        cartList.clear()
    }

    // 총 금액 계산
    fun getTotalPrice(): Int {
        return cartList.sumOf {
            // "15,000원" 등의 문자열에서 숫자만 추출하여 계산
            val priceInt = it.book.price.replace("[^0-9]".toRegex(), "").toIntOrNull() ?: 0
            priceInt * it.quantity
        }
    }
}