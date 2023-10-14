package com.example.travelapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.travelapp.PlanActivity.Companion.EXTRA_DATE
import com.example.travelapp.PlanActivity.Companion.EXTRA_DESTINATION
import com.example.travelapp.PlanActivity.Companion.EXTRA_FROM
import com.example.travelapp.PlanActivity.Companion.EXTRA_SELECTED_PACKAGES
import com.example.travelapp.databinding.ActivityDashboardBinding


class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)



        with(binding){
            btnPlan.setOnClickListener {
                navigateToPlanPage()
            }
            val date = intent.getStringExtra(EXTRA_DATE)
            val from = intent.getStringExtra(EXTRA_FROM)
            val destination = intent.getStringExtra(EXTRA_DESTINATION)
            val packages = intent.getStringArrayListExtra(EXTRA_SELECTED_PACKAGES)

            txtDate.text = " $date"
            txtFrom.text = "$from"
            txtDestination.text = " $destination"
            txtPaket.text = packages?.joinToString(separator = ", ") { it } ?: "No packages selected"
        }
    }
    private fun navigateToPlanPage() {
        val intent = Intent(this@DashboardActivity, PlanActivity::class.java)
        startActivity(intent)
    }
}