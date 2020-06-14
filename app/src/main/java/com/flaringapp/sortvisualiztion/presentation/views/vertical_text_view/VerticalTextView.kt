package com.flaringapp.sortvisualiztion.presentation.views.vertical_text_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.widget.TextView


class VerticalTextView : TextView {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        val csl = textColors
        val color = csl.defaultColor

        val paddingBottom = paddingBottom.toFloat()
        val paddingTop = paddingTop.toFloat()

        val viewWidth = width
        val viewHeight = height

        val paint = paint
        paint.color = color

        val bottom = viewWidth * 9.0f / 11.0f

        path.reset()
        path.moveTo(bottom, (viewHeight - paddingBottom - paddingTop))
        path.lineTo(bottom, paddingTop)

        canvas.drawTextOnPath(text.toString(), path, 0f, 0f, paint)
    }
}