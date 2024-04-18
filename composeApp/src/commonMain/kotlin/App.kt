import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import di.createViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import post.PostScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel = getViewModel(key = "post-screen-view-model",
            factory = viewModelFactory { createViewModel() })

        PostScreen(
            viewModel = viewModel,
            onPostTapped = { postId ->
                println("Post with id: $postId tapped.")
            }
        )
    }
}