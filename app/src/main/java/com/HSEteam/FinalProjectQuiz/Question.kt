package com.HSEteam.FinalProjectQuiz

class Question {

    private var mQuestion: String? = null
    private var mOption1: String? = null
    private var mOption2: String? = null
    private var mOption3: String? = null
    private var mOption4: String? = null
    private var mAnswer: String? = null
    private var mCategory: String? = null


    internal constructor() {}

    internal constructor(mQuestion: String, option1: String, option2: String, option3: String, option4: String, answer: String, category: String) {
        this.mQuestion = mQuestion
        this.mOption1 = option1
        this.mOption2 = option2
        this.mOption3 = option3
        this.mOption4 = option4
        this.mAnswer = answer
        this.mCategory = category
    }

    fun getmQuestion(): String? {
        return mQuestion
    }

    fun getmOption1(): String? {
        return mOption1
    }

    fun getmOption2(): String? {
        return mOption2
    }

    fun getmOption3(): String? {
        return mOption3
    }

    fun getmOption4(): String? {
        return mOption4
    }

    fun getmAnswer(): String? {
        return mAnswer
    }

    fun getmCategory(): String? {
        return mCategory
    }

    fun setmQuestion(mQuestion: String) {
        this.mQuestion = mQuestion
    }

    fun setmOption1(mOption1: String) {
        this.mOption1 = mOption1
    }

    fun setmOption2(mOption2: String) {
        this.mOption2 = mOption2
    }

    fun setmOption3(mOption3: String) {
        this.mOption3 = mOption3
    }

    fun setmOption4(mOption4: String) {
        this.mOption4 = mOption4
    }

    fun setmAnswer(mAnswer: String) {
        this.mAnswer = mAnswer
    }

    fun setmCategory(mCategory: String) {
        this.mCategory = mCategory
    }
}
