package ru.dast_6_tino.tiptime.ui.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import ru.dast_6_tino.tiptime.R
import ru.dast_6_tino.tiptime.ui.DarkLightPreviews
import ru.dast_6_tino.tiptime.ui.theme.TipTimeTheme

@Composable
fun EditNumberField(
    @DrawableRes leadIcon: Int,
    @StringRes label: Int,
    amount: String,
    onAmountChanged: (String) -> Unit,
    imeAction: ImeAction,
    modifier: Modifier = Modifier,
) = TextField(
    leadingIcon = { Icon(painterResource(leadIcon), null) },
    label = { Text(stringResource(label)) },
    value = amount,
    onValueChange = onAmountChanged,
    trailingIcon = {
        amount.takeIf { it.isNotBlank() }?.let {
            val description = stringResource(R.string.action_clear)
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = description,
                modifier = Modifier
                    .clickable { onAmountChanged.invoke("") }
                    .clearAndSetSemantics { contentDescription = description },
            )
        }
    },
    singleLine = true,
    keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = imeAction,
    ),
    modifier = modifier,
)

@DarkLightPreviews
@Composable
private fun EditNumberFieldPreview() = TipTimeTheme {
    Surface {
        EditNumberField(
            leadIcon = R.drawable.money,
            label = R.string.how_was_the_service,
            amount = "0",
            onAmountChanged = {},
            imeAction = ImeAction.Done,
        )
    }
}

@DarkLightPreviews
@Composable
private fun EditNumberFieldEmptyPreview() = TipTimeTheme {
    Surface {
        EditNumberField(
            leadIcon = R.drawable.money,
            label = R.string.how_was_the_service,
            amount = "",
            onAmountChanged = {},
            imeAction = ImeAction.Done,
        )
    }
}
