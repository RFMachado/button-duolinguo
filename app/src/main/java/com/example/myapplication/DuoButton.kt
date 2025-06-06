package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.DuoButtonBinding

class DuoButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: DuoButtonBinding

    init {
       inflateLayout()
       bindViews()
    }

    private fun bindViews() {
        val shadowSize = 4f // dp

        fun dpToPx(dp: Float): Float {
            return dp * resources.displayMetrics.density
        }

        binding.btn.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.btn.translationY = dpToPx(shadowSize)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    binding.btn.translationY = 0f
                }
            }
            true
        }
    }

    private fun inflateLayout() {
        binding = DuoButtonBinding.inflate(
            LayoutInflater.from(context),this)
    }
}