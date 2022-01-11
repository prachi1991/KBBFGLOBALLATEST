package com.applexinfotech.kbbfglobal.abmainj4e.ui.home.calendar_new

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.applexinfotech.kbbfglobal.R


class CustomAdapter(applicationContext: Context, flags: IntArray, countryNames: Array<String>) :
    BaseAdapter() {
    var context: Context
    var flags: IntArray
    var countryNames: Array<String>
    var inflter: LayoutInflater
    override fun getCount(): Int {
        return flags.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

//    override fun getView(i: Int, view: View, viewGroup: ViewGroup?): View {
//        var view: View = view
//        view = inflter.inflate(R.layout.layout_floating_cal_options, null)
//        val icon: AppCompatImageView = view.findViewById(R.id.ivDay)
//        val names:AppCompatTextView = view.findViewById(R.id.tvDay)
//        icon.setImageResource(flags[i])
//        names.text = countryNames[i]
//        return view
//    }

    override fun getView(i: Int, p1: View?, p2: ViewGroup?): View {
        val view = inflter.inflate(R.layout.layout_floating_cal_options, null)
        val icon: AppCompatImageView = view.findViewById(R.id.ivDay)
        val names: AppCompatTextView = view.findViewById(R.id.tvDay)
        icon.setImageResource(flags[i])
        names.text = countryNames[i]
        return view
    }

    init {
        context = applicationContext
        this.flags = flags
        this.countryNames = countryNames
        inflter = LayoutInflater.from(applicationContext)
    }
}