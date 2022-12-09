package com.example.complanschool

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.complanschool.ui.dashboard.FragmentStatusFasilitas
import com.example.complanschool.ui.dashboard.FragmentStatusPerson

class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentStatusPerson()
            1 -> fragment = FragmentStatusFasilitas()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }



}