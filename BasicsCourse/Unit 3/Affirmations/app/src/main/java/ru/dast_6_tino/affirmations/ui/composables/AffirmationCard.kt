package ru.dast_6_tino.affirmations.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.affirmations.R
import ru.dast_6_tino.affirmations.model.Affirmation
import ru.dast_6_tino.affirmations.ui.DarkLightPreviews
import ru.dast_6_tino.affirmations.ui.theme.AffirmationsTheme

@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier,
) = Card(modifier = modifier) {
    val message = stringResource(affirmation.stringResourceId)
    Image(
        painter = painterResource(affirmation.imageResourceId),
        contentDescription = message,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(194.dp),
    )
    Text(
        text = message,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(16.dp),
    )
}

@DarkLightPreviews
@Composable
private fun AffirmationCardPreview() = AffirmationsTheme {
    Surface {
        AffirmationCard(
            affirmation = Affirmation(R.string.affirmation1, R.drawable.image1),
        )
    }
}
