package com.example.myapplication

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
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
    private lateinit var shadowBackground: FrameLayout
    internal lateinit var icon: ImageView
    internal lateinit var label: TextView

    companion object {
        const val SHADOW_SIZE = 2f
    }

    init {
        inflateLayout()
        bindViews()
        setupOnTouchListener()
        loadAttributes(attrs, defStyleAttr)
        setupBackground()
    }

    private fun inflateLayout() {
        binding = DuoButtonBinding.inflate(
            LayoutInflater.from(context),
            this
        )
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        label.isEnabled = enabled
        button.isEnabled = enabled
        icon.isEnabled = enabled
        shadowBackground.isVisible = enabled
    }

    private fun bindViews() {
        button = binding.button
        icon = binding.icon
        label = binding.label
        shadowBackground = binding.shadow
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
                setLabel(it.getString(R.styleable.DuoButton_duoButtonLabel))
            }

        isClickable = true
        isFocusable = true
    }

    private fun setupOnTouchListener() {
        setOnClickListener { }
        apply {
            setOnTouchListener { view, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        button.isPressed = true
                        button.translationY = dpToPx(SHADOW_SIZE)
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        button.isPressed = false
                        button.translationY = 0f
                        view.performClick()
                    }
                }
                true
            }
        }
    }

    private fun setupBackground() {
        val labelAndIconColors = ContextCompat.getColorStateList(
            context,
            R.color.text_colors
        )

        button.background = ContextCompat.getDrawable(
            context,
            R.drawable.duolingo_button_bg
        )

        shadowBackground.background = ContextCompat.getDrawable(
            context,
            R.drawable.duolingo_button_bg_shadow
        )

        icon.imageTintList = labelAndIconColors
        label.setTextColor(labelAndIconColors)
    }

    fun dpToPx(dp: Float): Float = dp * resources.displayMetrics.density
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

@BindingAdapter("duoButtonLabel")
fun DuoButton.setLabel(label: String? = null) {
    this.label.text = label
}

@BindingAdapter("duoButtonLabel")
fun DuoButton.setLabel(@StringRes label: Int) {
    try {
        this.label.setText(label)
    } catch (e: Resources.NotFoundException) {
        this.label.text = null
    }
}