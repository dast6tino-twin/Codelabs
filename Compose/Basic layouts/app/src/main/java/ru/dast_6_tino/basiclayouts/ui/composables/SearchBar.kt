package ru.dast_6_tino.basiclayouts.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.basiclayouts.R
import ru.dast_6_tino.basiclayouts.ui.DarkLightPreviews
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        placeholder = {
            Text(stringResource(R.string.placeholder_search))
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        colors = TextFieldDefaults.colors().copy(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
    )
}

@DarkLightPreviews
@Composable
fun SearchBarLightPreview() {
    BasicLayoutsTheme {
        SearchBar()
    }
}
