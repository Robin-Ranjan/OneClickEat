package com.rajeev.fooddelivery.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rajeev.fooddelivery.activity.DetailsActivity
import com.rajeev.fooddelivery.databinding.PopularRecycleviewListBinding

class PopularAdapter(
    private var items: List<String>,
    private val price: List<String>,
    private val image: List<Int>, private val requiredContext: Context
) : RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {

        return PopularViewHolder(
            PopularRecycleviewListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val items = items[position]
        val image = image[position]
        val price = price[position]
        holder.bind(items, image, price)

        holder.itemView.setOnClickListener{
            val intent = Intent(requiredContext,DetailsActivity::class.java)
            intent.putExtra("MenuItemName",items)
            intent.putExtra("MenuItemImage",image)
            requiredContext.startActivity(intent)


        }
    }

    override fun getItemCount(): Int {
       return  items.size
    }

    class PopularViewHolder(private val binding: PopularRecycleviewListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView  = binding.foodImage
        fun bind(items: String, image: Int, price: String) {
            binding.foodName.text = items
            binding.foodPrice.text = price
            imageView.setImageResource(image)


        }


    }

}