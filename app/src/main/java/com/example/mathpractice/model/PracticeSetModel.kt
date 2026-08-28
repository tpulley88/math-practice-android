package com.example.mathpractice.model

data class PracticeSetModel(
    val bookID : Int,
    val bookPicture : Int,
    val bookName : String,
    val solutionSource : String,
    val numLessons : Int
)
