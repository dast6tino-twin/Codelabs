package ru.dast_6_tino.woof.ui.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.dast_6_tino.woof.R
import ru.dast_6_tino.woof.data.Dog
import ru.dast_6_tino.woof.ui.DarkLightPreviews
import ru.dast_6_tino.woof.ui.theme.WoofTheme

/**
 * Composable that displays a list item containing a dog icon and their information.
 *
 * @param dog contains the data that populates the list item
 * @param modifier modifiers to set to this composable
 * @param isExpandedDefault Flag to display hobby by default
 */
@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier,
    isExpandedDefault: Boolean = false,
) {
    var isExpanded by remember { mutableStateOf(isExpandedDefault) }
    val color by animateColorAsState(
        targetValue = if (isExpanded) {
            MaterialTheme.colorScheme.tertiaryContainer
        } else {
            MaterialTheme.colorScheme.primaryContainer
        },
    )
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.clickable { isExpanded = !isExpanded },
    ) {
        val paddingMedium = dimensionResource(R.dimen.padding_medium)
        Column(
            modifier = Modifier
                .background(color = color)
                .padding(paddingMedium)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium,
                    ),
                ),
        ) {
            val paddingSmall = dimensionResource(R.dimen.padding_small)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {
                DogIcon(dogIcon = dog.imageResourceId)
                Spacer(Modifier.width(paddingSmall))
                DogInformation(dogName = dog.name, dogAge = dog.age)
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = stringResource(R.string.expand_button_content_description),
                )
            }
            if (isExpanded) {
                Spacer(Modifier.height(paddingMedium))
                Text(
                    text = stringResource(R.string.about),
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    text = stringResource(dog.hobbies),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@DarkLightPreviews
@Composable
private fun DogItemExpandedPreview() = WoofTheme {
    Surface {
        DogItem(
            dog = Dog(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
            isExpandedDefault = true,
        )
    }
}

@DarkLightPreviews
@Composable
private fun DogItemFoldedPreview() = WoofTheme {
    Surface {
        DogItem(
            dog = Dog(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
            isExpandedDefault = false,
        )
    }
}

/**
 * Composable that displays a photo of a dog.
 *
 * @param dogIcon is the resource ID for the image of the dog
 * @param modifier modifiers to set to this composable
 */
@Composable
private fun DogIcon(
    @DrawableRes dogIcon: Int,
    modifier: Modifier = Modifier,
) = Image(
    painter = painterResource(dogIcon),
    // Content Description is not needed here - image is decorative, and setting a null content
    // description allows accessibility services to skip this element during navigation.
    contentDescription = null,
    contentScale = ContentScale.Crop,
    modifier = modifier
        .size(dimensionResource(R.dimen.image_size))
        .clip(MaterialTheme.shapes.small),
)

@DarkLightPreviews
@Composable
private fun DogIconPreview() = WoofTheme {
    Surface {
        DogIcon(R.drawable.koda)
    }
}

/**
 * Composable that displays a dog's name and age.
 *
 * @param dogName is the resource ID for the string of the dog's name
 * @param dogAge is the Int that represents the dog's age
 * @param modifier modifiers to set to this composable
 */
@Composable
fun DogInformation(
    @StringRes dogName: Int,
    dogAge: Int,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(dogName),
            style = MaterialTheme.typography.displayMedium,
        )
        Text(
            text = stringResource(R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@DarkLightPreviews
@Composable
private fun DogInformationPreview() = WoofTheme {
    Surface {
        DogInformation(R.string.dog_name_1, 2)
    }
}
