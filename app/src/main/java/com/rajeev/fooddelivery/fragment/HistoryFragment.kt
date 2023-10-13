package com.rajeev.fooddelivery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajeev.fooddelivery.R
import com.rajeev.fooddelivery.adapter.BuyAgainAdapter
import com.rajeev.fooddelivery.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

private lateinit var binding:FragmentHistoryBinding
private lateinit var adapter: BuyAgainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater,container,false)
        setUpRecentBuy()
        return binding.root
    }
    private fun setUpRecentBuy(){
        val buyAgainFoodName = arrayListOf("Food 1","Food 2","Food 3")
        val buyAgainFoodPrice = arrayListOf("$1","$2","$3")
        val buyAgainFoodImage = arrayListOf(R.drawable.menu1,R.drawable.menu2,R.drawable.menu3)
        adapter = BuyAgainAdapter(buyAgainFoodName,buyAgainFoodPrice,buyAgainFoodImage)
        binding.buyAgainRecycler.adapter = adapter
        binding.buyAgainRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    companion object {

    }
}