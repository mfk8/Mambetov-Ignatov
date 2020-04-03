package com.HSEteam.FinalProjectQuiz

import android.provider.BaseColumns

internal object QuizContract {

    class QuestionsTable : BaseColumns {
        companion object {

            val TABLE_NAME = "quiz_questions"
            val COLUMN_QUESTION = "question"
            val COLUMN_OPTION1 = "option1"
            val COLUMN_OPTION2 = "option2"
            val COLUMN_OPTION3 = "option3"
            val COLUMN_OPTION4 = "option4"
            val COLUMN_ANSWER = "answer"
            val COLUMN_CATEGORY = "category"
        }
    }
}
