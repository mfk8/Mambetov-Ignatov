package com.HSEteam.FinalProjectQuiz

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView

import java.util.ArrayList

class QuestionAdapter internal constructor(private val mContext: Context, private val mQuestionList: ArrayList<Question>, private var mCategoryLabel: String?) : RecyclerView.Adapter<*>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mCategoryLabel = if (mCategoryLabel!!.length > 2)
            mCategoryLabel!!.substring(0, 1).toUpperCase() + mCategoryLabel!!.substring(1)
        else
            mCategoryLabel!!.toUpperCase()
        (mContext as QuestionActivity).title = mCategoryLabel
        val questionView = LayoutInflater.from(mContext)
                .inflate(R.layout.question_card_layout, parent, false)
        return QuestionViewHolder(questionView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val question = mQuestionList[position]
        val questionViewHolder = holder as QuestionViewHolder
        questionViewHolder.mQuestion.text = question.getmQuestion()
        questionViewHolder.mRb1.text = question.getmOption1()
        questionViewHolder.mRb2.text = question.getmOption2()
        questionViewHolder.mRb3.text = question.getmOption3()
        questionViewHolder.mRb4.text = question.getmOption4()

        val radioButtons = questionViewHolder.mTableLayout.children
        for (rb in radioButtons) {
            rb.setOnClickListener { questionViewHolder.mTableLayout.checkAnswer(rb, question.getmAnswer(), mContext) }
        }
    }

    override fun getItemCount(): Int {
        return mQuestionList.size
    }

    internal inner class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var mQuestion: TextView
        var mRb1: RadioButton
        var mRb2: RadioButton
        var mRb3: RadioButton
        var mRb4: RadioButton

        var mTableLayout: ToggleButtonGroupTableLayout

        init {
            mQuestion = itemView.findViewById(R.id.question)

            mRb1 = itemView.findViewById(R.id.rb1)
            mRb2 = itemView.findViewById(R.id.rb2)
            mRb3 = itemView.findViewById(R.id.rb3)
            mRb4 = itemView.findViewById(R.id.rb4)

            mTableLayout = itemView.findViewById(R.id.table_layout)
        }
    }

}
