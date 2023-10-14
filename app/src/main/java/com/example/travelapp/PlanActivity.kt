package com.example.travelapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.widget.AppCompatButton
import com.example.travelapp.databinding.ActivityPlanBinding

class PlanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlanBinding
    private lateinit var originCities: Array<String>
    private lateinit var cityPrices: IntArray
    private lateinit var cityDistances: IntArray
    private lateinit var trainClasses: Array<String>
    private lateinit var trainClassPrices: IntArray
    private lateinit var selectedDate: String
    private var selectedOriginCity: String? = null
    private var selectedDestinationCity: String? = null
    private var selectedTrainClass: String? = null
    private var additionalPrice = 0
    private var selectedPackages: MutableList<String> = mutableListOf()

    companion object {
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_FROM = "extra_from"
        const val EXTRA_DESTINATION = "extra_destination"
        const val EXTRA_SELECTED_PACKAGES = "extra_selected_packages"
        const val REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            originCities = resources.getStringArray(R.array.origin_cities)
            cityPrices = resources.getIntArray(R.array.city_prices)
            cityDistances = resources.getIntArray(R.array.city_distances)

            val adapter = ArrayAdapter(this@PlanActivity, android.R.layout.simple_spinner_item, originCities)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinnerAsal.adapter = adapter
            spinnerTujuan.adapter = adapter

            trainClasses = resources.getStringArray(R.array.train_classes)
            trainClassPrices = resources.getIntArray(R.array.train_class_prices)

            val trainAdapter = ArrayAdapter(this@PlanActivity, android.R.layout.simple_spinner_item, trainClasses)
            trainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            val datePicker = findViewById<DatePicker>(R.id.date_picker)
            selectedDate = "${datePicker.dayOfMonth}/${datePicker.month + 1}/${datePicker.year}"

            datePicker.init(datePicker.year, datePicker.month, datePicker.dayOfMonth) { view, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            }

            toggleButton1.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 10
                    selectedPackages.add("Plus Lunch")
                } else {
                    additionalPrice -= 10
                    selectedPackages.remove("Plus Lunch")
                }
                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }

            toggleButton2.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                    selectedPackages.add("Window Seat")
                } else {
                    additionalPrice -= 20
                    selectedPackages.remove("Window Seat")
                }
                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }
            toggleButton3.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                    selectedPackages.add("Panoramic Carriage")
                } else {
                    additionalPrice -= 20
                    selectedPackages.remove("Panoramic Carriage")
                }
                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }
            toggleButton4.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                    selectedPackages.add("Plus Dinner")
                } else {
                    additionalPrice -= 20
                    selectedPackages.remove("Plus Dinner")
                }
                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }
            toggleButton5.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                    selectedPackages.add("Plus Pillow")
                } else {
                    additionalPrice -= 20
                    selectedPackages.remove("Plus Pillow")
                }
                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }
            toggleButton6.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                    selectedPackages.add("Plus Blanket")
                } else {
                    additionalPrice -= 20
                    selectedPackages.remove("Plus Blanket")
                }
                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }
            toggleButton7.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                    selectedPackages.add("Extra Luggage")
                } else {
                    additionalPrice -= 20
                    selectedPackages.remove("Extra Luggage")
                }
                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }



            btnBook.setOnClickListener {
                val intentToDashboardActivity = Intent(
                    this@PlanActivity,
                    DashboardActivity::class.java
                )
                intentToDashboardActivity.putExtra(EXTRA_DATE, selectedDate)
                intentToDashboardActivity.putExtra(EXTRA_FROM, selectedOriginCity)
                intentToDashboardActivity.putExtra(EXTRA_DESTINATION, selectedDestinationCity)
                intentToDashboardActivity.putStringArrayListExtra(EXTRA_SELECTED_PACKAGES, ArrayList(selectedPackages))
                startActivity(intentToDashboardActivity)
                finish()
            }


            spinnerAsal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedOriginCity = originCities[position]

                    val destinationCitiesList = originCities.toMutableList()
                    destinationCitiesList.remove(selectedOriginCity)
                    val destinationCities = destinationCitiesList.toTypedArray()

                    val adapterTujuan = ArrayAdapter(this@PlanActivity, android.R.layout.simple_spinner_item, destinationCities)
                    adapterTujuan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinnerTujuan.adapter = adapterTujuan

                    updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }

            spinnerTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedDestinationCity = spinnerTujuan.selectedItem as String
                    updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }

            spinnerTrainClass.adapter = trainAdapter

            spinnerTrainClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedTrainClass = trainClasses[position]
                    updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }
        }
    }

    private fun updateTotalPrice(
        textTotalPrice: TextView,
        originCity: String?,
        destinationCity: String?,
        selectedTrainClass: String?,
        cityDistances: IntArray,
        cityPrices: IntArray,
        trainClassPrices: IntArray,
        additionalPrice: Int
    ) {
        if (originCity != null && destinationCity != null && selectedTrainClass != null) {
            val originCityIndex = originCities.indexOf(originCity)
            val destinationCityIndex = originCities.indexOf(destinationCity)
            val trainClassIndex = trainClasses.indexOf(selectedTrainClass)

            if (originCityIndex >= 0 && destinationCityIndex >= 0 && trainClassIndex >= 0) {
                val distance = cityDistances[destinationCityIndex] - cityDistances[originCityIndex]
                val cityPrice = cityPrices[originCityIndex] + cityPrices[destinationCityIndex]
                val trainPrice = trainClassPrices[trainClassIndex]
                val totalPrice = (distance * cityPrice) + trainPrice + additionalPrice

                textTotalPrice.text = "Rp. $totalPrice"
            }
        } else {
            textTotalPrice.text = "" // Clear the total price if any Spinner is not selected.
        }
    }
}