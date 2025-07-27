package ru.dast_6_tino.artspace.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.artspace.R
import ru.dast_6_tino.artspace.ui.DarkLightPreviews
import ru.dast_6_tino.artspace.ui.theme.ArtSpaceTheme

@Composable
fun ArtImage(
    @DrawableRes image: Int,
    imageDescription: String,
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier
        .shadow(elevation = 12.dp)
        .background(MaterialTheme.colorScheme.background),
) {
    val painter = painterResource(image)
    val imageRatio = painter.intrinsicSize.width / painter.intrinsicSize.height
    Image(
        painter = painter,
        contentDescription = imageDescription,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .aspectRatio(imageRatio)
            .align(Alignment.Center)
            .padding(24.dp)
            .border(width = 1.dp, MaterialTheme.colorScheme.outline),
    )
}

@DarkLightPreviews
@Composable
private fun ArtImagePreview() = ArtSpaceTheme {
    Surface(
        modifier = Modifier.size(1200.dp),
    ) {
        ArtImage(
            image = R.drawable.ic_launcher_foreground,
            imageDescription = "Content description",
            modifier = Modifier.padding(16.dp),
        )
    }
}
