package ru.dast_6_tino.woof.ui.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
 */
@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier,
) = Card(
    shape = MaterialTheme.shapes.medium,
    modifier = modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_small)),
    ) {
        DogIcon(dog.imageResourceId)
        DogInformation(dog.name, dog.age)
    }
}

@DarkLightPreviews
@Composable
private fun DogItemPreview() = WoofTheme {
    Surface {
        DogItem(
            dog = Dog(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
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
fun DogIcon(
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
        .padding(dimensionResource(R.dimen.padding_small))
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
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
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
