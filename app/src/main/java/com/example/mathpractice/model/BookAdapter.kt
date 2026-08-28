package com.example.mathpractice.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mathpractice.R

class BookAdapter(var bookList : ArrayList<PracticeSetModel>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private lateinit var mListener : OnItemClickedListener

    interface OnItemClickedListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickedListener(listener : OnItemClickedListener) {
        mListener = listener
    }

    inner class BookViewHolder(view: View, listener: OnItemClickedListener): RecyclerView.ViewHolder(view) {
        val bookTitle : TextView = view.findViewById(R.id.bookTitleTV)
        val bookImage : ImageView = view.findViewById(R.id.bookImageView)

        init {

            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookitemlayout, parent, false)
        return BookViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val bookItem = bookList[position]

        holder.bookTitle.text = bookItem.bookName
        holder.bookImage.setImageResource(bookItem.bookPicture)

    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}