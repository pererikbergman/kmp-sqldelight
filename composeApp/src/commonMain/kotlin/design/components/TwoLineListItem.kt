package design.components

import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import design.components.Design

@Composable
fun Design.TwoLineListItem(
    modifier: Modifier = Modifier,
    primaryText: String,
    secondaryText: String
) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(
                text = primaryText,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                text = secondaryText,
                style = MaterialTheme.typography.bodySmall
            )
        }
    )
}