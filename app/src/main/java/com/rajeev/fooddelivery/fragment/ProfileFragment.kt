package com.rajeev.fooddelivery.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rajeev.fooddelivery.R
import com.rajeev.fooddelivery.databinding.ActivityPayOutBinding
import com.rajeev.fooddelivery.databinding.FragmentProfileBinding
import com.rajeev.fooddelivery.modal.UserModal


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var userId:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentProfileBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        setUserData()
        binding.saveInfo.setOnClickListener{
            val name = binding.profileName.text.toString()
            val address = binding.profileAdddress.text.toString()
            val email = binding.profileEmail.text.toString()
            val phone = binding.profilePhone.text.toString()

            updateUserData(name,email,address,phone)
        }
        return binding.root
    }

    private fun updateUserData(name: String, email: String, address: String, phone: String) {
        val userId = auth.currentUser?.uid
        if(userId!= null){
            val userReference = database.getReference("user").child(userId)
            val userData = hashMapOf(
                "name" to name ,
                "email" to email ,
                "address" to address,
                "phone" to phone
            )

            userReference.setValue(userData).addOnSuccessListener {
                Toast.makeText(requireContext(), "Successfully updated ", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(requireContext(), "Failed to update", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUserData() {
       val  userId = auth.currentUser?.uid
        if(userId != null){
            val userReference = database.getReference("user").child(userId)

            userReference.addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val userProfile = snapshot.getValue(UserModal::class.java)
                        if(userProfile != null){
                            binding.profileName.setText(userProfile.name)
                            binding.profileAdddress.setText(userProfile.address)
                            binding.profileEmail.setText(userProfile.email)
                            binding.profilePhone.setText(userProfile.phone)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }


}
