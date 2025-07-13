package ru.dast_6_tino.businesscard.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.businesscard.ui.DarkLightPreviews
import ru.dast_6_tino.businesscard.ui.theme.BusinessCardTheme

@Composable
fun Contacts(modifier: Modifier = Modifier) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.Start,
) {
    Contact(Icons.Filled.Phone, "+0 (000) 000-00-00")
    Contact(Icons.Filled.Share, "@SocialMediaHandle")
    Contact(Icons.Filled.Email, "email@domain.com")
}

@Composable
fun Contact(
    icon: ImageVector,
    value: String,
    modifier: Modifier = Modifier,
) = Row(modifier) {
    Icon(imageVector = icon, contentDescription = null)
    Text(text = value, modifier = Modifier.padding(start = 4.dp))
}

@DarkLightPreviews
@Composable
fun ContactsPreview() = BusinessCardTheme {
    Surface {
        Contacts()
    }
}

@DarkLightPreviews
@Composable
fun ContactPreview() = BusinessCardTheme {
    Surface {
        Contact(Icons.Filled.Phone, "+0 (000) 000-00-00")
    }
}
