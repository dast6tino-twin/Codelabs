package ru.dast_6_tino.accessibility.ui.article

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.dast_6_tino.accessibility.data.posts.post3
import ru.dast_6_tino.accessibility.model.*
import ru.dast_6_tino.accessibility.model.Paragraph
import ru.dast_6_tino.accessibility.ui.theme.JetnewsTheme

private val defaultSpacerSize = 16.dp

@Composable
fun PostContent(post: Post, modifier: Modifier = Modifier) = LazyColumn(
    modifier = modifier.padding(horizontal = defaultSpacerSize),
) {
    item {
        Spacer(Modifier.height(defaultSpacerSize))
        val imageModifier = modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        Image(
            painter = painterResource(post.imageId),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop,
        )
        Spacer(Modifier.height(defaultSpacerSize))
    }
    item {
        Text(text = post.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
    }
    post.subtitle?.let { subtitle ->
        item {
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp,
                )
            }
            Spacer(Modifier.height(defaultSpacerSize))
        }
    }
    item {
        PostMetadata(post.metadata)
        Spacer(Modifier.height(24.dp))
    }
    items(post.paragraphs) {
        Paragraph(paragraph = it)
    }
    item {
        Spacer(Modifier.height(48.dp))
    }
}

@Composable
private fun PostMetadata(metadata: Metadata, modifier: Modifier = Modifier) = Row(
    modifier = modifier.semantics(mergeDescendants = true) {},
) {
    Image(
        imageVector = Icons.Filled.AccountCircle,
        contentDescription = null,
        modifier = Modifier.size(40.dp),
        colorFilter = ColorFilter.tint(LocalContentColor.current),
        contentScale = ContentScale.Fit,
    )
    Spacer(Modifier.width(8.dp))
    Column {
        Text(
            text = metadata.author.name,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 4.dp),
        )

        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
            Text(
                text = "${metadata.date} • ${metadata.readTimeMinutes} min read",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
private fun Paragraph(paragraph: Paragraph) {
    val type = paragraph.type
    val (textStyle, paragraphStyle, trailingPadding) = getTextAndParagraphStyle(type)

    Box(modifier = Modifier.padding(bottom = trailingPadding)) {
        val annotatedString = AnnotatedString(
            text = paragraph.text,
            spanStyles = paragraph.markups.map { markup ->
                markup.toAnnotatedStringItem(
                    typography = MaterialTheme.typography,
                    codeBlockBackground = MaterialTheme.colorScheme.onSurface.copy(alpha = .15f),
                )
            },
        )
        when (type) {
            ParagraphType.BULLET -> BulletParagraph(
                text = annotatedString,
                textStyle = textStyle,
                paragraphStyle = paragraphStyle,
            )

            ParagraphType.CODE_BLOCK -> CodeBlockParagraph(
                text = annotatedString,
                textStyle = textStyle,
                paragraphStyle = paragraphStyle,
            )

            ParagraphType.HEADER -> Text(
                modifier = Modifier
                    .padding(4.dp)
                    .semantics { heading() },
                text = annotatedString,
                style = textStyle.merge(paragraphStyle),
            )

            else -> Text(
                modifier = Modifier.padding(4.dp),
                text = annotatedString,
                style = textStyle,
            )
        }
    }
}

@Composable
private fun CodeBlockParagraph(
    text: AnnotatedString,
    textStyle: TextStyle,
    paragraphStyle: ParagraphStyle,
) = Surface(
    color = MaterialTheme.colorScheme.onSurface.copy(alpha = .15f),
    shape = MaterialTheme.shapes.small,
    modifier = Modifier.fillMaxWidth(),
) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = text,
        style = textStyle.merge(paragraphStyle),
    )
}

@Composable
private fun BulletParagraph(
    text: AnnotatedString,
    textStyle: TextStyle,
    paragraphStyle: ParagraphStyle,
) = Row {
    with(LocalDensity.current) {
        // this box is acting as a character, so it's sized with font scaling (sp)
        Box(
            modifier = Modifier
                .size(8.sp.toDp(), 8.sp.toDp())
                .alignBy {
                    // Add an alignment "baseline" 1sp below the bottom of the circle
                    9.sp.roundToPx()
                }
                .background(LocalContentColor.current, CircleShape),
        ) { /* no content */ }
    }
    Text(
        modifier = Modifier
            .weight(1f)
            .alignBy(FirstBaseline),
        text = text,
        style = textStyle.merge(paragraphStyle),
    )
}

@Composable
private fun getTextAndParagraphStyle(type: ParagraphType): Triple<TextStyle, ParagraphStyle, Dp> = when (type) {
    ParagraphType.CAPTION -> Triple(
        first = MaterialTheme.typography.bodyLarge,
        second = ParagraphStyle(),
        third = 24.dp,
    )

    ParagraphType.TITLE -> Triple(
        first = MaterialTheme.typography.headlineMedium,
        second = ParagraphStyle(),
        third = 24.dp,
    )

    ParagraphType.SUBHEAD -> Triple(
        first = MaterialTheme.typography.titleLarge,
        second = ParagraphStyle(),
        third = 16.dp,
    )

    ParagraphType.TEXT -> Triple(
        first = MaterialTheme.typography.bodyLarge.copy(lineHeight = 28.sp),
        second = ParagraphStyle().copy(lineHeight = 28.sp),
        third = 24.dp,
    )

    ParagraphType.HEADER -> Triple(
        first = MaterialTheme.typography.headlineSmall,
        second = ParagraphStyle(),
        third = 16.dp,
    )

    ParagraphType.CODE_BLOCK -> Triple(
        first = MaterialTheme.typography.bodyLarge.copy(fontFamily = FontFamily.Monospace),
        second = ParagraphStyle(),
        third = 24.dp,
    )

    ParagraphType.QUOTE -> Triple(
        first = MaterialTheme.typography.bodyLarge,
        second = ParagraphStyle(),
        third = 24.dp,
    )

    ParagraphType.BULLET -> Triple(
        first = MaterialTheme.typography.bodyLarge,
        second = ParagraphStyle(textIndent = TextIndent(firstLine = 8.sp)),
        third = 24.dp,
    )
}

private fun Markup.toAnnotatedStringItem(
    typography: Typography,
    codeBlockBackground: Color,
): AnnotatedString.Range<SpanStyle> = when (this.type) {
    MarkupType.ITALIC -> AnnotatedString.Range(
        item = typography.bodyLarge.copy(fontStyle = FontStyle.Italic).toSpanStyle(),
        start = start,
        end = end,
    )

    MarkupType.LINK -> AnnotatedString.Range(
        item = typography.bodyLarge.copy(textDecoration = TextDecoration.Underline).toSpanStyle(),
        start = start,
        end = end,
    )

    MarkupType.BOLD -> AnnotatedString.Range(
        item = typography.bodyLarge.copy(fontWeight = FontWeight.Bold).toSpanStyle(),
        start = start,
        end = end,
    )

    MarkupType.CODE -> AnnotatedString.Range(
        item = typography.bodyLarge.copy(
            background = codeBlockBackground,
            fontFamily = FontFamily.Monospace,
        ).toSpanStyle(),
        start = start,
        end = end,
    )
}

@Preview("Post content")
@Preview("Post content (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPost() = JetnewsTheme {
    Surface {
        PostContent(post = post3)
    }
}

@Preview("Post metadata")
@Preview("Post metadata (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPostMetadata() = JetnewsTheme {
    Surface {
        PostMetadata(post3.metadata)
    }
}
