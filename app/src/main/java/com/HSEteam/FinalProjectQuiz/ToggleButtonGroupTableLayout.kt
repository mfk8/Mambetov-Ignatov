package com.HSEteam.FinalProjectQuiz

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import android.widget.RadioButton
import android.widget.TableLayout
import android.widget.TableRow

import java.util.ArrayList

class ToggleButtonGroupTableLayout : TableLayout {

    private var mActiveRadioButton: RadioButton? = null


    val children: ArrayList<RadioButton>
        get() {
            val radioButtons = ArrayList<RadioButton>()
            val childCount = this.childCount
            for (i in 0 until childCount) {
                val tableRow = this.getChildAt(i) as TableRow
                val rowChildCount = tableRow.childCount
                for (j in 0 until rowChildCount) {
                    val v = tableRow.getChildAt(j)
                    if (v is RadioButton) {
                        radioButtons.add(v)
                    }
                }
            }
            return radioButtons
        }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    fun checkAnswer(rb: RadioButton, answer: String, mContext: Context) {
        if (mActiveRadioButton != null) {
            mActiveRadioButton!!.isChecked = false
        }
        var id = -1
        rb.isChecked = true
        if (rb.text == answer) {
            setRadioButtonBackgroundColor(rb, R.color.transparent_green)
            (mContext as QuestionActivity).updateScore()
        } else {
            setRadioButtonBackgroundColor(rb, R.color.transparent_red)
            for (radioButton in children) {
                if (radioButton.text == answer) {
                    setRadioButtonBackgroundColor(radioButton, R.color.transparent_green)
                    id = radioButton.id
                }
            }
        }
        (mContext as QuestionActivity).displayScore()
        mActiveRadioButton = rb
        for (radioButton in children) {
            radioButton.isClickable = false
            if (radioButton.id != rb.id && radioButton.id != id) {
                setRadioButtonBackgroundColor(radioButton, R.color.transparent_grey)
                radioButton.setTextColor(resources.getColor(R.color.transparent_black))
            }
        }

    }

    private fun setRadioButtonBackgroundColor(button: RadioButton, colorId: Int) {
        button.background.setColorFilter(Color.parseColor(context.getString(colorId)), PorterDuff.Mode.MULTIPLY)
    }
}
