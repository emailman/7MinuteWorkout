package edu.mailman.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import edu.mailman.a7minuteworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    private var binding: ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.tlbBmiActivity)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }

        binding?.tlbBmiActivity?.setOnClickListener {
            onBackPressed()
        }

        binding?.btnCalculateUnits?.setOnClickListener {
            if (validateMetricUnits()) {
                val heightValue: Float =
                    binding?.etMetricUnitHeight?.text.toString().toFloat() / 100
                val weightValue: Float =
                    binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = weightValue / (heightValue * heightValue)

                displayBMIResult(bmi)

            } else {
                Toast.makeText(this@BMIActivity, "Please enter valid value",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayBMIResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            // BMI is less than 15
            bmiLabel = "You are very severely underweight"
            bmiDescription = "You really need to eat more"
        }  else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            // BMI is between 15 and 16
            bmiLabel = "You are severely underweight"
            bmiDescription = "You really need to eat more"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0) {
            // BMI is between 16 and 18.5
            bmiLabel = "You are underweight"
            bmiDescription = "You really need to eat more"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            // BMI is between 18.5 and 25
            bmiLabel = "Normal"
            bmiDescription = "You are in good shape"
        }else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            // BMI is between 25 and 30
            bmiLabel = "You are overweight"
            bmiDescription = "You really need to workout more"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            // BMI is between 30 and 35
            bmiLabel = "You are moderately obese"
            bmiDescription = "You really need to workout more"
        }  else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0){
            // BMI is between 35 and 40
            bmiLabel = "You are severely obese"
            bmiDescription = "This is a dangerous condition!"
        } else {
            // BMI is over 40
            bmiLabel = "You are very severely obese"
            bmiDescription = "This is a dangerous condition"
        }

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,
            RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if (binding?.etMetricUnitWeight?.toString()!!.isEmpty()) {
            isValid = false
        } else if (binding?.etMetricUnitHeight?.toString()!!.isEmpty()) {
            isValid = false
        }
        return isValid
    }
}