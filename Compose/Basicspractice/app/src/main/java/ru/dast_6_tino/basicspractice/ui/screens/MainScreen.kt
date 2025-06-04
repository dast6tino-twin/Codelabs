package ru.dast_6_tino.basicspractice.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dast_6_tino.basicspractice.R
import ru.dast_6_tino.basicspractice.ui.DarkLightPreviews
import ru.dast_6_tino.basicspractice.ui.theme.BasicsPracticeTheme

@Composable
fun MainScreen(
    title: String,
    firstText: String,
    secondText: String,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier.fillMaxSize()) {
    Image(
        painter = painterResource(R.drawable.background_main_screen_title),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth(),
    )
    Text(
        text = title,
        fontSize = 24.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
    )
    Text(
        text = firstText,
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    )
    Text(
        text = secondText,
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
    )
}

@DarkLightPreviews
@Composable
fun MainScreenPreview() = BasicsPracticeTheme {
    MainScreen(
        title = "Title",
        firstText = "First text part",
        secondText = "Second text part",
    )
}
