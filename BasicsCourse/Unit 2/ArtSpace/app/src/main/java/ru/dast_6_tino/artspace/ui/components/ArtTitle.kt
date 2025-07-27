package ru.dast_6_tino.artspace.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.artspace.ui.DarkLightPreviews
import ru.dast_6_tino.artspace.ui.theme.ArtSpaceTheme

@Composable
fun ArtTitle(
    name: String,
    author: String,
    year: String,
    copyright: String,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .padding(16.dp),
) {
    Text(name, style = MaterialTheme.typography.titleLarge)
    val description = buildAnnotatedString {
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(author) }
        append(" ($year)")
    }
    Text(description)
    Spacer(Modifier.height(8.dp))
    Text(copyright, style = MaterialTheme.typography.bodySmall)
}

@DarkLightPreviews
@Composable
private fun ArtTitlePreview() = ArtSpaceTheme {
    Surface {
        ArtTitle("Name", "Author name", "2015", "Copyright")
    }
}
