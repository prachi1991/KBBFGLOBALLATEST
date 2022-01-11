package com.applexinfotech.kbbfglobal.abmainj4e.base

import com.applexinfotech.kbbfglobal.R
import com.applexinfotech.kbbfglobal.abmainj4e.ui.home.calendar_new.Flight
import java.time.YearMonth

private typealias Airport = Flight.Airport

fun generateFlights(): List<Flight> {
    val list = mutableListOf<Flight>()
    val currentMonth = YearMonth.now()

    val currentMonth17 = currentMonth.atDay(17)
    list.add(Flight(currentMonth17.atTime(14, 0), Airport("Lagos", "LOS"), Airport("Abuja", "ABV"), R.color.black))
    list.add(Flight(currentMonth17.atTime(21, 30), Airport("Enugu", "ENU"), Airport("Owerri", "QOW"), R.color.grey))

    val currentMonth22 = currentMonth.atDay(22)
    list.add(Flight(currentMonth22.atTime(13, 20), Airport("Ibadan", "IBA"), Airport("Benin", "BNI"), R.color.colorPrimary))
    list.add(Flight(currentMonth22.atTime(17, 40), Airport("Sokoto", "SKO"), Airport("Ilorin", "ILR"), R.color.red))

    list.add(
        Flight(
            currentMonth.atDay(3).atTime(20, 0),
            Airport("Makurdi", "MDI"),
            Airport("Calabar", "CBQ"),
            R.color.teal_700
        )
    )

    list.add(
        Flight(
            currentMonth.atDay(12).atTime(18, 15),
            Airport("Kaduna", "KAD"),
            Airport("Jos", "JOS"),
            R.color.colorPrimary
        )
    )

    val nextMonth13 = currentMonth.plusMonths(1).atDay(13)
    list.add(Flight(nextMonth13.atTime(7, 30), Airport("Kano", "KAN"), Airport("Akure", "AKR"), R.color.red))
    list.add(Flight(nextMonth13.atTime(10, 50), Airport("Minna", "MXJ"), Airport("Zaria", "ZAR"), R.color.green))

    list.add(
        Flight(
            currentMonth.minusMonths(1).atDay(9).atTime(20, 15),
            Airport("Asaba", "ABB"),
            Airport("Port Harcourt", "PHC"),
            R.color.orange
        )
    )

    return list
}
