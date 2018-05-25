package es.uplace.uplace

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.MenuItem
import android.view.View
import android.view.animation.Interpolator

/**
 * [android.view.MenuItem.OnMenuItemClickListener] usado para deslizar el property recycler view
 * hacia abajo en el eje-Y cuando el menu item filter en el toolbar es presionado.
 */
class FilterIconClickListener @JvmOverloads internal constructor(
        private val context: Context,
        private val sheet: View,
        private val interpolator: Interpolator? = null) : MenuItem.OnMenuItemClickListener {

    override fun onMenuItemClick(p0: MenuItem?): Boolean {

        backdropShown = !backdropShown

        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        val translateY = height - context.resources.getDimensionPixelSize(R.dimen.property_recycler_view_reveal_height)
        val animator = ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())

        animator.duration = 500

        if (interpolator != null) {
            animator.interpolator = interpolator
        }

        animatorSet.play(animator)
        animator.start()

        return true
    }

    private val animatorSet = AnimatorSet()
    private val height: Int
    private var backdropShown = false

    init {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        height = displayMetrics.heightPixels
    }
}
