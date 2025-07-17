package ru.dast_6_tino.accessibility.data.posts

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import ru.dast_6_tino.accessibility.model.Post
import ru.dast_6_tino.accessibility.utils.addOrRemove

/**
 * Simplified implementation of PostsRepository that returns a hardcoded list of
 * posts with resources synchronously.
 */
class PostsRepository {

    // for now, keep the favorites in memory
    private val favorites = MutableStateFlow<Set<String>>(setOf())

    /**
     * Get a specific JetNews post.
     */
    fun getPost(postId: String?): Post? = posts.find { it.id == postId }

    /**
     * Get JetNews posts.
     */
    fun getPosts(): List<Post> = posts

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<String>> = favorites

    /**
     * Toggle a postId to be a favorite or not.
     */
    fun toggleFavorite(postId: String) {
        val set = favorites.value.toMutableSet()
        set.addOrRemove(postId)
        favorites.value = set
    }

}
