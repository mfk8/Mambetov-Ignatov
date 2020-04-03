package com.HSEteam.FinalProjectQuiz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView

import java.util.ArrayList

class DashboardActivity : AppCompatActivity() {

    private var mGridView: GridView? = null
    private var mCategoryAdapter: CategoryAdapter? = null
    private var mCategoryItems: ArrayList<CategoryItem>? = null
    private var mColors: IntArray? = null
    private var mCategoryTitles: Array<String>? = null
    private var mCategoryID: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setUpCategoryItems()

        mGridView = findViewById(R.id.categories_list)
        mCategoryAdapter = CategoryAdapter(this, R.layout.grid_view_item, mCategoryItems!!)
        mGridView!!.adapter = mCategoryAdapter

    }

    private fun setUpCategoryItems() {
        mCategoryItems = ArrayList()
        mCategoryTitles = resources.getStringArray(R.array.category_title)
        mCategoryID = resources.getStringArray(R.array.category_ID)

        mColors = resources.getIntArray(R.array.colors)

        for (i in mCategoryTitles!!.indices) {
            mCategoryItems!!.add(CategoryItem(mColors!![i], mCategoryTitles!![i], mCategoryID!![i]))
            Log.d("TAG", "Title\t" + mCategoryTitles!![i] + "\tID\t" + mCategoryID!![i])
        }
    }
}
