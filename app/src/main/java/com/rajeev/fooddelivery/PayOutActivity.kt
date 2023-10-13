package com.rajeev.fooddelivery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rajeev.fooddelivery.databinding.ActivityChooseLocationBinding
import com.rajeev.fooddelivery.databinding.ActivityPayOutBinding
import com.rajeev.fooddelivery.fragment.CongratsBottomSheet

class PayOutActivity : AppCompatActivity() {
   private lateinit var binding:ActivityPayOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.placeOrder.setOnClickListener{
            val bottomSheetDialog = CongratsBottomSheet()
            bottomSheetDialog.show(supportFragmentManager,"Test")
        }
    }
}