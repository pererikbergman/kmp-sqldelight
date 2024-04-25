package di

import core.database.DriverFactory
import core.database.createDatabase
import post.PostScreenUseCases
import post.PostScreenViewModel
import post.data.post.PostDataSourceSql
import post.data.post.PostRepositorySQL
import post.domain.usecase.GetAllPostsUseCase

fun createViewModel(): PostScreenViewModel {
    return PostScreenViewModel(
        PostScreenUseCases(
            GetAllPostsUseCase(
                PostRepositorySQL(
                    PostDataSourceSql(
                        createDatabase(DriverFactory())
                    )
                )
            )
        )
    )
}