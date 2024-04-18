package post

import post.domain.usecase.GetAllPostsUseCase

data class PostScreenUseCases(
    val allPostsUseCase: GetAllPostsUseCase
)