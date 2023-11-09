package com.rajeev.fooddelivery.modal
data class CartItems(
    val foodName:String ?= null,
    val foodPrice:String ?= null,
    val foodDiscription:String ?= null,
    val foodImage:String ?= null,
    val foodQuantity:Int ?= null,
    val foodIngredient:String?= null

)
