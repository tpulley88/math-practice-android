package com.example.mathpractice.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mathpractice.R
import com.example.mathpractice.controller.JSONReader
import com.example.mathpractice.controller.PracticeSetController
import com.example.mathpractice.controller.Utility
import com.example.mathpractice.model.AnswerModel
import com.example.mathpractice.model.ProblemAdapter
import com.example.mathpractice.model.PracticeSetModel


class ProblemInputActivity : AppCompatActivity() {

    private lateinit var dataList: ArrayList<AnswerModel>
    private lateinit var lessonList: ArrayList<AnswerModel>
    private var bookID : Int = 0
    private var lesson : Int = 0
    private var attemptNum : Int = 1
    private lateinit var bookChosen : PracticeSetModel
    private var recyclerView: RecyclerView? = null
    private lateinit var problemAdapter: ProblemAdapter
    private lateinit var lessonHeader : TextView
    private lateinit var numPassedTV : TextView
    private lateinit var gradeTV : TextView
    private var numPassed : Int = 0
    private var currentGrade : Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_probleminput)

        recyclerView = findViewById(R.id.problemRV)

        bookID = intent.getIntExtra(Utility.CHOSEN_BOOK_ID, 0)
        bookChosen = PracticeSetController.getPracticeSet(bookID)!!

        lesson = intent.getIntExtra(Utility.CHOSEN_LESSON, 0)

        dataList = JSONReader.readJSON(bookChosen.solutionSource, assets.open(bookChosen.solutionSource))

        lessonList = Utility.getLessonProblems(dataList, lesson)

        lessonHeader = findViewById(R.id.lessonTV)

        lessonHeader.text = "Lesson $lesson"

        numPassedTV = findViewById(R.id.numPassedTV)
        numPassedTV.text = numPassed.toString()

        gradeTV = findViewById(R.id.gradeTV)
        gradeTV.text = currentGrade.toString()


        setAdapter()

    }



    private fun setAdapter() {
        problemAdapter = ProblemAdapter(lessonList)


        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = problemAdapter
        problemAdapter.setOnItemClickedListener(object : ProblemAdapter.OnItemClickedListener {
            override fun onItemClick(position: Int) {

               checkAnswer(position)
            }

            })
    }

    private fun checkAnswer(position: Int) {
        val tempList : ArrayList<Any> = arrayListOf()

        val answer1 =
            (recyclerView!!.findViewHolderForAdapterPosition(position)!!.itemView.findViewById<View>(
                R.id.answerET
            ) as TextView).text.toString()

        tempList.add(answer1)

        val correctAns = lessonList[position].answer

        if (lessonList[position].answer.size > 1) {
            var ansNum = 1
            val numAnswers = lessonList[position].answer.size

            while (ansNum < numAnswers) {
                tempList.add(
                    (recyclerView!!.findViewHolderForAdapterPosition(position)!!.itemView.findViewWithTag<View>(
                        "answerET$position$ansNum"
                    ) as TextView).text.toString()
                )
                ansNum += 1
            }
        }



        if (tempList == correctAns) {

            if (lessonList[position].secondary?.isNotEmpty() == true) {

                    tempList.add("\nCHECK SECONDARY ANSWER: \n" + (recyclerView!!.findViewHolderForAdapterPosition(position)!!.itemView.findViewWithTag<View>(
                    "secondaryET$position"
                ) as TextView).text.toString())

            }

            lessonList[position].answered = true

            lessonList[position].attemptList?.add("\nAttempt #$attemptNum $tempList")

            numPassed += 1

            numPassedTV.text = "$numPassed/${lessonList.size}"

            setAdapter()
            setGrade()

        } else {

            lessonList[position].attemptList?.add("\nAttempt #$attemptNum $tempList")

            attemptNum += 1

            setAdapter()

        }
    }

    private fun setGrade() {

        currentGrade = (100.0 / lessonList.size) * numPassed
        gradeTV.text = String.format("%.2f", currentGrade) + "%"



    }

}