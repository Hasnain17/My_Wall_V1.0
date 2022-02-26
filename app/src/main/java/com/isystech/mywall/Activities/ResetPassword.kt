package com.isystech.mywall.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isystech.mywall.R
import com.isystech.mywall.Utils.OnSwipeTouchListener
import com.isystech.mywall.databinding.ActivityResetPasswordBinding

class ResetPassword : AppCompatActivity() {
    private lateinit var binding:ActivityResetPasswordBinding
    var count = 0
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {
            override fun onSwipeTop() {}
            override fun onSwipeRight() {
                count = if (count == 0) {
                    binding.imageView.setImageResource(R.drawable.good_night_img)
                    binding.textView.text="Night"
                    1
                } else {
                    binding.imageView.setImageResource(R.drawable.good_morning_img)
                    binding.textView.text="Morning"
                    0
                }
            }

            override fun onSwipeLeft() {
                count = if (count == 0) {
                    binding.imageView.setImageResource(R.drawable.good_night_img)
                    binding.textView.text="Night"
                    1
                } else {
                    binding.imageView.setImageResource(R.drawable.good_morning_img)
                    binding.textView.text="Morning"
                    0
                }
            }
            override fun onSwipeBottom() {

            }
        })
    }
}