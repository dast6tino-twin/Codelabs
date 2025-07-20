package ru.dast_6_tino.diceroller

import androidx.annotation.DrawableRes
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable

@Stable
sealed interface Dice {

    @get:DrawableRes val image: Int
    @get:StringRes val contentDescription: Int

    data object One : Dice {

        @field:DrawableRes override val image: Int = R.drawable.dice_1
        @field:StringRes override val contentDescription: Int = R.string.one

    }

    data object Two : Dice {

        @DrawableRes override val image: Int = R.drawable.dice_2
        @StringRes override val contentDescription: Int = R.string.two

    }

    data object Three : Dice {

        @field:DrawableRes override val image: Int = R.drawable.dice_3
        @field:StringRes override val contentDescription: Int = R.string.three

    }

    data object Four : Dice {

        @field:DrawableRes override val image: Int = R.drawable.dice_4
        @field:StringRes override val contentDescription: Int = R.string.four

    }

    data object Five : Dice {

        @field:DrawableRes override val image: Int = R.drawable.dice_5
        @field:StringRes override val contentDescription: Int = R.string.five

    }

    data object Six : Dice {

        @field:DrawableRes override val image: Int = R.drawable.dice_6
        @field:StringRes override val contentDescription: Int = R.string.six

    }

    companion object {

        fun getDiceByValue(@IntRange(1, 6) value: Int): Dice = when (value) {
            1 -> One
            2 -> Two
            3 -> Three
            4 -> Four
            5 -> Five
            6 -> Six
            else -> throw IllegalArgumentException("Unexpected value = $value")
        }

    }

}
