package edu.mailman.a7minuteworkout

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import edu.mailman.a7minuteworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity  : AppCompatActivity() {
    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)

        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding?.toolbarFinishActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener {
            finish()
        }

        val dao = (application as WorkoutApp).db.historyDAO()
        addDateToDatabase(dao)

    }
    private fun addDateToDatabase(historyDAO: HistoryDAO) {
        val cal = Calendar.getInstance()
        val dateTime = cal.time
        Log.i("Date: ", "" + dateTime)

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.i("Formatted Date: ", "" + date)


        lifecycleScope.launch {
            historyDAO.insert(HistoryEntity(date))
        }
    }
}