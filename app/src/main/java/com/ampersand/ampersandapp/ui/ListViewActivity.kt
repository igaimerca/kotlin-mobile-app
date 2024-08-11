package com.ampersand.ampersandapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ampersand.ampersandapp.R
import com.ampersand.ampersandapp.adapter.DataAdapter
import com.ampersand.ampersandapp.databinding.ActivityListViewBinding
import com.ampersand.ampersandapp.model.DataModel
import com.ampersand.ampersandapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListViewBinding
    private lateinit var dataAdapter: DataAdapter
    private var dataList: List<DataModel> = listOf()
    private var isShowingAll: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar!!.title = "LIST VIEW"
        binding.toolbar!!.setNavigationIcon(R.drawable.ic_back)
        binding.toolbar!!.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.viewMoreButton.setOnClickListener {
            toggleViewMore()
        }

        fetchData()
    }

    private fun fetchData() {
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        RetrofitClient.apiService.getObjects().enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        dataList = data
                        displayData()
                    }
                } else {
                    showError()
                }
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                showError()
            }
        })
    }

    private fun displayData() {
        val displayList = if (isShowingAll) dataList else dataList.take(5)
        dataAdapter = DataAdapter(displayList) { dataModel ->
            val intent = Intent(this@ListViewActivity, DetailsViewActivity::class.java)
            intent.putExtra("dataModelId", dataModel.id)
            startActivity(intent)
        }
        binding.recyclerView.adapter = dataAdapter
        binding.viewMoreButton.text = if (isShowingAll) "VIEW LESS" else "VIEW MORE"

        val dividerItemDecoration = DividerItemDecoration(binding.recyclerView.context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun toggleViewMore() {
        isShowingAll = !isShowingAll
        displayData()
    }

    private fun showError() {
        Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show()
    }
}
