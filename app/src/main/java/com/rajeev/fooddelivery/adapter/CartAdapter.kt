package com.rajeev.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rajeev.fooddelivery.databinding.CartRecycleItemBinding

class CartAdapter(
    private val CartItems: MutableList<String>,
    private var CartItemPrice: MutableList<String>,
    private var CartImage: MutableList<Int>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val itemQuantities = IntArray(CartItems.size) { 1 }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding =
            CartRecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = CartItems.size


    inner class CartViewHolder(private val binding: CartRecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                val quantites = itemQuantities[position]
                cartFoodName.text = CartItems[position]
                cartItemPrice.text = CartItemPrice[position]
                cartImage.setImageResource(CartImage[position])
                txtCartQuantity.text = quantites.toString()

                btnMinus.setOnClickListener {
                    deceaseQuantity(position)
                }
                btnPlus.setOnClickListener {
                    increseQuantity(position)
                }

                btnDelete.setOnClickListener {
                    val itemPosition = adapterPosition
                    if(itemPosition != RecyclerView.NO_POSITION){
                        deleteItem(itemPosition)

                    }                    }
                }
            }

            private fun increseQuantity(position: Int) {
                if (itemQuantities[position] < 10) {
                    itemQuantities[position]++
                    binding.txtCartQuantity.text = itemQuantities[position].toString()
                }
            }

            private fun deleteItem(position: Int) {
                CartItems.removeAt(position)
                CartImage.removeAt(position)
                CartItemPrice.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, CartItems.size)
            }

            private fun deceaseQuantity(position: Int) {
                if (itemQuantities[position] > 1) {
                    itemQuantities[position]--
                    binding.txtCartQuantity.text = itemQuantities[position].toString()
                }
            }
        }
    }