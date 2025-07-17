package ru.dast_6_tino.accessibility.data

import android.content.Context
import ru.dast_6_tino.accessibility.data.interests.InterestsRepository
import ru.dast_6_tino.accessibility.data.posts.PostsRepository

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {

    val postsRepository: PostsRepository
    val interestsRepository: InterestsRepository

}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    override val postsRepository: PostsRepository by lazy {
        PostsRepository()
    }

    override val interestsRepository: InterestsRepository by lazy {
        InterestsRepository()
    }

}
