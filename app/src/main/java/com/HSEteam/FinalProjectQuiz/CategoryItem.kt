package com.HSEteam.FinalProjectQuiz

class CategoryItem internal constructor(private val mBgColor: Int, private val mCategoryTitle: String, private val mCategoryID: String) {

    fun getmBgColor(): Int {
        return mBgColor
    }

    fun getmCategoryTitle(): String {
        return mCategoryTitle
    }

    fun getmCategoryID(): String {
        return mCategoryID
    }
}
