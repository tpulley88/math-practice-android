package com.example.mathpractice

import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader
import java.util.*

object SolutionLists {

    fun buildSolutionList(parsedLine: String) {

        var newString : String = parsedLine

        val bookID: Int = Integer.parseInt(newString.substring(0, 2))

        newString = parsedLine.drop(2)

        val lessonID : Int
        val questionType: Enum<QuestionType>
        when {
            newString.contains("W") -> {
                lessonID = Integer.parseInt(newString.substringBefore("W"))

                questionType = QuestionType.WARMUP

                newString = newString.replaceBefore("W", "")
                newString = newString.replace("W", "")

            }
            newString.contains("M") -> {
                lessonID = Integer.parseInt(newString.substringBefore("M"))

                questionType = QuestionType.MIXED

                newString = newString.replaceBefore("M", "")
                newString = newString.replace("M", "")

            }
            else -> {
                lessonID = Integer.parseInt(newString.substringBefore("P"))

                questionType = QuestionType.PRACTICE

                newString = newString.replaceBefore("P", "")
                newString = newString.replace("P", "")

            }
        }



        val questionID: String = newString.substringBefore("`")

        newString = newString.replaceBefore("`", "")
        newString = newString.replace("`", "")

        val answer: String = newString.substringBefore("|")

        newString = newString.replaceBefore("|", "")
        newString = newString.replace("|", "")


        val secondAnswer: String? = newString.substringBefore("~")

        if (secondAnswer != null) {
            newString = newString.drop(secondAnswer.length)
        }

        var hint: String? = newString

        hint = if (hint != null) {
            newString.drop(hint.length)
        } else {
            newString.removeSuffix("Hint:")
        }



        println("BookID : $bookID " +
               " LessonID: $lessonID" +
                " Question Type: ${questionType.name}" +
                " QuestionID: $questionID" +
               " Answer: $answer" +
                " Second Answer: $secondAnswer" +
                " Hint: $hint")

    }

}