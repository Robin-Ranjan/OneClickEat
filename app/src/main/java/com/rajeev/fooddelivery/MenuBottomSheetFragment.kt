package com.rajeev.fooddelivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rajeev.fooddelivery.adapter.MenuAdapter
import com.rajeev.fooddelivery.databinding.FragmentMenuBottomSheetBinding


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)
        binding.btnBack.setOnClickListener{
            dismiss()
        }
        val menuFoodName = listOf(
            "Burger",
            "Apple",
            "items",
            "momo",
            "Bread",
            "Mixture",
            "Apple",
            "items",
            "momo",
            "Bread",
            "Mixture"
        )
        val menuItemPrice =
            listOf("₹15", "₹44", "₹45", "₹56", "₹78", "₹67", "₹44", "₹45", "₹56", "₹78", "₹67")
        val menuImage =
            listOf(
                R.drawable.menu1,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu4,
                R.drawable.menu5,
                R.drawable.menu6,
                R.drawable.menu2,
                R.drawable.menu3,
                R.drawable.menu4,
                R.drawable.menu5,
                R.drawable.menu6
            )
        val adapter =
            MenuAdapter(ArrayList(menuFoodName), ArrayList(menuItemPrice), ArrayList(menuImage),requireContext())
        binding.menuRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecycler.adapter = adapter

        return binding.root
    }

    companion object {

    }
}
