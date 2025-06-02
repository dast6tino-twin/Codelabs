package ru.dast_6_tino.textswithimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dast_6_tino.textswithimage.ui.DarkLightPreviews
import ru.dast_6_tino.textswithimage.ui.theme.TextsWithImageTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextsWithImageTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        nameTo = stringResource(R.string.sam),
                        nameFrom = stringResource(R.string.emma),
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

}

@Composable
fun Greeting(nameTo: String, nameFrom: String, modifier: Modifier = Modifier) = Box(modifier = modifier) {
    Image(
        painter = painterResource(R.drawable.party),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alpha = 0.5F,
        modifier = Modifier.fillMaxSize(),
    )
    GreetingTexts(
        nameTo = nameTo,
        nameFrom = nameFrom,
        modifier = modifier
            .align(Alignment.Center)
            .padding(16.dp),
    )
}

@DarkLightPreviews
@Composable
fun GreetingPreview() {
    TextsWithImageTheme {
        Greeting(nameTo = "Sam", nameFrom = "Emma")
    }
}

@Composable
fun GreetingTexts(nameTo: String, nameFrom: String, modifier: Modifier = Modifier) = Column(modifier = modifier) {
    Text(
        text = stringResource(R.string.greeting, nameTo),
        fontSize = 100.sp,
        lineHeight = 116.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
    Text(
        text = stringResource(R.string.greeting_from, nameFrom),
        fontSize = 32.sp,
        modifier = Modifier
            .padding(top = 16.dp)
            .align(Alignment.CenterHorizontally),
    )
}

@DarkLightPreviews
@Composable
fun GreetingTextsPreview() {
    TextsWithImageTheme {
        GreetingTexts(nameTo = "Sam", nameFrom = "Emma")
    }
}
