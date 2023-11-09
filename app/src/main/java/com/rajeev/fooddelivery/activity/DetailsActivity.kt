package com.rajeev.fooddelivery.activity

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.rajeev.fooddelivery.databinding.ActivityDetailsBinding
import com.rajeev.fooddelivery.modal.CartItems

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private var foodName: String? = null
    private var foodImage: String? = null
    private var foodDescription: String? = null
    private var foodIngrediants: String? = null
    private var foodPrice: String? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize firebase auth
        auth = FirebaseAuth.getInstance()

        foodName = intent.getStringExtra("MenuItemName")
        foodDescription = intent.getStringExtra("MenuItemDescription")
        foodIngrediants = intent.getStringExtra("MenuItemIngredients")
        foodPrice = intent.getStringExtra("MenuItemPrice")

        foodImage = intent.getStringExtra("MenuItemImage")
        with(binding) {
            detailFoodName.text = foodName.toString()
            descriptionTextView.text = foodDescription
            ingrediants.text = foodIngrediants
            Glide.with(this@DetailsActivity).load(Uri.parse(foodImage)).into(detailFoodImage)
        }



        binding.imageButton.setOnClickListener {
            finish()
        }

        binding.addToCart.setOnClickListener {
            addItemToCart()
        }

    }

    private fun addItemToCart() {
        val database = FirebaseDatabase.getInstance().reference
        val userId: String = auth.currentUser?.uid ?: ""
        // create a cart items object for a particular user
        val cartItem = CartItems(
            foodName.toString(),
            foodPrice.toString(),
            foodDescription.toString(),
            foodImage.toString(),
            1
        )

        //save data to cart item to firebase database

        database.child("user").child(userId).child("cartItems").push().setValue(cartItem)
            .addOnSuccessListener {
                Toast.makeText(this, "item added successfully😁😁", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Item Not added 😢", Toast.LENGTH_SHORT).show()
            }


    }
}