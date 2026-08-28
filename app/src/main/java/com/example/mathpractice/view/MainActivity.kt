package com.example.mathpractice.view

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mathpractice.*
import com.example.mathpractice.controller.PracticeSetController
import com.example.mathpractice.controller.Utility
import com.example.mathpractice.model.BookAdapter
import com.example.mathpractice.model.PracticeSetModel

class MainActivity : AppCompatActivity() {

    private lateinit var bookAdapter: BookAdapter
    private var nameEditText: EditText? = null
    private var recyclerView: RecyclerView? = null
    private var solutionSource: String? = null
    private var lessons : Int = 0
    private var bookChosen : PracticeSetModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.nameEditText)
        recyclerView = findViewById(R.id.bookRecyclerView)

        setAdapter()
    }

    private fun setAdapter() {
        bookAdapter = BookAdapter(PracticeSetController.getPracticeSetList())
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = bookAdapter
        bookAdapter.setOnItemClickedListener(object : BookAdapter.OnItemClickedListener {
            override fun onItemClick(position: Int) {

                if (nameEditText?.text?.isNotEmpty() == true) {

                    //Retrieves appropriate JSON file name
                    solutionSource = PracticeSetController.getPracticeSource(position)
                    lessons = PracticeSetController.getNumberLessons(position)
                    bookChosen = PracticeSetController.getPracticeSet(position)

                    launchActivity(solutionSource!!)

                } else {
                    Toast.makeText(this@MainActivity, "Please enter your name", Toast.LENGTH_LONG)
                        .show()

                }
            }
        })
    }

    private fun launchActivity(bookSolutions: String) {

        val intent = Intent(this@MainActivity, LessonsActivity::class.java)
        intent.putExtra(Utility.USER_NAME, nameEditText!!.text.toString())
        intent.putExtra(Utility.CHOSEN_BOOK_ID, bookChosen?.bookID)
        startActivity(intent)

    }

}
