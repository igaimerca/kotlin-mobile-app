package com.ampersand.ampersandapp.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ampersand.ampersandapp.R
import com.ampersand.ampersandapp.databinding.ActivityDetailsViewBinding
import com.ampersand.ampersandapp.model.DataModel
import com.ampersand.ampersandapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar!!.title = "DETAILS VIEW"
        binding.toolbar!!.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar!!.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val dataModelId = intent.getIntExtra("dataModelId", -1)
        fetchDetails(dataModelId)
    }

    private fun fetchDetails(id: Int) {
        binding.progressBar.visibility = View.VISIBLE
        binding.detailContainer.visibility = View.GONE

        RetrofitClient.apiService.getObjectDetails(id).enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                binding.progressBar.visibility = View.GONE
                binding.detailContainer.visibility = View.VISIBLE
                if (response.isSuccessful) {
                    response.body()?.let { dataModel ->
                        displayDetails(dataModel)
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                binding.detailContainer.visibility = View.VISIBLE
                showError()
            }
        })
    }

    private fun displayDetails(dataModel: DataModel) {
        val nameTextView = TextView(this).apply {
            text = "${dataModel.name}"
            textSize = 20f
            setTextColor(resources.getColor(R.color.text_color, null))
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
        binding.detailContainer.addView(nameTextView)

        dataModel.data?.forEach { (key, value) ->
            val detailTextView = TextView(this).apply {
                text = "$key: $value"
                textSize = 18f
                setTextColor(resources.getColor(R.color.text_color, null))
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
            binding.detailContainer.addView(detailTextView)
        }
    }

    private fun showError() {
        val errorTextView = TextView(this).apply {
            text = "Failed to load details"
            textSize = 18f
            setTextColor(resources.getColor(R.color.text_color, null))
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
        binding.detailContainer.addView(errorTextView)
    }
}
