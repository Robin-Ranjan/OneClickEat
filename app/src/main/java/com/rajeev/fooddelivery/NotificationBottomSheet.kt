package com.rajeev.fooddelivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rajeev.fooddelivery.adapter.NotificationAdapter
import com.rajeev.fooddelivery.databinding.FragmentNotificationBottomsheetBinding

class NotificationBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentNotificationBottomsheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBottomsheetBinding.inflate(layoutInflater, container, false)
        val notification = listOf(
            "YOur Order Has been canceled",
            "Order has been taken",
            "Congratulation your order has been taken"
        )
        val notificationImages = listOf(R.drawable.sademoji, R.drawable.truck, R.drawable.congrats)
        val adapter = NotificationAdapter(
            ArrayList(notification),
            ArrayList(notificationImages)
        )
        binding.notificationRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.notificationRecycler.adapter = adapter
        return binding.root
    }

    companion object {

    }
}