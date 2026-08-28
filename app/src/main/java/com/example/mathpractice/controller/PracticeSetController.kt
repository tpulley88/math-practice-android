package com.example.mathpractice.controller

import com.example.mathpractice.R
import com.example.mathpractice.model.PracticeSetModel

object PracticeSetController {

    fun getPracticeSetList(): ArrayList<PracticeSetModel> {
        return arrayListOf(
            PracticeSetModel(
                0,
                R.drawable.ic_launcher_background,
                "Foundations Practice",
                "foundations_practice.json",
                3
            )
        )
    }

    fun getPracticeSource(position: Int): String {

        val tempList = getPracticeSetList()

        return tempList[position].solutionSource
    }

    fun getNumberLessons(position: Int) : Int {
        val tempList = getPracticeSetList()

        return tempList[position].numLessons
    }

    fun getPracticeSet(id: Int): PracticeSetModel? {

        val tempList = getPracticeSetList()

        return tempList[id]
    }

}
