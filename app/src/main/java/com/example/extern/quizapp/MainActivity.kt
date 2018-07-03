package com.example.extern.quizapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IMainActivity {


    // declare variables
    private var questionImage: ImageView? = null
    private var questionText: TextView? = null
    private var responseTextView: TextView? = null
    private var nextButton: ImageButton? = null
    private var previousButton: ImageButton? = null
    private var homeButton: Button? = null
    var currentIndex: Int = 0
    var recyclerView: RecyclerView? = null
    var answerButton: Button? = null

    val questions = arrayOf("What is the total length of the McKenzie?", "Which river is the McKenzie a tributary of?", "How many dams are on the McKenzie?")
    val answerOptionsOne = arrayOf("90", "50", "140")
    val answerOptionsTwo = arrayOf("Columbia River", "Rogue River", "Willamette River")
    val answerOptionsThree = arrayOf("3", "6", "8")
    val answerOptions = arrayOf(answerOptionsOne, answerOptionsTwo, answerOptionsThree)
    val answers = arrayOf("90", "Willamette River", "6")

    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = MainAdapter(answerOptionsOne, this)

        optionsList.layoutManager = LinearLayoutManager(this)
        optionsList.adapter = adapter


        nextButton = findViewById(R.id.nextButton) as ImageButton
        previousButton = findViewById(R.id.previousButton) as ImageButton
        questionText = findViewById(R.id.questionText) as TextView
        homeButton = findViewById(R.id.homeButton) as Button
        recyclerView = findViewById(R.id.optionsList) as RecyclerView
        answerButton = findViewById(R.id.answerButton) as Button
        responseTextView = findViewById(R.id.responseText)



        answerButton?.setOnClickListener {
            recyclerView?.visibility = View.VISIBLE
        }



        questionText?.text=questions[currentIndex]

        nextButton?.setOnClickListener {
            if (currentIndex == questions.size - 1) {
                currentIndex = 0

            } else {
                currentIndex++
            }

            updateQuestion()
            adapter.update(
               when (currentIndex) {
                   0 -> answerOptionsOne
                   1 -> answerOptionsTwo
                   else -> {answerOptionsThree}
               }
            )
        }





        previousButton?.setOnClickListener {
            if (currentIndex == 0) {
                currentIndex = questions.size - 1
            } else {
                currentIndex--
            }

            updateQuestion()
            adapter.update(
                    when (currentIndex) {
                        0 -> answerOptionsOne
                        1 -> answerOptionsTwo
                        else -> {answerOptionsThree}
                    }
            )
        }

        homeButton?.setOnClickListener {
            currentIndex = 0
            updateQuestion()
            adapter.update(answerOptionsOne)
        }







    }

    override fun onAnswerClick(answer: String) {
        if (answers[currentIndex] == answer) {
           responseTextView?.text = "Correct"
        } else {
            responseTextView?.text = "Incorrect"

        }

        responseTextView?.visibility = View.VISIBLE

    }

    private fun updateQuestion() {
        questionText!!.text = questions[currentIndex]
        responseTextView?.visibility = View.INVISIBLE
        recyclerView?.visibility = View.INVISIBLE
    }
}
