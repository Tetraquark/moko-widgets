package com.icerockdev.mpp.widgets.forms

import android.widget.TextView
import com.icerockdev.mpp.widgets.style.view.TextStyle

fun TextView.applyStyle(textStyle: TextStyle) {
    setTextColor(textStyle.color)
    textSize = textStyle.size.toFloat()
    applyFontStyle(textStyle.fontStyle)
}