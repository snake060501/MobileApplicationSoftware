package com.example.bookmarket.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmarket.ui.BookDetailActivity
import com.example.bookmarket.databinding.ItemBookBinding
import com.example.bookmarket.model.Book

class BookAdapter(private var bookList: List<Book>) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    inner class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.txtTitle.text = book.title
            binding.txtAuthor.text = book.author
            binding.txtPrice.text = book.price
            binding.imgCover.setImageResource(book.imageResId)

            // 아이템 클릭 시 상세 화면으로 이동 및 데이터 전달
            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, BookDetailActivity::class.java).apply {
                    putExtra("SELECTED_BOOK", book)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int = bookList.size

    // 👈 [검색용 핵심 함수] 외부에서 필터링된 리스트를 받아와 화면을 갱신합니다.
    fun filterList(filteredList: List<Book>) {
        this.bookList = filteredList
        notifyDataSetChanged() // 리사이클러뷰 리스트 새로고침
    }
}