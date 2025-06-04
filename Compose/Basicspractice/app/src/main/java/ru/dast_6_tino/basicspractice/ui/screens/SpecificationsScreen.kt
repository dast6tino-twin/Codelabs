package ru.dast_6_tino.basicspractice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.basicspractice.R
import ru.dast_6_tino.basicspractice.ui.DarkLightPreviews
import ru.dast_6_tino.basicspractice.ui.theme.*

@Composable
fun SpecificationsScreen(modifier: Modifier = Modifier) = Column(
    modifier = modifier.fillMaxSize(),
) {
    val rowModifier = Modifier.weight(1F)
    val specificationModifier = Modifier
        .weight(1F)
        .fillMaxSize()

    Row(modifier = rowModifier) {
        Specification(
            title = stringResource(R.string.specifications_title_first),
            message = stringResource(R.string.specifications_message_first),
            backgroundColor = BlueChalk,
            modifier = specificationModifier,
        )
        Specification(
            title = stringResource(R.string.specifications_title_second),
            message = stringResource(R.string.specifications_message_second),
            backgroundColor = Melrose,
            modifier = specificationModifier,
        )
    }
    Row(modifier = rowModifier) {
        Specification(
            title = stringResource(R.string.specifications_title_third),
            message = stringResource(R.string.specifications_message_third),
            backgroundColor = Perfume,
            modifier = specificationModifier,
        )
        Specification(
            title = stringResource(R.string.specifications_title_forth),
            message = stringResource(R.string.specifications_message_forth),
            backgroundColor = BlueChalkSecond,
            modifier = specificationModifier,
        )
    }
}

@Composable
fun Specification(
    title: String,
    message: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) = Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
        .background(backgroundColor)
        .padding(16.dp),
) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 16.dp),
    )
    Text(text = message, textAlign = TextAlign.Justify)
}

@DarkLightPreviews
@Composable
fun SpecificationsScreenPreview() = BasicsPracticeTheme {
    SpecificationsScreen()
}

@DarkLightPreviews
@Composable
fun SpecificationPreview() = BasicsPracticeTheme {
    Specification(title = "Title", message = "Message", backgroundColor = Color.Magenta)
}
