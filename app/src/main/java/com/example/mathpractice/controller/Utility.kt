package com.example.mathpractice.controller

import com.example.mathpractice.model.AnswerModel

object Utility {


    const val USER_NAME: String = "user"

    const val CHOSEN_BOOK_ID : String = "book id"

    const val CHOSEN_LESSON : String = "lesson"


    fun getLessonProblems(dataList: ArrayList<AnswerModel>, lesson: Int): ArrayList<AnswerModel> {

        val problemList : ArrayList<AnswerModel> = ArrayList()

        for (i in dataList) {
            if (i.lesson == lesson.toString()) {
                problemList.add(i)
            }
        }

        return problemList
    }

}