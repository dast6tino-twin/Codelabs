package ru.dast_6_tino.superheroes.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ru.dast_6_tino.superheroes.R
import ru.dast_6_tino.superheroes.data.HeroesRepository
import ru.dast_6_tino.superheroes.model.Hero
import ru.dast_6_tino.superheroes.ui.DarkLightPreviews
import ru.dast_6_tino.superheroes.ui.composables.HeroCard
import ru.dast_6_tino.superheroes.ui.composables.HeroesAppBar
import ru.dast_6_tino.superheroes.ui.theme.SuperheroesTheme

@DarkLightPreviews
@Composable
fun HeroesScreen(
    heroes: List<Hero> = HeroesRepository.getHeroes(),
) = SuperheroesTheme {
    Scaffold(
        topBar = {
            HeroesAppBar()
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        val paddingMedium = dimensionResource(R.dimen.padding_medium)
        LazyColumn(
            contentPadding = PaddingValues(paddingMedium),
            modifier = Modifier.padding(innerPadding),
        ) {
            items(heroes) { hero ->
                HeroCard(hero, Modifier.padding(bottom = paddingMedium))
            }
        }
    }
}
