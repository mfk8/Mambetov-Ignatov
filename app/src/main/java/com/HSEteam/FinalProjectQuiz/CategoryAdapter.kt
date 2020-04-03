package com.HSEteam.FinalProjectQuiz

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList

class CategoryAdapter internal constructor(private val mContext: Context, resource: Int, private val mCategoryItems: ArrayList<CategoryItem>) : ArrayAdapter<CategoryItem>(mContext, resource, mCategoryItems) {

    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        var categoryViewHolder: CategoryViewHolder
        val categoryItem = mCategoryItems[position]

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.grid_view_item, parent, false)
            categoryViewHolder = CategoryViewHolder(convertView)
            convertView!!.tag = categoryViewHolder
        }
        categoryViewHolder = convertView.tag as CategoryViewHolder
        categoryViewHolder.categoryImage.setBackgroundColor(categoryItem.getmBgColor())
        categoryViewHolder.categoryTitle.text = categoryItem.getmCategoryTitle()
        categoryViewHolder.categoryImage.setOnClickListener {
            val questionIntent = Intent(mContext, QuestionActivity::class.java)
            questionIntent.putExtra(CATEGORY_ID, categoryItem.getmCategoryID())
            questionIntent.putExtra(CATEGORY_COLOR, categoryItem.getmBgColor())
            mContext.startActivity(questionIntent)
        }
        return convertView
    }

    internal class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var categoryImage: ImageView
        var categoryTitle: TextView

        init {
            categoryImage = itemView.findViewById(R.id.category_image)
            categoryTitle = itemView.findViewById(R.id.category_title)
        }
    }

    companion object {

        internal val CATEGORY_COLOR = "CategoryColor"
        internal val CATEGORY_ID = "CategoryID"
    }
}
