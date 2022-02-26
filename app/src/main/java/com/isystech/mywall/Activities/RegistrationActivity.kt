package com.isystech.mywall.Activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.isystech.mywall.R
import com.isystech.mywall.Utils.OnSwipeTouchListener
import com.isystech.mywall.databinding.ActivityRegistrationBinding
import java.lang.Exception

class RegistrationActivity : AppCompatActivity(){
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityRegistrationBinding
    var count = 0
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()

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
        binding.btnSignUp.setOnClickListener{
            val name=binding.etName.text.toString()
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.toString()
            try {
                if (email.isEmpty()||name.isEmpty()||password.isEmpty()){
                    throw Exception("All fields must be filled")
                }
                else if (password.length<6)
                {
                    throw   Exception("Password length should be more than 6 character")

                }
                else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches()))
                {
                    throw  Exception("Invalid Email Address")
                }
                else{
                    registerUser(name,email,password)
                }
            }
            catch (e: Exception){
                showToast(e.message.toString())
            }
        }
    }
    private fun registerUser(name: String, email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if (it.isSuccessful){
                showToast("SignUp Successful")
                intent= Intent(applicationContext, HomeActivity::class.java)
                startActivity(intent)
            }
            else
            {
                showToast("SignUp Error")
            }
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }
}