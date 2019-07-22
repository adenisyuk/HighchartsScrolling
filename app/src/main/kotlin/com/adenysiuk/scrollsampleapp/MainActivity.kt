package com.adenysiuk.scrollsampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.highsoft.highcharts.common.hichartsclasses.HIArea
import com.highsoft.highcharts.common.hichartsclasses.HIChart
import com.highsoft.highcharts.common.hichartsclasses.HIExporting
import com.highsoft.highcharts.common.hichartsclasses.HIOptions
import com.highsoft.highcharts.core.HIChartView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<HIChartView>(R.id.chart).options = createConfig()
    }

    private fun createConfig(): HIOptions {
        return HIOptions().apply {
            series = arrayListOf(HIArea().apply {
                data = java.util.ArrayList(generateChartData())
            })

            chart = HIChart().apply {
                panning = true
                zoomType = "x"
                exporting = HIExporting().apply {
                    enabled = false
                }
            }
        }
    }

    private fun generateChartData(): List<Array<Any>> {
        val now = System.currentTimeMillis()
        val timeStampStep = TimeUnit.MINUTES.toMillis(1)
        return List(60 * 4) { index ->
            arrayOf(now + timeStampStep * index, Math.random())
        }
    }

}