import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "BMI Calculator",
        state = WindowState(
            position = WindowPosition.Aligned(Alignment.Center)
        )
    ) {
        App()
    }
}