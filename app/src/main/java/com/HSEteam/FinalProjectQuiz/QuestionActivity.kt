package com.HSEteam.FinalProjectQuiz

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.constraint.ConstraintLayout
import android.widget.TextView

import java.util.ArrayList
import java.util.Objects

import com.HSEteam.FinalProjectQuiz.CategoryAdapter.CATEGORY_COLOR
import com.HSEteam.FinalProjectQuiz.CategoryAdapter.CATEGORY_ID

class QuestionActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: QuestionAdapter? = null
    private var mQuestionList: ArrayList<Question>? = null
    private var mDbHelper: QuizDBHelper? = null

    private var mParentLayout: ConstraintLayout? = null
    private var mScoreTextView: TextView? = null
    private var mRemaningQuestionsTextView: TextView? = null
    private var mTotalQuestions: Int = 0
    private var mScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        Objects.requireNonNull<ActionBar>(supportActionBar).setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        var categoryBundle: Bundle? = null
        if (intent != null) {
            categoryBundle = intent.extras
        }

        mParentLayout = findViewById(R.id.question_layout)
        if (categoryBundle != null) {
            var hexColor = String.format("#%06X", 0xFFFFFF and categoryBundle.getInt(CATEGORY_COLOR))
            hexColor = "#44" + hexColor.substring(1)
            mParentLayout!!.setBackgroundColor(Color.parseColor(hexColor))
        }

        mScoreTextView = findViewById(R.id.score)
        mRemaningQuestionsTextView = findViewById(R.id.remaining_questions)

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView!!.isNestedScrollingEnabled = false
        mRecyclerView!!.setHasFixedSize(true)

        mDbHelper = QuizDBHelper(this, categoryBundle)
        if (categoryBundle != null) {
            mQuestionList = mDbHelper!!.getAllQuestions(categoryBundle.getString(CATEGORY_ID))
            mTotalQuestions = mQuestionList!!.size
            mScore = 0
            displayScore()
        }
        mAdapter = QuestionAdapter(this, mQuestionList, categoryBundle!!.getString(CATEGORY_ID))
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.adapter = mAdapter
    }

    fun displayScore() {
        val scoreString = "Score $mScore/$mTotalQuestions"
        mScoreTextView!!.text = scoreString
        mRemaningQuestionsTextView!!.text = "Remaining: " + mTotalQuestions--
    }

    fun updateScore() {
        mScore++
    }
}
