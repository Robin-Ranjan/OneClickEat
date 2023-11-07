package com.rajeev.fooddelivery.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rajeev.fooddelivery.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
     private lateinit var binding: ActivityDetailsBinding

     private var foodName:String ? = null
     private var foodImage:String ? = null
     private var foodDescription:String ? = null
     private var foodIngrediants:String ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodName = intent.getStringExtra("MenuItemName")
        foodDescription = intent.getStringExtra("MenuItemDescription")
        foodIngrediants = intent.getStringExtra("MenuItemIngredients")

        foodImage= intent.getStringExtra("MenuItemImage")
        with(binding){
            detailFoodName.text = foodName.toString()
            descriptionTextView.text= foodDescription
            ingrediants.text = foodIngrediants
            Glide.with(this@DetailsActivity).load(Uri.parse(foodImage)).into(detailFoodImage)
        }



        binding.imageButton.setOnClickListener{
            finish()
        }

    }
}