package com.flaringapp.sortvisualiztion.utils.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.View.*
import android.view.ViewPropertyAnimator
import com.flaringapp.sortvisualiztion.app.constants.Constants.ANIM_DURATION
import com.flaringapp.sortvisualiztion.utils.view.ViewUtils.animateAppear
import com.flaringapp.sortvisualiztion.utils.view.ViewUtils.animateHide


object ViewUtils {

    fun animateAppear(view: View) {
        if (view.visibility == INVISIBLE or GONE) {
            view.visibility = VISIBLE
            view.alpha = 0f
        }

        animateAlpha(view, 1f).start()
    }

    fun animateHide(view: View) {
        animateAlpha(view, 0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    view.visibility = GONE
                }
            })
    }

    fun animateAlpha(view: View, to: Float): ViewPropertyAnimator {
        view.animate().cancel()
        return view.animate()
            .alpha(to)
            .setDuration(ANIM_DURATION)
    }
}

fun View.gone(animate: Boolean = true) {
    if (animate) animateHide(this)
    else visibility = GONE
}

fun View.show(animate: Boolean = true) {
    if (animate) animateAppear(this)
    else visibility = VISIBLE.also { alpha = 1f }
}

fun Context.dp(dp: Float): Float {
    return resources.dp(dp)
}

fun Context.dp(dp: Int): Float {
    return resources.dp(dp)
}

fun Resources.dp(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

fun Resources.dp(dp: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), displayMetrics)
}