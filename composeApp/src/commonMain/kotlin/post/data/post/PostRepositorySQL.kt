package post.data.post

import core.Result
import core.database.DataError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import post.domain.post.Post
import post.domain.post.PostRepository

class PostRepositorySQL(
    private val dataSource: PostDataSource
) : PostRepository {

    override fun fetchAll() = flow<Result<List<Post>, DataError>> {
        dataSource.getAll().catch {
            emit(Result.Error(DataError.UNKNOWN))
        }.collect { dtos ->
            emit(Result.Success(dtos.map { dto -> dto.toPost() }))
        }
    }

    override fun fetchById(id: Int) = flow<Result<Post, DataError>> {
        dataSource.getById(id.toLong()).catch {
            emit(Result.Error(DataError.UNKNOWN))
        }.collect { postDto ->
            emit(Result.Success(postDto.toPost()))
        }
    }

    override fun create(newPost: Post) = flow<Result<Post, DataError>> {
        dataSource.put(newPost.toPostDto()).catch {
            emit(Result.Error(DataError.UNKNOWN))
        }.collect { postDto ->
            emit(Result.Success(postDto.toPost()))
        }
    }

    override fun update(existingPost: Post) = flow<Result<Post, DataError>> {
        dataSource.update(existingPost.toPostDto()).catch {
            emit(Result.Error(DataError.UNKNOWN))
        }.collect { postDto ->
            emit(Result.Success(postDto.toPost()))
        }
    }

    override fun delete(postToRemove: Post) = flow<Result<Boolean, DataError>> {
        try {
            dataSource.delete(postToRemove.toPostDto())
            emit(Result.Success(true))
        } catch (e: Exception) {
            emit(Result.Error(DataError.DATA_NOT_FOUND))
        }
    }

}