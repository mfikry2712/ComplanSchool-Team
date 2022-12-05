package com.example.complanschool.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.example.complanschool.R

class CustomButtonGoogle : AppCompatButton {

    private lateinit var enabledBackground: Drawable
    private lateinit var ic: Drawable
    private var txtColor: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        background = enabledBackground
        setTextColor(txtColor)
        textSize = 12f
        gravity = Gravity.CENTER
        text = resources.getString(R.string.cv_btn_google)
    }
    private fun init() {
        ic = ContextCompat.getDrawable(context, R.drawable.ic_google) as Drawable
        showClearButton()
        //compoundDrawablePadding = 0
        //setButtonDrawables(ic)
        txtColor = ContextCompat.getColor(context, android.R.color.black)
        enabledBackground = ContextCompat.getDrawable(context, R.drawable.custom_buttongoogle) as Drawable
    }

    private fun showClearButton() {
        setButtonDrawables(startOfTheText = ic)
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText:Drawable? = null,
        endOfTheText:Drawable? = null,
        bottomOfTheText: Drawable? = null
    ){
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }
}