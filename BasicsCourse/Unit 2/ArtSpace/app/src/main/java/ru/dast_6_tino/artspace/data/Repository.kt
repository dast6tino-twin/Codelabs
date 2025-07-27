package ru.dast_6_tino.artspace.data

import ru.dast_6_tino.artspace.R

object Repository {

    fun getArts(): List<Art> = listOf(
        Art(
            image = R.drawable.art_a_ball_game_before_a_country_palace,
            name = "A Ball Game Before a Country Palace",
            author = "Adriaen van de Venne",
            year = "about 1614",
        ),
        Art(
            image = R.drawable.art_a_calm_at_a_mediterranean_port,
            name = "A Calm at a Mediterranean Port",
            author = "Claude-Joseph Vernet",
            year = "1770",
        ),
        Art(
            image = R.drawable.art_a_formal_garden,
            name = "A Formal Garden",
            author = "Johannes Janson",
            year = "1766",
        ),
        Art(
            image = R.drawable.art_achilles_among_the_daughters_of_lycomedes,
            name = "Achilles among the Daughters of Lycomedes",
            author = "Pietro Paolini",
            year = "about 1625–1630",
        ),
        Art(
            image = R.drawable.art_adoration_of_the_magi,
            name = "Adoration of the Magi",
            author = "Defendente Ferrari",
            year = "about 1520",
        ),
        Art(
            image = R.drawable.art_adoration_of_the_shepherds,
            name = "Adoration of the Shepherds",
            author = "Nicolaes Maes",
            year = "about 1660",
        ),
        Art(
            image = R.drawable.art_a_young_scholar_and_his_tutor,
            name = "A Young Scholar and his Tutor",
            author = "Workshop of Rembrandt Harmensz. van Rijn",
            year = "about 1629–1630",
        ),
        Art(
            image = R.drawable.art_euclid,
            name = "Euclid",
            author = "Jusepe de Ribera",
            year = "about 1630–1635",
        ),
        Art(
            image = R.drawable.art_interior_with_an_easel,
            name = "Interior with an Easel, Bredgade 25",
            author = "Vilhelm Hammershøi",
            year = "1912",
        ),
        Art(
            image = R.drawable.art_modern_rome_campo_vaccino,
            name = "Modern Rome - Campo Vaccino",
            author = "Joseph Mallord William Turner",
            year = "1839",
        ),
    )

}
