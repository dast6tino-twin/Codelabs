package ru.dast_6_tino.superheroes.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.superheroes.R
import ru.dast_6_tino.superheroes.model.Hero
import ru.dast_6_tino.superheroes.ui.DarkLightPreviews
import ru.dast_6_tino.superheroes.ui.theme.SuperheroesTheme

@Composable
fun HeroCard(
    hero: Hero,
    modifier: Modifier = Modifier,
) = Card(
    shape = MaterialTheme.shapes.medium,
    elevation = CardDefaults.cardElevation(2.dp),
    modifier = modifier,
) {
    val paddingMedium = dimensionResource(R.dimen.padding_medium)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(paddingMedium),
    ) {
        Column(
            modifier = Modifier.weight(1f),
        ) {
            Text(text = stringResource(hero.nameRes), style = MaterialTheme.typography.displaySmall)
            Text(text = stringResource(hero.descriptionRes), style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(Modifier.width(paddingMedium))
        Image(
            painter = painterResource(hero.imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(dimensionResource(R.dimen.image_size))
                .clip(MaterialTheme.shapes.small),
        )
    }
}

@DarkLightPreviews
@Composable
private fun HeroCardPreview() = SuperheroesTheme {
    Surface {
        HeroCard(
            hero = Hero(
                nameRes = R.string.hero1,
                descriptionRes = R.string.description1,
                imageRes = R.drawable.android_superhero1,
            ),
            modifier = Modifier.padding(16.dp),
        )
    }
}