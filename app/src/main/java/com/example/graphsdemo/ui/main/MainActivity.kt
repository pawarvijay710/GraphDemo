package com.example.graphsdemo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graphsdemo.data.model.credit.CreditCard
import com.example.graphsdemo.data.model.product.Product
import com.example.graphsdemo.databinding.ActivityMainBinding
import com.example.graphsdemo.ui.main.apdapter.ApiAdapter
import com.example.graphsdemo.ui.main.apdapter.CreditCardAdapter
import com.example.graphsdemo.ui.main.viewmodel.ApiViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.mindorks.framework.mvvm.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val productViewModel: ApiViewModel by viewModels()
    private lateinit var productAdapter: ApiAdapter
    private lateinit var barChart: BarChart
    private var mutableProductList: MutableList<Product> = ArrayList()
    private var mutableCreditList: MutableList<CreditCard> = ArrayList()

    private val creditCardViewModel: ApiViewModel by viewModels()
    private lateinit var creditAdapter: CreditCardAdapter

    private var flag = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        barChart = binding.barChart
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )

        initBarChart()

        if (flag) {
            setupProductUI()
        } else {
            setupCreditCardUI()
        }

        setupProductObserver()
        productViewModel.fetchData()

        setupCreditcCardObserver()
        creditCardViewModel.fetchCreditCard()

        binding.changeBtn.setOnClickListener {
            Log.d("MainActivity", "Clicked")
            if (flag) {
                flag = false
                setupCreditCardUI()

            } else {
                flag = true
                setupProductUI()
            }
        }
    }

    private fun initBarChart() {
        barChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = barChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        //remove right y-axis
        barChart.axisRight.isEnabled = false
        barChart.axisLeft.isEnabled = false

        //remove legend
        barChart.legend.isEnabled = false


        //remove description label
        barChart.description.isEnabled = false

        /*barChart.axisLeft.setDrawGridLines(false)
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.setDrawAxisLine(false)

        //remove right y-axis
        barChart.axisRight.isEnabled = false
        barChart.axisLeft.isEnabled = false

        //remove legend
        barChart.legend.isEnabled = false

        barChart.setDrawValueAboveBar(false)
        barChart.description.isEnabled = false
        barChart.xAxis.isEnabled = false

        //remove description label
        barChart.description.isEnabled = false
*/
        //add animation
        barChart.animateY(1500)

        // to draw label on xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = MyAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +90f
    }

    private fun setGraphData() {

        val entries: ArrayList<BarEntry> = ArrayList()

        //you can replace this data object with  your custom object
        for (i in mutableProductList.indices) {
            val data = mutableProductList[i]
            entries.add(BarEntry(i.toFloat(), data.netPrice.toFloat()))
        }


        val barDataSet = BarDataSet(entries, "")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)
        barChart.data = data

        //draw chart
        barChart.invalidate()
    }

    private fun setupCreditCardUI() {
        creditAdapter = CreditCardAdapter()
        creditAdapter.addData(mutableCreditList as ArrayList<CreditCard>)
        binding.recyclerView.adapter = creditAdapter
    }

    private fun setupCreditcCardObserver() {
        creditCardViewModel.setData(6, 0)
        creditCardViewModel.fetchCreditCard()
        creditCardViewModel.apiCreditCardData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { apiResponse -> mutableCreditList.addAll(apiResponse.data) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupProductUI() {
        productAdapter = ApiAdapter()
        productAdapter.addData(mutableProductList as ArrayList<Product>)
        binding.recyclerView.adapter = productAdapter
    }

    private fun setupProductObserver() {
        productViewModel.setData(5, 0)
        productViewModel.apiData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { apiResponse -> setProductData(apiResponse.data) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setProductData(apiResponse: List<Product>) {
        mutableProductList.addAll(apiResponse)
        setGraphData()
        productAdapter.notifyDataSetChanged()
    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            Log.d("Graph", "getAxisLabel: index $index")
            return if (index < mutableProductList.size) {
                mutableProductList[index].name
            } else {
                ""
            }
        }
    }
}