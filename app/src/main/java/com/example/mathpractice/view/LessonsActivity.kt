package com.example.mathpractice.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mathpractice.*
import com.example.mathpractice.controller.PracticeSetController
import com.example.mathpractice.controller.Utility
import com.example.mathpractice.model.LessonAdapter
import com.example.mathpractice.model.PracticeSetModel

class LessonsActivity : AppCompatActivity() {

    private lateinit var lessonList: ArrayList<String>
    private var recyclerView: RecyclerView? = null
    private var bookID: Int = 0
    private lateinit var bookChosen : PracticeSetModel
    private var userName : String? = null
    private var numLessons : Int = 0
    private lateinit var welcomeText : TextView
    private lateinit var lessonAdapter: LessonAdapter


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)

        welcomeText = findViewById(R.id.welcomeText)
        recyclerView = findViewById(R.id.lessonRecyclerView)

        userName = intent.getStringExtra(Utility.USER_NAME)
        welcomeText.text = "Welcome $userName"

        bookID = intent.getIntExtra(Utility.CHOSEN_BOOK_ID, 0)
        bookChosen = PracticeSetController.getPracticeSet(bookID)!!

        numLessons = bookChosen.numLessons

        lessonList = createRVNumList(numLessons)

        setAdapter()


    }

    private fun setAdapter() {

        lessonAdapter = LessonAdapter(lessonList)

        val layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = lessonAdapter
        lessonAdapter.setOnItemClickedListener(object : LessonAdapter.OnItemClickedListener {
            override fun onItemClick(position: Int) {

                val intent = Intent(this@LessonsActivity, ProblemInputActivity::class.java)
                intent.putExtra(Utility.CHOSEN_BOOK_ID, bookChosen?.bookID)
                intent.putExtra(Utility.CHOSEN_LESSON, position)
                startActivity(intent)

            }
        })

    }

    private fun createRVNumList(numLessons: Int) : ArrayList<String>{

        val tempList = arrayListOf<String>()

        for (i in 0 until numLessons) {

            tempList.add("Lesson $i")
        }

        return tempList
    }

}