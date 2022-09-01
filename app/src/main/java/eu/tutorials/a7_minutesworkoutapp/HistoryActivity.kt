package eu.tutorials.a7_minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import eu.tutorials.a7_minutesworkoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarHistoryActivity)

        val actionbar = supportActionBar//actionbar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true) //set back button
            actionbar.title = "HISTORY"
            
        // Setting a title in the action bar.
        }

        binding?.toolbarHistoryActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        val HisyDao = (application as HistoryApp).db.historyDao()
        getAllCompletedDates(HisyDao)

    }

    private fun getAllCompletedDates(historyDao: HistoryDao){


        lifecycleScope.launch {
            historyDao.fetchAllDates().collect { allCompletedDatalist->
                if(allCompletedDatalist.isNotEmpty()){
                    binding?.tvHistory?.visibility = View.VISIBLE
                    binding?.rvHistoryStatus?.visibility = View.VISIBLE
                    binding?.tvNoRecordsAvailable?.visibility = View.GONE

                    binding?.rvHistoryStatus?.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    val dates = ArrayList<String>()
                    for (date in allCompletedDatalist){
                        dates.add(date.date)
                    }
                    val historyAdapter = historyAdapter(dates)
                    binding?.rvHistoryStatus?.adapter = historyAdapter
                }else{
                    binding?.tvHistory?.visibility = View.GONE
                    binding?.rvHistoryStatus?.visibility = View.GONE
                    binding?.tvNoRecordsAvailable?.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}