package com.mor.tedoooandroidhomeassignment

import kotlinx.coroutines.delay
import java.util.*
import kotlin.random.Random

data class User(
    val username: String,
    val avatar: String?
)

data class Post(
    val id: String,
    val user: User,
    val text: String,
    val images: List<String>,
    val didLike: Boolean,
    val comments: Int,
    val likes: Int,
    val date: Date
)

data class Comment(
    val id: String,
    val user: User,
    val text: String,
    val date: Date
)

data class FeedResponse(
    val hasMore: Boolean,
    val posts: List<Post>
)

data class CommentResponse(
    val hasMore: Boolean,
    val comments: List<Comment>
)

object TedoooRepository {

    private val users = listOf(
        User("Avram", null),
        User("Yossi", "https://i.pravatar.cc/300?a=yossi"),
        User("Dina", "https://i.pravatar.cc/300?a=dina"),
        User("Tal", null),
        User("Alex", "https://i.pravatar.cc/300?a=alex"),
    )

    private fun randomUser(): User {
        return users[Random.nextInt(0, users.size)]
    }

    private fun randomDate(): Date {
        return Date(Date().time - Random.nextLong(3600 * 24 * 7))
    }

    private fun randomPost(): Post {
        return Post(
            UUID.randomUUID().toString(), randomUser(),
            UUID.randomUUID().toString(), List(10) {
                "https://i.pravatar.cc/300?a=${UUID.randomUUID()}"
            }, Random.nextBoolean(),
            Random.nextInt(1, 10),
            Random.nextInt(1, 10),
            randomDate()
        )
    }

    private fun randomComment(): Comment {
        return Comment(
            UUID.randomUUID().toString(), randomUser(),
            UUID.randomUUID().toString(), randomDate()
        )
    }

    suspend fun getFeed(): Result<FeedResponse> {
        delay(750L)
        return Result.success(
            FeedResponse(
                Random.nextInt(0, 5) != 4,
                List(10) { randomPost() }
            )
        )
    }

    suspend fun getComments(postId: String, maxId: String?): Result<CommentResponse> {
        delay(750L)
        return Result.success(
            CommentResponse(
                Random.nextInt(0, 5) != 4,
                List(10) { randomComment() }
            )
        )
    }

}