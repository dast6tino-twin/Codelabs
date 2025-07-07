package ru.dast_6_tino.accessibility.model

import androidx.annotation.DrawableRes

data class Post(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val url: String,
    val publication: Publication? = null,
    val metadata: Metadata,
    val paragraphs: List<Paragraph> = emptyList(),
    @param:DrawableRes val imageId: Int,
    @param:DrawableRes val imageThumbId: Int,
)

data class Metadata(
    val author: PostAuthor,
    val date: String,
    val readTimeMinutes: Int,
)

data class PostAuthor(
    val name: String,
    val url: String? = null,
)

data class Publication(
    val name: String,
    val logoUrl: String,
)

data class Paragraph(
    val type: ParagraphType,
    val text: String,
    val markups: List<Markup> = emptyList(),
)

data class Markup(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null,
)

enum class MarkupType {
    LINK,
    CODE,
    ITALIC,
    BOLD,
}

enum class ParagraphType {
    TITLE,
    CAPTION,
    HEADER,
    SUBHEAD,
    TEXT,
    CODE_BLOCK,
    QUOTE,
    BULLET,
}
