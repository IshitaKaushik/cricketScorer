package com.wingify.cricketscorer.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wingify.cricketscorer.fragments.BatsmanFragment
import com.wingify.cricketscorer.fragments.BowlerFragment

class DashboardAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            BowlerFragment()
        } else {
            BatsmanFragment()
        }
    }
}