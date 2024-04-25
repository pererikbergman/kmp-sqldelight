package post.data.post

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.rakangsoftware.sqldelight.data.local.database.Database
import com.rakangsoftware.sqldelight.data.local.database.PostDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostDataSourceSql(database: Database) : PostDataSource {

    private val query = database.databaseQueries

    override fun getAll() =
        query.selectAllPosts()
            .asFlow()
            .mapToList(Dispatchers.IO)

    override fun getById(id: Long) =
        query.selectPostById(id)
            .asFlow()
            .mapToOne(Dispatchers.IO)

    override fun put(postDto: PostDto) = run {
        query.insertPost(postDto.name, postDto.title, postDto.body, postDto.number_of_views)
        val id = query.selectLastInsertedRowId().executeAsOne()
        getById(id)
    }

    override fun update(postDto: PostDto) = run {
        query.updatePost(postDto.name, postDto.title, postDto.body, postDto.id, postDto.number_of_views)
        getById(postDto.id)
    }

    override fun delete(postDto: PostDto) = flow {
        query.deletePost(postDto.id)
        emit(Unit)
    }

}