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
import com.isystech.mywall.databinding.ActivitySignInBinding
import java.lang.Exception

@SuppressLint("ClickableViewAccessibility")
class SignInActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding:ActivitySignInBinding
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
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

        binding.btnSignup.setOnClickListener{
            intent= Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        binding.tvResetPass.setOnClickListener{
            intent= Intent(this, ResetPassword::class.java)
            startActivity(intent)
        }
        binding.btnSignIn.setOnClickListener{
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            try {
                if (email.isEmpty()||password.isEmpty())
                {
                    throw Exception("All fields must be filled")
                }
                else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches()))
                {
                    throw Exception("Invalid Email Address")
                }
                else
                {
                    signIn(email,password)
                }
            }
            catch (e:Exception){
                showToast(e.message.toString())
            }
        }
    }

    private fun signIn(email: String, password: String)
    {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if (it.isSuccessful)
            {
                showToast("Sign-In")
            }
            else
            {
                showToast("Sign-In Error: "+it.exception.toString())
            }
        }
    }
    /*
    private fun signInUser() {
        val email=binding.etEmail.text.toString()
        val password=binding.etPassword.text.toString()
        if (email.isBlank()||password.isBlank())
        {
            showToast("All fields must be filled")
            return
        }
        else if (password.length<6)
        {
            showToast("Password length should be more than 6 character")
            return
        }
        else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
          showToast(("Invalid Email Address"));
        }
        else
        {
            goToFireBase(email,password)
        }
    }
    private fun goToFireBase(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if (it.isSuccessful)
            {
                showToast("Sign-In Successfully")
                intent=Intent(applicationContext,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
            {
                if (it.exception in FirebaseAuthInvalidCredentialsException1)
                {

                }
                showToast("Sign-in Failed")
            }
        }
    }*/

    private fun showToast(message: String) {
        Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()
    }
}