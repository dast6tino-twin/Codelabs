package ru.dast_6_tino.courses.data

import ru.dast_6_tino.courses.R
import ru.dast_6_tino.courses.model.Topic

object DataSource {

    fun getTopics() = listOf(
        Topic(R.drawable.architecture, R.string.architecture, 58),
        Topic(R.drawable.automotive, R.string.automotive, 110),
        Topic(R.drawable.biology, R.string.biology, 78),
        Topic(R.drawable.business, R.string.business, 32),
        Topic(R.drawable.crafts, R.string.crafts, 121),
        Topic(R.drawable.culinary, R.string.culinary, 118),
        Topic(R.drawable.design, R.string.design, 423),
        Topic(R.drawable.drawing, R.string.drawing, 326),
        Topic(R.drawable.ecology, R.string.ecology, 98),
        Topic(R.drawable.engineering, R.string.engineering, 234),
        Topic(R.drawable.fashion, R.string.fashion, 92),
        Topic(R.drawable.film, R.string.film, 165),
        Topic(R.drawable.finance, R.string.finance, 522),
        Topic(R.drawable.gaming, R.string.gaming, 164),
        Topic(R.drawable.geology, R.string.geology, 421),
        Topic(R.drawable.history, R.string.history, 41),
        Topic(R.drawable.journalism, R.string.journalism, 35),
        Topic(R.drawable.law, R.string.law, 176),
        Topic(R.drawable.lifestyle, R.string.lifestyle, 305),
        Topic(R.drawable.music, R.string.music, 212),
        Topic(R.drawable.painting, R.string.painting, 172),
        Topic(R.drawable.photography, R.string.photography, 321),
        Topic(R.drawable.physics, R.string.physics, 461),
        Topic(R.drawable.tech, R.string.tech, 118),
    )

}
