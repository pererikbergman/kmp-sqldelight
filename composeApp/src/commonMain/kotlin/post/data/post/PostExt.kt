package post.data.post

import com.rakangsoftware.sqldelight.data.local.database.PostDto
import post.domain.post.Post

fun PostDto.toPost() = Post(
    id, name, title, body, creationDate, modifiedDate, number_of_views
)

fun Post.toPostDto() = PostDto(
    id, name, title, body, creationDate, modifiedDate, numberOfViews
)
