package post.domain.post

data class Post(
    val id: Long,
    val name: String,
    val title: String,
    val body: String,
    val creationDate: String,
    val modifiedDate: String,
    val numberOfViews: Long,
)