package post.data.post

import core.Result
import core.database.DataError
import kotlinx.coroutines.flow.flow
import post.domain.post.Post
import post.domain.post.PostRepository

class PostRepositoryMemory() : PostRepository {

    private val posts = mutableListOf(
        Post(1, "Julia", "Discovering Space", "The cosmos is vast and beautiful.", "2024-04-18", "2024-04-18", 0),
        Post(2, "Marco", "Java vs Python", "Comparing two powerful languages.", "2024-04-18", "2024-04-18", 0),
        Post(3, "Emily", "Gardening Tips", "Learn to grow your own herbs.", "2024-04-18", "2024-04-18", 0),
        Post(4, "Raj", "Mysteries of History", "Exploring ancient civilizations.", "2024-04-18", "2024-04-18", 0),
        Post(5, "Sarah", "Healthy Eating", "Balanced diets for a busy life.", "2024-04-18", "2024-04-18", 0),
        Post(6, "Alex", "Marathon Training", "Preparing for your first race.", "2024-04-18", "2024-04-18", 0),
        Post(7, "Lena", "Photography Basics", "Capture stunning landscapes.", "2024-04-18", "2024-04-18", 0),
        Post(8, "Tom", "Future of AI", "How AI is changing our world.", "2024-04-18", "2024-04-18", 0),
        Post(9, "Sophia", "World Cuisine", "Exploring flavors from around the globe.", "2024-04-18", "2024-04-18", 0),
        Post(10, "Miguel", "Space Exploration", "Journey to the stars and beyond.", "2024-04-18", "2024-04-18", 0)
    )

    override fun fetchAll() = flow<Result<List<Post>, DataError>> {
        emit(Result.Success(posts))
    }

    override fun fetchById(id: Int) = flow<Result<Post, DataError>> {
        val post = posts.find { it.id == id.toLong() }
        post?.let {
            emit(Result.Success(it))
        } ?: run {
            emit(Result.Error(DataError.DATA_NOT_FOUND))
        }
    }

    override fun create(newPost: Post) = flow<Result<Post, DataError>> {
        posts.add(newPost.copy(id = (posts.maxOfOrNull { it.id } ?: 0) + 1))  // Ensure unique ID
        emit(Result.Success(posts.last()))
    }

    override fun update(existingPost: Post) = flow<Result<Post, DataError>> {
        val index = posts.indexOfFirst { it.id == existingPost.id }
        if (index != -1) {
            posts[index] = existingPost
            emit(Result.Success(existingPost))
        } else {
            emit(Result.Error(DataError.DATA_NOT_FOUND))
        }
    }

    override fun delete(postToRemove: Post) = flow<Result<Boolean, DataError>> {
        val result = posts.removeAll { it.id == postToRemove.id }
        if (result) {
            emit(Result.Success(true))
        } else {
            emit(Result.Error(DataError.UNKNOWN))
        }
    }
}