package edu.mailman.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import edu.mailman.a7minuteworkout.databinding.ActivityBmiBinding

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

        val roundedBMI = "%.1f".format(bmi).toFloat()
        when (roundedBMI) {
            in 0F .. 14.99F -> {
                // BMI is less than 15
                bmiLabel = "You are very severely underweight"
                bmiDescription = "You really need to eat more"
            }
            in 15F .. 15.99F -> {
                // BMI is between 15 and 16
                bmiLabel = "You are severely underweight"
                bmiDescription = "You really need to eat more"
            }
            in 16F .. 18.49F -> {
                // BMI is between 16 and 18.5
                bmiLabel = "You are underweight"
                bmiDescription = "You really need to eat more"
            }
            in 18.5F .. 24.99F -> {
                // BMI is between 18.5 and 25
                bmiLabel = "Normal"
                bmiDescription = "You are in good shape"
            }
            in 25F .. 29.99F -> {
                // BMI is between 25 and 30
                bmiLabel = "You are overweight"
                bmiDescription = "You really need to workout more"
            }
            in 30F .. 34.99F -> {
                // BMI is between 30 and 35
                bmiLabel = "You are moderately obese"
                bmiDescription = "You really need to workout more"
            }
            in 35F .. 39.99F -> {
                // BMI is between 35 and 40
                bmiLabel = "You are severely obese"
                bmiDescription = "This is a dangerous condition!"
            }
            else -> {
                // BMI is over 40
                bmiLabel = "You are very severely obese"
                bmiDescription = "This is a dangerous condition"
            }
        }

        val bmiValue = "%.1f".format(bmi)
        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun validateMetricUnits(): Boolean {
        return !(binding?.etMetricUnitWeight?.text.toString().isEmpty() ||
                binding?.etMetricUnitHeight?.text.toString().isEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}