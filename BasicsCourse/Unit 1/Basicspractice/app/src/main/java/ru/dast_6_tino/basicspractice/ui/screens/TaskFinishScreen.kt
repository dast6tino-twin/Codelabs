package ru.dast_6_tino.basicspractice.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dast_6_tino.basicspractice.R
import ru.dast_6_tino.basicspractice.ui.DarkLightPreviews
import ru.dast_6_tino.basicspractice.ui.theme.BasicsPracticeTheme

@Composable
fun TaskFinishScreen(modifier: Modifier = Modifier) = Column(
    modifier = modifier
        .fillMaxSize()
        .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Image(
        painter = painterResource(R.drawable.ic_complete_circle),
        contentDescription = null,
    )
    Text(
        text = stringResource(R.string.all_tasks_completed),
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
    )
    Text(
        text = stringResource(R.string.nice_work),
        fontSize = 16.sp,
    )
}

@DarkLightPreviews
@Composable
fun TaskFinishScreenPreview() = BasicsPracticeTheme {
    TaskFinishScreen()
}
