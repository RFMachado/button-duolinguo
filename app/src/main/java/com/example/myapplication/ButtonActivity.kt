package com.example.myapplication

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityButtonBinding

class ButtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityButtonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val shadowSize = 4f // dp

        fun dpToPx(dp: Float): Float {
            return dp * resources.displayMetrics.density
        }

        binding.button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.button.animate().translationY(dpToPx(shadowSize)).setDuration(50).start()
                   // binding.shadowView.animate().translationY(0f).setDuration(50).start()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    binding.button.animate().translationY(0f).setDuration(50).start()
                  //  binding.shadowView.animate().translationY(dpToPx(shadowSize)).setDuration(50).start()
                    if (event.action == MotionEvent.ACTION_UP) {
                        binding.button.performClick()
                    }
                }
            }
            true
        }

    }


}