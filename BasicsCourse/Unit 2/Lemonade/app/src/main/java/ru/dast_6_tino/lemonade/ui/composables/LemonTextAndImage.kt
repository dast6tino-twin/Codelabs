package ru.dast_6_tino.lemonade.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.lemonade.LemonadeSteps
import ru.dast_6_tino.lemonade.ui.DarkLightPreviews
import ru.dast_6_tino.lemonade.ui.theme.LemonadeTheme
import ru.dast_6_tino.lemonade.ui.theme.Viking

@Composable
fun LemonTextAndImage(
    step: LemonadeSteps,
    modifier: Modifier = Modifier,
) = Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = modifier,
) {
    val imageShape = AbsoluteRoundedCornerShape(12.dp)
    Image(
        painter = painterResource(step.image),
        contentDescription = stringResource(step.contentDescription),
        modifier = Modifier
            .size(200.dp)
            .background(color = MaterialTheme.colorScheme.tertiaryContainer, shape = imageShape)
            .border(width = 2.dp, color = Viking, shape = imageShape),
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(step.message),
        style = MaterialTheme.typography.bodyLarge,
    )
}

@DarkLightPreviews
@Composable
private fun LemonTextAndImagePreview() = LemonadeTheme {
    Surface {
        LemonTextAndImage(LemonadeSteps.LemonTree)
    }
}
