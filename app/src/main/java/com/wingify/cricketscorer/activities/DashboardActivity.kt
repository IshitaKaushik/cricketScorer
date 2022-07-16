package com.wingify.cricketscorer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wingify.cricketscorer.R
import com.wingify.cricketscorer.adapters.DashboardAdapter

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboardAdapter: DashboardAdapter
    private lateinit var dashboardViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        dashboardAdapter = DashboardAdapter(this)
        dashboardViewPager = findViewById(R.id.viewpager)
        dashboardViewPager.adapter = dashboardAdapter
        val label = arrayOf("BOWLER STATUS", "BATSMAN STATUS")
        val tabs = findViewById<TabLayout>(R.id.tablayout)
        TabLayoutMediator(tabs, dashboardViewPager) { tab, position ->
            tab.text = label[position]
        }.attach()
    }
}