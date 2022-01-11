package com.applexinfotech.kbbfglobal.abmainj4e.ui.home.calendar_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.applexinfotech.kbbfglobal.abmainj4e.base.BaseFragment
import com.applexinfotech.kbbfglobal.abmainj4e.ui.home.HomeViewModel
import com.applexinfotech.kbbfglobal.databinding.FragmentDayViewBinding

class DayViewFragment : BaseFragment() {

    private lateinit var binding: FragmentDayViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDayViewBinding.inflate(inflater, container, false)
        binding.viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        setAdapter()
        return binding.root
    }


    private fun setAdapter() {
        val arrayList = ArrayList<String>()
        binding.rvDayView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvDayView.adapter =
            DayAdapter(requireContext(), arrayList, object : DayAdapter.ItemListener {
                override fun onItemClick(position: Int) {
//                    val fragment = AnnouncementDetailsFragment()
//                    fragment.show(supportFragmentManager, "readmore")
                }

            })
    }
}