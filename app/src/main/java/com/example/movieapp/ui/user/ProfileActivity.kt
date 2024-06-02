package com.example.movieapp.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityProfileBinding
import com.example.movieapp.databinding.ActivityUserBinding
import com.example.movieapp.helper.DataStore
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var dataStore: DataStore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        dataStore = DataStore(this)

        binding.btnBack.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }

        observeUserData()
        binding.btnUpdate.setOnClickListener {
            updateUserData()
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }


    }

    private fun observeUserData() {
        lifecycleScope.launch {
            dataStore.username.collect{username ->
                binding.etUname.setText(username ?: "")
            }
        }

        lifecycleScope.launch {
            dataStore.fullName.collect{fullName ->
                binding.etFullName.setText(fullName ?: "")

            }
        }

        lifecycleScope.launch {
            dataStore.address.collect{ address ->
                binding.etAddress.setText(address ?: "")
            }
        }

        lifecycleScope.launch {
            dataStore.dob.collect{ dob ->
                binding.etDob.setText(dob ?: "")
            }
        }
    }

    private fun updateUserData() {
        val username = binding.etUname.text.toString()
        val fullName = binding.etFullName.text.toString()
        val address = binding.etAddress.text.toString()
        val dob = binding.etDob.text.toString()

        lifecycleScope.launch {
            dataStore.updateUserData(username, fullName, address, dob)
            Toast.makeText(this@ProfileActivity, "Profile updated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logout() {
        lifecycleScope.launch {
            dataStore.logout()
            Toast.makeText(this@ProfileActivity, "You've successfully logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@ProfileActivity, UserActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)

            finish()

        }
    }
}