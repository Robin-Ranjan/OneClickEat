package com.rajeev.fooddelivery.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajeev.fooddelivery.PayOutActivity
import com.rajeev.fooddelivery.R
import com.rajeev.fooddelivery.adapter.CartAdapter
import com.rajeev.fooddelivery.databinding.CartRecycleItemBinding
import com.rajeev.fooddelivery.databinding.FragmentCartBinding

class CartFragment : Fragment() {

private lateinit var binding: FragmentCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        val cartFoodName = listOf("Burger","Apple","items","momo","Bread","Mixture")
        val cartItemPrice = listOf("₹15","₹44","₹45","₹56","₹78","₹67")
        val cartImage =
            listOf(R.drawable.menu1,
            (R.drawable.menu2),
           R.drawable.menu3,
           R.drawable.menu4,
            R.drawable.menu5,
           R.drawable.menu6)
        val adapter = CartAdapter(ArrayList(cartFoodName),ArrayList(cartItemPrice),ArrayList(cartImage))
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecyclerView.adapter = adapter

        binding.btnProceed.setOnClickListener{
            val intent = Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {

    }
}