package ru.dast_6_tino.lemonade

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Stable

@Stable
sealed interface LemonadeSteps {

    @get:DrawableRes val image: Int
    @get:StringRes val contentDescription: Int
    @get:StringRes val message: Int

    data object LemonTree : LemonadeSteps {

        @get:DrawableRes override val image: Int get() = R.drawable.lemon_tree
        @get:StringRes override val contentDescription: Int get() = R.string.lemon_tree_content_description
        @get:StringRes override val message: Int get() = R.string.tap_lemon_tree

    }

    data object Lemon : LemonadeSteps {

        @get:DrawableRes override val image: Int get() = R.drawable.lemon
        @get:StringRes override val contentDescription: Int get() = R.string.lemon_content_description
        @get:StringRes override val message: Int get() = R.string.keep_tap_lemon

    }

    data object FullGlass : LemonadeSteps {

        @get:DrawableRes override val image: Int get() = R.drawable.glass_full
        @get:StringRes override val contentDescription: Int get() = R.string.glass_of_lemonade_content_description
        @get:StringRes override val message: Int get() = R.string.tap_lemonade

    }

    data object EmptyGlass : LemonadeSteps {

        @get:DrawableRes override val image: Int get() = R.drawable.glass_empty
        @get:StringRes override val contentDescription: Int get() = R.string.empty_glass_content_description
        @get:StringRes override val message: Int get() = R.string.tap_empty_glass

    }

}
