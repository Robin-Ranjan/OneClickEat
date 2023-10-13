package com.rajeev.fooddelivery.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rajeev.fooddelivery.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
   private  val binding:ActivitySignInBinding by lazy {
       ActivitySignInBinding.inflate(layoutInflater)
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.txtAlreadyHaveAccount.setOnClickListener(){
            val  intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnCreatAccount.setOnClickListener(){
            val intent = Intent(this, ChooseLocation::class.java)
            startActivity(intent)
            finish()
        }

    }
}