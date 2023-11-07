package com.rajeev.fooddelivery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rajeev.fooddelivery.MenuBottomSheetFragment
import com.rajeev.fooddelivery.R
import com.rajeev.fooddelivery.adapter.MenuAdapter
import com.rajeev.fooddelivery.adapter.PopularAdapter
import com.rajeev.fooddelivery.databinding.FragmentHomeBinding
import com.rajeev.fooddelivery.modal.MenuItem

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var databse: FirebaseDatabase
    private lateinit var menuItems: MutableList<MenuItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.viewAllMenu.setOnClickListener{
            val bottomSheetDialog = MenuBottomSheetFragment()
            bottomSheetDialog.show(parentFragmentManager,"Tag")
        }
        //retrieve and display popular menu item
        retriveMenuAndDisplay()
        return binding.root
    }

    private fun retriveMenuAndDisplay() {
        //grt a reference of databse
        databse = FirebaseDatabase.getInstance()
        val foodRef =  databse.reference.child("menu")
        menuItems = mutableListOf()
        //retive items from the database
        foodRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(foodSnapShot in snapshot.children){
                    val menuItem = foodSnapShot.getValue(MenuItem::class.java)
                    menuItem?.let {menuItems.add(it)  }
                }
                // display random popular items
                randomPopularItems()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun randomPopularItems() {
        // create as shuffled list of item
        val index = menuItems.indices.toList().shuffled()
        val numItem = 6
        val subSetMenuItem = index.take(numItem).map { menuItems[it]}

        setPopularItems(subSetMenuItem)
    }

    private fun setPopularItems(subSetMenuItem: List<MenuItem>) {
        val adapter = MenuAdapter(subSetMenuItem,requireContext())
        binding.popularRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.popularRecycler.adapter = adapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.banner1, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner2, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.banner3, ScaleTypes.FIT))

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)


        val foodName =
            listOf("Apple", "Orange", "PineApple", "Berry", "harry", "kjsaddfbh", "jasdgljh")
        val foodPrice = listOf("$5", "$6", "$50", "$100", "$500", "jkgjhbv", "clchidch")
        val popularFoodImages = listOf(
            R.drawable.menu1,
            R.drawable.menu2,
            R.drawable.menu3,
            R.drawable.menu4,
            R.drawable.menu5,
            R.drawable.menu6,
            R.drawable.menu7
        )

    }


    companion object {

    }
}