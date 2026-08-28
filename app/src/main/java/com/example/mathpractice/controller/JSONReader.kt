package com.example.mathpractice.controller

import com.example.mathpractice.model.AnswerModel
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object JSONReader {

    fun readJSON(bookT: String, jFile: InputStream): ArrayList<AnswerModel> {

        // Instance of list using the data model class.
        val answerList: ArrayList<AnswerModel> = ArrayList()

        try {
            // We are getting the JSON object
            //Here we are calling a Method which is returning the JSON object
            val obj = JSONObject(getJSONFromAssets(bookT, jFile)!!)
            // fetch JSONArray named answers by using getJSONArray
            val answersArray = obj.getJSONArray(bookT)
            // Get the users data using for loop i.e. id, name, email and so on

            for (i in 0 until answersArray.length()) {

                val answerObject = answersArray.getJSONObject(i)

                // Create a JSONObject for fetching single answer data
                val lesson = answerObject.getString("lesson")
                val type = answerObject.getString("type")
                val number = answerObject.getString("number")

                var arr : ArrayList<Any> = arrayListOf()
                val answer = answerObject.getJSONArray("answer")
                for (i in 0 until answer.length()) {
                    val ans: String = answer.getString(i)
                    arr.add(ans)
                }

                val secondary = answerObject.getString("secondary")
                val hint = answerObject.getString("hint")
                val learn = answerObject.getString("learn")

                // Now add all the variables to the data model class and the data model class to the array list.
                val answerDetails = AnswerModel(lesson, type, number, arr, secondary, hint, learn)

                // add the details in the list
                answerList.add(answerDetails)
            }
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

        return answerList


    }


    /**
     * Method to load the JSON from the Assets file and return the object
     */
    private fun getJSONFromAssets(bookT : String, jFile: InputStream): String? {

        val json: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val size = jFile.available()
            val buffer = ByteArray(size)
            jFile.read(buffer)
            jFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}