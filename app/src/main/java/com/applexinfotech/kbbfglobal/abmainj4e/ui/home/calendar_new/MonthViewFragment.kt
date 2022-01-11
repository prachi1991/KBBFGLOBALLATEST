package com.applexinfotech.kbbfglobal.abmainj4e.ui.home.calendar_new

import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.children
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applexinfotech.kbbfglobal.R
import com.applexinfotech.kbbfglobal.abmainj4e.base.*
import com.applexinfotech.kbbfglobal.abmainj4e.base.layoutInflater
import com.applexinfotech.kbbfglobal.databinding.CalendarDayBinding
import com.applexinfotech.kbbfglobal.databinding.Example5CalendarHeaderBinding
import com.applexinfotech.kbbfglobal.databinding.Example5EventItemViewBinding
import com.applexinfotech.kbbfglobal.databinding.Example5FragmentBinding
import com.kizitonwose.calendarview.CalendarView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.next
import com.kizitonwose.calendarview.utils.previous
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

data class Flight(
    val time: LocalDateTime,
    val departure: Airport,
    val destination: Airport,
    @ColorRes val color: Int
) {
    data class Airport(val city: String, val code: String)
}

class Example5FlightsAdapter :
    RecyclerView.Adapter<Example5FlightsAdapter.Example5FlightsViewHolder>() {

    val flights = mutableListOf<Flight>()

    private val formatter = DateTimeFormatter.ofPattern("EEE'\n'dd MMM'\n'HH:mm")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Example5FlightsViewHolder {
        return Example5FlightsViewHolder(
            Example5EventItemViewBinding.inflate(parent.context.layoutInflater,parent,false)

        )
    }

    override fun onBindViewHolder(viewHolder: Example5FlightsViewHolder, position: Int) {
        viewHolder.bind(flights[position])
    }

    override fun getItemCount(): Int = flights.size

    inner class Example5FlightsViewHolder(val binding: Example5EventItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flight: Flight) {
            binding.itemFlightDateText.apply {
                text = formatter.format(flight.time)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setBackgroundColor(itemView.context.getColor(flight.color))
                }
            }

            binding.itemDepartureAirportCodeText.text = flight.departure.code
            binding.itemDepartureAirportCityText.text = flight.departure.city

            binding.itemDestinationAirportCodeText.text = flight.destination.code
            binding.itemDestinationAirportCityText.text = flight.destination.city
        }
    }
}

class MonthViewFragment : BaseFragmentCalendar(R.layout.example_5_fragment), HasToolbar {

    override val toolbar: Toolbar?
        get() = null

    override val titleRes: Int = R.string.app_name

    private var selectedDate: LocalDate? = null
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")

    private val flightsAdapter = Example5FlightsAdapter()
    private val flights = generateFlights().groupBy { it.time.toLocalDate() }

    private lateinit var binding: Example5FragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Example5FragmentBinding.bind(view)

        binding.exFiveRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = flightsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
        flightsAdapter.notifyDataSetChanged()

        val daysOfWeek = daysOfWeekFromLocale()

        val currentMonth = YearMonth.now()
        binding.exFiveCalendar.setup(
            currentMonth.minusMonths(10),
            currentMonth.plusMonths(10),
            daysOfWeek.first()
        )
        binding.exFiveCalendar.scrollToMonth(currentMonth)
        binding.exFiveCalendar.daySize = CalendarView.sizeAutoWidth(248)

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = CalendarDayBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        if (selectedDate != day.date) {
                            val oldDate = selectedDate
                            selectedDate = day.date
                            val binding = this@MonthViewFragment.binding
                            binding.exFiveCalendar.notifyDateChanged(day.date)
                            oldDate?.let { binding.exFiveCalendar.notifyDateChanged(it) }
                            updateAdapterForDate(day.date)
                        }
                    }
                }
            }
        }
        binding.exFiveCalendar.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            @RequiresApi(Build.VERSION_CODES.M)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.binding.exFiveDayText
                val layout = container.binding.exFiveDayLayout
                textView.text = day.date.dayOfMonth.toString()

                val flightTopView = container.binding.exFiveDayFlightTop
                val flightBottomView = container.binding.exFiveDayFlightBottom
                val orangeView = container.binding.exFiveDayOrangeBottom
                //val exFiveDayFlight = container.binding.exFiveDayFlight

                flightTopView.background = null
                flightBottomView.background = null
                orangeView.background = null

