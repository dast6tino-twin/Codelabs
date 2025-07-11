package ru.dast_6_tino.accessibility.data.interests

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.dast_6_tino.accessibility.utils.addOrRemove

typealias TopicsMap = Map<String, List<String>>

/**
 * Implementation of InterestRepository that returns a hardcoded list of
 * topics, people and publications synchronously.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class InterestsRepository {

    /**
     * Get relevant topics to the user.
     */
    val topics by lazy {
        mapOf(
            "Android" to listOf("Jetpack Compose", "Kotlin", "Jetpack"),
            "Programming" to listOf("Kotlin", "Declarative UIs", "Java"),
            "Technology" to listOf("Pixel", "Google"),
        )
    }

    // for now, keep the selections in memory
    private val selectedTopics = MutableStateFlow(setOf<TopicSelection>())

    // Used to make suspend functions that read and update state safe to call from any thread
    private val mutex = Mutex()

    /**
     * Toggle between selected and unselected
     */
    suspend fun toggleTopicSelection(topic: TopicSelection) {
        mutex.withLock {
            val set = selectedTopics.value.toMutableSet()
            set.addOrRemove(topic)
            selectedTopics.value = set
        }
    }

    /**
     * Currently selected topics
     */
    fun observeTopicsSelected(): Flow<Set<TopicSelection>> = selectedTopics

}

data class TopicSelection(val section: String, val topic: String)
