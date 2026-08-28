package com.example.mathpractice.model

import com.example.mathpractice.QuestionType
import org.json.JSONArray
import org.json.JSONObject

data class AnswerModel(
    val lesson: String,
    val type: String,
    val number: String,
    val answer: ArrayList<Any>,
    val secondary: String?,
    val hint: String?,
    val learn: String?,
    var answered : Boolean = false,
    var attemptList : ArrayList<Any>? = arrayListOf()
)
