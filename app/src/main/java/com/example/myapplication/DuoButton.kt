package com.example.myapplication

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.myapplication.databinding.DuoButtonBinding

class DuoButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private lateinit var binding: DuoButtonBinding
    private lateinit var button: LinearLayout
    internal lateinit var icon: ImageView

    init {
        inflateLayout()
        bindViews()
        setupOntouchListener()
        loadAttributes(attrs, defStyleAttr)
    }

    private fun inflateLayout() {
        binding = DuoButtonBinding.inflate(
            LayoutInflater.from(context), this
        )
    }

    private fun bindViews() {
        button = binding.btn
        icon = binding.icon
    }

    private fun loadAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        context
            .theme
            .obtainStyledAttributes(
                attrs,
                R.styleable.DuoButton,
                defStyleAttr,
                0
            )
            .use {
                setIconButtonDrawable(it.getDrawable(R.styleable.DuoButton_srcCompat))
            }

        isClickable = true
        isFocusable = true
    }

    private fun setupOntouchListener() {
        val shadowSize = 2f // dp
        setOnClickListener {  }
        apply {
            setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        button.translationY = dpToPx(shadowSize)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        button.translationY = 0f
                        view.performClick()
                    }
                }
                true
            }
        }
    }

    fun dpToPx(dp: Float): Float {
        return dp * resources.displayMetrics.density
    }
}

@BindingAdapter("srcCompat")
fun DuoButton.setIconButtonDrawable(icon: Drawable? = null) {
    this.icon.setImageDrawable(icon)
    this.icon.isVisible = icon != null
}

@BindingAdapter("srcCompat")
fun DuoButton.setIconButtonDrawable(@DrawableRes icon: Int = 0) {
    this.icon.setImageResource(icon)
    this.icon.isVisible = icon != 0
}