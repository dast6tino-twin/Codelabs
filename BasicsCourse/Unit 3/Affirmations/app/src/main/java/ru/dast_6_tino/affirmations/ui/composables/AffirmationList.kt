package ru.dast_6_tino.affirmations.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.affirmations.R
import ru.dast_6_tino.affirmations.model.Affirmation
import ru.dast_6_tino.affirmations.ui.DarkLightPreviews
import ru.dast_6_tino.affirmations.ui.theme.AffirmationsTheme

@Composable
fun AffirmationList(
    affirmations: List<Affirmation>,
    modifier: Modifier = Modifier,
) = LazyColumn(modifier = modifier) {
    items(affirmations) { affirmation ->
        AffirmationCard(
            affirmation = affirmation,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@DarkLightPreviews
@Composable
fun AffirmationListPreview() = AffirmationsTheme {
    Surface {
        AffirmationList(
            affirmations = listOf(
                Affirmation(R.string.affirmation1, R.drawable.image1),
                Affirmation(R.string.affirmation1, R.drawable.image1),
                Affirmation(R.string.affirmation1, R.drawable.image1),
                Affirmation(R.string.affirmation1, R.drawable.image1),
            ),
        )
    }
}
