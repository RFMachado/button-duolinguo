package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.DuoButtonBinding

class DuoButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: DuoButtonBinding
    private lateinit var button: LinearLayout

    init {
        inflateLayout()
        bindViews()
        setupOntouchListener()
    }

    private fun createFramelayout() {
//        val frameLayout = FrameLayout(context).apply {
//            layoutParams = FrameLayout.LayoutParams(
//                LayoutParams.MATCH_CONSTRAINT,
//                LayoutParams.MATCH_CONSTRAINT
//            )
//            background = ContextCompat.getDrawable(context, R.drawable.duolingo_button_bg_shadow)
//        }
//
//        // Criando as regras de posicionamento no ConstraintLayout
//        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_CONSTRAINT).apply {
//            bottomToBottom = binding.btn.id
//            startToStart = binding.btn.id
//            endToEnd = binding.btn.id
//            startToStart = binding.btn.id
//        }
//
//// Aplicando as regras ao FrameLayout
//        frameLayout.layoutParams = layoutParams
//
//        frameLayout.translationY = dpToPx(4f)
    }

    private fun setupOntouchListener() {
        val shadowSize = 2f // dp
        setOnClickListener {  }
        apply {
            isClickable = true
            isFocusable = true
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

    private fun bindViews() {
        button = binding.btn
    }

    fun dpToPx(dp: Float): Float {
        return dp * resources.displayMetrics.density
    }

    private fun inflateLayout() {
        binding = DuoButtonBinding.inflate(
            LayoutInflater.from(context), this
        )
    }
}