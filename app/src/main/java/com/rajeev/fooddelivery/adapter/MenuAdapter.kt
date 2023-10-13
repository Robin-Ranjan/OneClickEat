package com.rajeev.fooddelivery.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rajeev.fooddelivery.activity.DetailsActivity
import com.rajeev.fooddelivery.databinding.FragmentMenuBottomSheetBinding
import com.rajeev.fooddelivery.databinding.MenuItemBinding

class MenuAdapter(
    private var MenuItemNames: MutableList<String>,
    private val MenuItemPrice: MutableList<String>,
    private val MenuImage: MutableList<Int>, private val requireContext: Context
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private val itemClicklistener: OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return MenuItemNames.size
    }

    inner class MenuViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClicklistener?.onItemClick(position)
                }

                //set on click listener to open details

                val intent = Intent(requireContext, DetailsActivity::class.java)
                intent.putExtra("MenuItemName", MenuItemNames.get(position))
                intent.putExtra("MenuItemImage", MenuImage.get(position))
                requireContext.startActivity(intent)
            }
        }

        fun bind(position: Int) {
            binding.apply {
                menuFoodName.text = MenuItemNames[position]
                menuFoodPrice.text = MenuItemPrice[position]
                menuImage.setImageResource(MenuImage[position])


            }
        }

    }
    interface OnClickListener {
        fun onItemClick(position: Int)
    }

}



