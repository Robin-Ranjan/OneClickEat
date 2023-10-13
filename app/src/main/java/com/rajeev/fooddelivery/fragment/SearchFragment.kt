package com.rajeev.fooddelivery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajeev.fooddelivery.R
import com.rajeev.fooddelivery.adapter.MenuAdapter
import com.rajeev.fooddelivery.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter

    private val originalMenuFoodName = listOf(
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
    private val originalMenuItemPrice =
        listOf("₹15", "₹44", "₹45", "₹56", "₹78", "₹67", "₹44", "₹45", "₹56", "₹78", "₹67")

    private val originalMenuImage =
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val filterMenuFoodName= mutableListOf<String>()
    private val filterMenuItemPrice= mutableListOf<String>()
    private val filterMenuImage= mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        adapter = MenuAdapter(
            filterMenuFoodName, filterMenuItemPrice,
            filterMenuImage,requireContext())
        binding.menuRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRecycler.adapter = adapter

        //setup for search view
        setUpSearchView()
        //show all menu item
        showAllMenu()
        return binding.root
    }

    private fun showAllMenu() {
        filterMenuFoodName.clear()
        filterMenuFoodName.clear()
        filterMenuImage.clear()

        filterMenuFoodName.addAll(originalMenuFoodName)
        filterMenuItemPrice.addAll(originalMenuItemPrice)
        filterMenuImage.addAll(originalMenuImage)
        adapter.notifyDataSetChanged()


    }

    private fun setUpSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterMenuItem(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
               filterMenuItem(newText)
                return true
            }
        })
    }

    private fun filterMenuItem(query: String) {

        filterMenuFoodName.clear()
        filterMenuFoodName.clear()
        filterMenuImage.clear()
        originalMenuFoodName.forEachIndexed{index, FoodName ->
            if(FoodName.contains(query.toString(),ignoreCase = true)){
                filterMenuFoodName.add(FoodName)
                filterMenuItemPrice.add(originalMenuItemPrice[index])
                filterMenuImage.add(originalMenuImage[index])
            }
        }
        adapter.notifyDataSetChanged()

    }

    companion object {

    }
}