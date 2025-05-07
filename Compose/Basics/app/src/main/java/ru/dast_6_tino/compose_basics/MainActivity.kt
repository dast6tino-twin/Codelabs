package ru.dast_6_tino.compose_basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.compose_basics.ui.DarkLightPreviews
import ru.dast_6_tino.compose_basics.ui.theme.BasicsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Welcome to the Basics!")
        Button(modifier = Modifier.padding(vertical = 16.dp), onClick = onContinueClicked) {
            Text("Continue")
        }
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier, names: List<String> = List(1000, Int::toString)) {
    LazyColumn(modifier = modifier.padding(4.dp)) {
        items(names) { name ->
            Greeting(name)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
    ) {
        CardContent(name)
    }
}

@Composable
fun CardContent(name: String, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    // Extra padding with animation example. Bad practice
    /*val bottomPadding by animateDpAsState(
        targetValue = if (expanded) 24.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        )
    )*/

    val rowModifier = modifier
        .padding(16.dp)
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow,
            ),
        )
    Row(modifier = rowModifier) {
        Column(
            modifier = Modifier.weight(1f),
            // Extra padding with animation example. Bad practice
            /*.padding(bottom = bottomPadding.coerceAtLeast(0.dp))*/
        ) {
            Text(text = "Hello ")
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                ),
            )
            if (expanded) Text(text = "Composem ipsum ".repeat(10))
        }

        IconButton(onClick = { expanded = !expanded }) {
            if (expanded) {
                Icon(
                    imageVector = Icons.Filled.ExpandLess,
                    contentDescription = stringResource(R.string.show_less),
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.ExpandMore,
                    contentDescription = stringResource(R.string.show_more),
                )
            }
        }
        // Button example
        /*ElevatedButton(
            onClick = { expanded = !expanded },
        ) {
            val value = if (expanded) R.string.show_less else R.string.show_more
            Text(stringResource(value))
        }*/
    }
}

@DarkLightPreviews
@Composable
fun OnboardingScreenPreview() {
    BasicsTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@DarkLightPreviews
@Composable
fun GreetingsPreview() {
    BasicsTheme { Greetings() }
}

@DarkLightPreviews
@Composable
fun GreetingPreview() {
    BasicsTheme { Greeting("0") }
}

@DarkLightPreviews
@Composable
fun CardContentPreview() {
    BasicsTheme { CardContent("0") }
}
