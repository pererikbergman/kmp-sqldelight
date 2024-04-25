package post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import design.components.TwoLineListItem
import design.components.Design

@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    viewModel: PostScreenViewModel,
    onPostTapped: (postId: Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    PostScreen(
        modifier = modifier,
        uiState = uiState,
    ) { postId ->
        onPostTapped(postId)
    }
}

@Composable
fun PostScreen(
    modifier: Modifier = Modifier,
    uiState: PostScreenViewModel.UIState,
    onPostTapped: (postId: Long) -> Unit
) {
    LazyColumn {
        items(uiState.posts) { post ->
            Design.TwoLineListItem(
                modifier = Modifier.clickable {
                    onPostTapped(post.id)
                },
                primaryText = "${post.title} Views: ${post.numberOfViews}",
                secondaryText = post.body
            )
        }
    }
}