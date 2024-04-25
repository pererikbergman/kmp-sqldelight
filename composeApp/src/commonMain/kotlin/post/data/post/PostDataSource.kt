package post.data.post

import com.rakangsoftware.sqldelight.data.local.database.PostDto
import kotlinx.coroutines.flow.Flow

interface PostDataSource {
    fun getAll(): Flow<List<PostDto>>
    fun getById(id: Long): Flow<PostDto>
    fun put(postDto: PostDto): Flow<PostDto>
    fun update(postDto: PostDto): Flow<PostDto>
    fun delete(postDto: PostDto): Flow<Unit>
}