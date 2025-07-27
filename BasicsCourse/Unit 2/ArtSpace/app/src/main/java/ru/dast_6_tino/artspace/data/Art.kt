package ru.dast_6_tino.artspace.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import ru.dast_6_tino.artspace.R

@Immutable
data class Art(
    @get:DrawableRes val image: Int,
    val name: String,
    val author: String,
    val year: String,
    @get:StringRes val copyright: Int = R.string.getty_search_gateway_copyrights,
) {

    companion object {

        val default = Art(
            image = R.drawable.ic_launcher_foreground,
            name = "Art name",
            author = "Art author",
            year = "Year",
        )

    }

}
