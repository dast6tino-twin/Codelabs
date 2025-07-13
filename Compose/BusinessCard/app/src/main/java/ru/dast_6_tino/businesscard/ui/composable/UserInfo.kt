package ru.dast_6_tino.businesscard.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dast_6_tino.businesscard.R
import ru.dast_6_tino.businesscard.ui.DarkLightPreviews
import ru.dast_6_tino.businesscard.ui.theme.BusinessCardTheme

@Composable
fun UserInfo(modifier: Modifier = Modifier) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
) {
    Image(
        painter = painterResource(R.drawable.user_image_placeholder),
        contentDescription = stringResource(R.string.user_avatar),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .border(1.dp, MaterialTheme.colorScheme.onBackground, CircleShape),
    )
    Text(
        text = stringResource(R.string.user_name_placeholder),
        fontSize = 24.sp,
        modifier = Modifier.padding(top = 16.dp),
    )
    Text(
        text = stringResource(R.string.user_title_placeholder),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 8.dp),
    )
}

@DarkLightPreviews
@Composable
fun UserInfoPreview() = BusinessCardTheme {
    Surface {
        UserInfo()
    }
}
