package post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import core.Result
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import post.domain.post.Post

class PostScreenViewModel(
    private val useCases: PostScreenUseCases
) : ViewModel() {

//    private var _uiState = mutableStateOf(UIState())
//    val uiState: State<UIState> = _uiState

    private var _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState>
        get() = _uiState

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch(context = Dispatchers.Default) {

            // The result of the database operation is stored in 'resource'
            val resource = withContext(Dispatchers.IO) {
                // Switch to IO dispatcher for database operation
                useCases.allPostsUseCase()
            }

            when (resource) {
                is Result.Success -> {
                    _uiState.update {
                        it.copy(posts = resource.data)
                    }
                }

                is Result.Error -> {
                    _uiState.update {
                        it.copy(posts = emptyList())
                    }
                }
            }
        }
    }

    data class UIState(
        val posts: List<Post> = emptyList()
    )
}