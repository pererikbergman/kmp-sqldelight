package post.domain.usecase

import core.Result
import core.database.DataError
import kotlinx.coroutines.flow.first
import post.domain.post.Post
import post.domain.post.PostRepository

class GetAllPostsUseCase(
    private val postRepository: PostRepository
) {
    suspend operator fun invoke(): Result<List<Post>, DataError> {
        return postRepository.fetchAll().first()
    }
}
