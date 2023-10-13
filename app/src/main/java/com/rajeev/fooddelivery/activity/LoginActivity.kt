package com.rajeev.fooddelivery.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rajeev.fooddelivery.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {
    private val binding : LoginActivityBinding by lazy {
        LoginActivityBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.txtDoNotHaveAccount.setOnClickListener(){
           val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnLogin.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}