//                flightTopView.setBackgroundColor(view.context.getColor(R.color.colorPrimary))
//                flightBottomView.setBackgroundColor(view.context.getColor(R.color.cal_skyblue))
//                orangeView.setBackgroundColor(view.context.getColor(R.color.light_mango))
//                exFiveDayFlight.background = null

                if (day.owner == DayOwner.THIS_MONTH) {
                    textView.setTextColorRes(R.color.black)

//                    layout.setBackgroundResource(if (selectedDate == day.date) R.drawable.date_circle else 0)

                    textView.setBackgroundResource(if (selectedDate == day.date) R.drawable.date_circle else 0)

                    if (selectedDate == day.date) {
                        textView.setTextColorRes(R.color.white)
                    }
                    // textView.setPadding(0,0,0,20)

                    val flights = flights[day.date]
                    if (flights != null) {
                        if (flights.count() == 1) {
//                            flightBottomView.setBackgroundColor(view.context.getColor(R.color.colorPrimary))
                        } else {
                            flightTopView.setBackgroundColor(view.context.getColor(R.color.colorPrimary))
                            flightBottomView.setBackgroundColor(view.context.getColor(R.color.sky_blue_light))
                            orangeView.setBackgroundColor(view.context.getColor(R.color.light_mango))
                            //exFiveDayFlight.setBackgroundColor(view.context.getColor(R.color.colorPrimary))
                        }
                    }
                } else {
                    textView.setTextColorRes(R.color.grey)
                     layout.background = null
                }
            }
        }

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = Example5CalendarHeaderBinding.bind(view).legendLayout
        }

        // header days layout(Sun, mon, tues...)
        binding.exFiveCalendar.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun create(view: View) = MonthViewContainer(view)
                override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                    // Setup each header day text if we have not done that already.
                    if (container.legendLayout.tag == null) {
                        container.legendLayout.tag = month.yearMonth
                        container.legendLayout.children.map { it as TextView }
                            .forEachIndexed { index, tv ->
                                tv.text = daysOfWeek[index].getDisplayName(
                                    TextStyle.SHORT,
                                    Locale.ENGLISH
                                )
                                tv.setTextColorRes(R.color.black)
                                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                            }
                        month.yearMonth
                    }
                }
            }

        binding.exFiveCalendar.monthScrollListener = { month ->
            val title = "${monthTitleFormatter.format(month.yearMonth)} ${month.yearMonth.year}"
            binding.exFiveMonthYearText.text = title

            selectedDate?.let {
                // Clear selection if we scroll to a new month.
                selectedDate = null
                binding.exFiveCalendar.notifyDateChanged(it)
                updateAdapterForDate(null)
            }
        }

        binding.exFiveNextMonthImage.setOnClickListener {
            binding.exFiveCalendar.findFirstVisibleMonth()?.let {
                binding.exFiveCalendar.smoothScrollToMonth(it.yearMonth.next)
            }
        }

        binding.exFivePreviousMonthImage.setOnClickListener {
            binding.exFiveCalendar.findFirstVisibleMonth()?.let {
                binding.exFiveCalendar.smoothScrollToMonth(it.yearMonth.previous)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requireActivity().window.statusBarColor =
                    requireContext().getColor(R.color.colorPrimary)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requireActivity().window.statusBarColor =
                requireContext().getColor(R.color.colorPrimaryDark)
        }
    }

    private fun updateAdapterForDate(date: LocalDate?) {
        flightsAdapter.flights.clear()
        flightsAdapter.flights.addAll(flights[date].orEmpty())
        flightsAdapter.notifyDataSetChanged()
    }
}
