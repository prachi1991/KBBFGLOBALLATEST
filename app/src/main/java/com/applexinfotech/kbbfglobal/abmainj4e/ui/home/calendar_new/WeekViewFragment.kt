package com.applexinfotech.kbbfglobal.abmainj4e.ui.home.calendar_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.applexinfotech.kbbfglobal.abmainj4e.base.BaseFragment
import com.applexinfotech.kbbfglobal.abmainj4e.ui.home.HomeViewModel
import com.applexinfotech.kbbfglobal.databinding.ActivityWeekCalendarBinding

class WeekViewFragment: BaseFragment() {

    private lateinit var binding: ActivityWeekCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityWeekCalendarBinding.inflate(inflater, container, false)
        binding.viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return binding.root
    }
}