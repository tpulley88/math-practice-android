package com.example.mathpractice.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mathpractice.R

class LessonAdapter(var lessonList : ArrayList<String>) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private lateinit var mListener : OnItemClickedListener

    interface OnItemClickedListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickedListener(listener : OnItemClickedListener) {
        mListener = listener
    }

    inner class LessonViewHolder(view: View, listener: OnItemClickedListener): RecyclerView.ViewHolder(view) {
        val lessonText : TextView = view.findViewById(R.id.lessonText)

        init {

            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.lessonitemlayout, parent, false)
        return LessonViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessonList[position]

        holder.lessonText.text = lesson

    }

    override fun getItemCount(): Int {
        return lessonList.size
    }
}