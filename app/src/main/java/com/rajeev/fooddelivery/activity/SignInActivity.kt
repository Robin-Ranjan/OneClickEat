package com.rajeev.fooddelivery.activity

import android.app.Activity
import android.content.ContentProviderClient
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rajeev.fooddelivery.R
import com.rajeev.fooddelivery.databinding.ActivitySignInBinding
import com.rajeev.fooddelivery.modal.UserModal

class SignInActivity : AppCompatActivity() {

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var userName: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var googleSignClient: GoogleSignInClient
    private val binding: ActivitySignInBinding by lazy {
        ActivitySignInBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val googleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(
                getString(
                    R.string.default_web_client_id
                )
            ).requestEmail().build()
        //initializing
        auth = Firebase.auth
        database = Firebase.database.reference
        googleSignClient = GoogleSignIn.getClient(this, googleSignInOptions)

        binding.btnCreatAccount.setOnClickListener() {
            userName = binding.userName.text.toString()
            email = binding.email.text.toString().trim()
            password = binding.password.text.toString().trim()

            if (userName.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Fill all details", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(email, password)
            }
        }

        binding.btnGoogle.setOnClickListener {
            val signIntent = googleSignClient.signInIntent
            launcher.launch(signIntent)

        }

        binding.txtAlreadyHaveAccount.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    //launcher for google signIn
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                if (task.isSuccessful) {
                    val account: GoogleSignInAccount? = task.result
                    val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                    auth.signInWithCredential(credential).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "SignIn Successful 😊", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "SignIn Failed 😢", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "SignIn Failed 😢", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "SignIn Failed 😢", Toast.LENGTH_SHORT).show()
            }
        }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show()
                saveUserData()
                startActivity((Intent(this, LoginActivity::class.java)))
                finish()
            } else {
                Log.d("Account", "createAcoount: Failure", task.exception)
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUserData() {
        userName = binding.userName.text.toString()
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()
        val user = UserModal(userName, email, password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        //save data to firebase database
        database.child("user").child(userId).setValue(user)
    }
}