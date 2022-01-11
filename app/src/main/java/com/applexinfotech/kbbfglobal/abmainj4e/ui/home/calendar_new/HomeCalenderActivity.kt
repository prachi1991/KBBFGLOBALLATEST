package com.applexinfotech.kbbfglobal.abmainj4e.ui.home.calendar_new

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.applexinfotech.kbbfglobal.R
import com.applexinfotech.kbbfglobal.abmainj4e.base.BaseActivity
import com.applexinfotech.kbbfglobal.abmainj4e.ui.home.HomeViewModel
import com.applexinfotech.kbbfglobal.databinding.ActivityCalenderBinding
import kotlinx.android.synthetic.main.toolbar_calendar.view.*


class HomeCalenderActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityCalenderBinding
    private lateinit var llDay: LinearLayoutCompat
    private lateinit var llWeek: LinearLayoutCompat
    private lateinit var llMonth: LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calender)
        binding.viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        setToolbar()
    }


    private fun setToolbar() {
        binding.toolbar.ivBack.setOnClickListener { onBackPressed() }
        binding.toolbar.ivMonth.setOnClickListener {
            openDialog()
        }
        val fragment = MonthViewFragment()
        replaceFragment(fragment, R.id.frameCalendar, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun openDialog() {
        val dialog = Dialog(this, R.style.myfullscreen)
        dialog.setContentView(R.layout.layout_floating_cal_options)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        llDay = dialog.findViewById(R.id.llDay)
        llWeek = dialog.findViewById(R.id.llweek)
        llMonth = dialog.findViewById(R.id.llMonth)

        llDay.setOnClickListener {
            val fragment = DayViewFragment()
            replaceFragment(fragment, R.id.frameMain, false)
            binding.toolbar.ivMonth.setImageDrawable(resources.getDrawable(R.drawable.ic_day_white))
            dialog.dismiss()
        }
        llWeek.setOnClickListener {
            val fragment = WeekViewFragment()
            replaceFragment(fragment, R.id.frameMain, false)
            binding.toolbar.ivMonth.setImageDrawable(resources.getDrawable(R.drawable.ic_weekly_white))
            dialog.dismiss()
        }

        llMonth.setOnClickListener {
            val fragment = MonthViewFragment()
            replaceFragment(fragment, R.id.frameMain, false)
            binding.toolbar.ivMonth.setImageDrawable(resources.getDrawable(R.drawable.cal_month))
            dialog.dismiss()
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {

//        if (position == 0) {
//            val intent = Intent(this, ApplyLeaveActivity::class.java)
//            startActivity(intent)
//        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //TODO("Not yet implemented")
    }


}