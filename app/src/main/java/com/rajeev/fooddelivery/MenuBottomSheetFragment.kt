package com.rajeev.fooddelivery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rajeev.fooddelivery.adapter.MenuAdapter
import com.rajeev.fooddelivery.databinding.FragmentMenuBottomSheetBinding
import com.rajeev.fooddelivery.modal.MenuItem


class MenuBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentMenuBottomSheetBinding

    private lateinit var database:FirebaseDatabase
    private lateinit var menuItems:MutableList<MenuItem>



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

        retriveMenuItems()

        return binding.root
    }

    private fun retriveMenuItems() {
        database = FirebaseDatabase.getInstance()
        val foodRef :DatabaseReference = database.reference.child("menu")
        menuItems = mutableListOf()

        foodRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(foodSnapShot in snapshot.children){
                    val menuItem = foodSnapShot.getValue(MenuItem::class.java)
                    menuItem?.let { menuItems.add(it)  }
                }
                // set to adapter
                setAdapter()
            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setAdapter() {
        if(menuItems.isNotEmpty()){
            val adapter = MenuAdapter(menuItems,requireContext())
            binding.menuRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.menuRecycler.adapter = adapter
        }
    }

    companion object {

    }
}
