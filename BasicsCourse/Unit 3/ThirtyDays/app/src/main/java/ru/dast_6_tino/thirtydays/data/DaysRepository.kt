package ru.dast_6_tino.thirtydays.data

import ru.dast_6_tino.thirtydays.R
import ru.dast_6_tino.thirtydays.model.Day

object DaysRepository {

    fun getDays(): List<Day> = listOf(
        Day(R.string.day_one_title, R.string.day_one_message, R.drawable.image_day_1),
        Day(R.string.day_two_title, R.string.day_two_message, R.drawable.image_day_2),
        Day(R.string.day_three_title, R.string.day_three_message, R.drawable.image_day_3),
        Day(R.string.day_four_title, R.string.day_four_message, R.drawable.image_day_4),
        Day(R.string.day_five_title, R.string.day_five_message, R.drawable.image_day_5),
        Day(R.string.day_six_title, R.string.day_six_message, R.drawable.image_day_6),
        Day(R.string.day_seven_title, R.string.day_seven_message, R.drawable.image_day_7),
        Day(R.string.day_eight_title, R.string.day_eight_message, R.drawable.image_day_8),
        Day(R.string.day_nine_title, R.string.day_nine_message, R.drawable.image_day_9),
        Day(R.string.day_ten_title, R.string.day_ten_message, R.drawable.image_day_10),
        Day(R.string.day_eleven_title, R.string.day_eleven_message, R.drawable.image_day_11),
        Day(R.string.day_twelve_title, R.string.day_twelve_message, R.drawable.image_day_12),
        Day(R.string.day_thirteen_title, R.string.day_thirteen_message, R.drawable.image_day_13),
        Day(R.string.day_fourteen_title, R.string.day_fourteen_message, R.drawable.image_day_14),
        Day(R.string.day_fifteen_title, R.string.day_fifteen_message, R.drawable.image_day_15),
        Day(R.string.day_sixteen_title, R.string.day_sixteen_message, R.drawable.image_day_16),
        Day(R.string.day_seventeen_title, R.string.day_seventeen_message, R.drawable.image_day_17),
        Day(R.string.day_eighteen_title, R.string.day_eighteen_message, R.drawable.image_day_18),
        Day(R.string.day_nineteen_title, R.string.day_nineteen_message, R.drawable.image_day_19),
        Day(R.string.day_twenty_title, R.string.day_twenty_message, R.drawable.image_day_20),
        Day(R.string.day_twenty_one_title, R.string.day_twenty_one_message, R.drawable.image_day_21),
        Day(R.string.day_twenty_two_title, R.string.day_twenty_two_message, R.drawable.image_day_22),
        Day(R.string.day_twenty_three_title, R.string.day_twenty_three_message, R.drawable.image_day_23),
        Day(R.string.day_twenty_four_title, R.string.day_twenty_four_message, R.drawable.image_day_24),
        Day(R.string.day_twenty_five_title, R.string.day_twenty_five_message, R.drawable.image_day_25),
        Day(R.string.day_twenty_six_title, R.string.day_twenty_six_message, R.drawable.image_day_26),
        Day(R.string.day_twenty_seven_title, R.string.day_twenty_seven_message, R.drawable.image_day_27),
        Day(R.string.day_twenty_eight_title, R.string.day_twenty_eight_message, R.drawable.image_day_28),
        Day(R.string.day_twenty_nine_title, R.string.day_twenty_nine_message, R.drawable.image_day_29),
        Day(R.string.day_thirty_title, R.string.day_thirty_message, R.drawable.image_day_30),
    )

}
