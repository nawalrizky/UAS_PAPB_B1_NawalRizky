package com.example.travelapp
import android.app.Activity
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
import com.example.travelapp.R
import com.example.travelapp.databinding.ActivityPlanBinding

class PlanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlanBinding
    private lateinit var originCities: Array<String>
    private lateinit var cityPrices: IntArray
    private lateinit var cityDistances: IntArray
    private lateinit var trainClasses: Array<String>
    private lateinit var trainClassPrices: IntArray
    private lateinit var selectedDate: String
    companion object {
        const val EXTRA_DATE = "extra_date"
        const val EXTRA_FROM = "extra_from"
        const val EXTRA_DESTINATION = "extra_destination"
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
            var selectedOriginCity: String? = null
            var selectedDestinationCity: String? = null
            var selectedTrainClass: String? = trainClasses[0] // Initialize to the first train class
            val datePicker = findViewById<DatePicker>(R.id.date_picker)
            datePicker.init(datePicker.year, datePicker.month, datePicker.dayOfMonth) { view, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            }

            btnBook.setOnClickListener {
                val intentToDashboardActivity =
                    Intent(
                        this@PlanActivity,
                        DashboardActivity::class.java
                    )
                intentToDashboardActivity.putExtra(EXTRA_DATE, selectedDate)
                intentToDashboardActivity.putExtra(EXTRA_FROM, selectedOriginCity)
                intentToDashboardActivity.putExtra(EXTRA_DESTINATION, selectedDestinationCity)
                startActivity(intentToDashboardActivity)
                finish()
            }



            val toggleButton1 = findViewById<ToggleButton>(R.id.toggleButton1)
            val toggleButton2 = findViewById<ToggleButton>(R.id.toggleButton2)
            val toggleButton3 = findViewById<ToggleButton>(R.id.toggleButton3)
            val toggleButton4 = findViewById<ToggleButton>(R.id.toggleButton4)
            val toggleButton5 = findViewById<ToggleButton>(R.id.toggleButton5)
            val toggleButton6 = findViewById<ToggleButton>(R.id.toggleButton6)
            val toggleButton7 = findViewById<ToggleButton>(R.id.toggleButton7)


            var additionalPrice = 0 //

            toggleButton1.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 10
                } else {
                    additionalPrice -= 10
                }

                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }

            toggleButton2.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                } else {
                    additionalPrice -= 20
                }

                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }

            toggleButton3.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                } else {
                    additionalPrice -= 20
                }

                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }

            toggleButton4.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                } else {
                    additionalPrice -= 20
                }

                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }
            toggleButton5.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                } else {
                    additionalPrice -= 20
                }

                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }
            toggleButton6.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                } else {
                    additionalPrice -= 20
                }

                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
            }
            toggleButton7.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    additionalPrice += 20
                } else {
                    additionalPrice -= 20
                }

                updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices, additionalPrice)
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

                    updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices,additionalPrice)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }

            spinnerTujuan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedDestinationCity = originCities[position]
                    updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices,additionalPrice)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Handle nothing selected if needed
                }
            }

            spinnerTrainClass.adapter = trainAdapter

            spinnerTrainClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedTrainClass = trainClasses[position]
                    updateTotalPrice(textTotalPrice, selectedOriginCity, selectedDestinationCity, selectedTrainClass, cityDistances, cityPrices, trainClassPrices,additionalPrice)
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
