import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.beleningalina.jetpackcomposepoc.ui.components.InfoCard
import com.beleningalina.jetpackcomposepoc.ui.navigation.AppScreen
import com.beleningalina.jetpackcomposepoc.ui.product.MockAnalytics
import com.beleningalina.jetpackcomposepoc.ui.theme.AppSpacing

/**
 * This example uses SideEffect to synchronize the Compose state with an external
 * system (MockAnalytics) after every successful recomposition.
 *
 * - SideEffect: runs its block only after the Composable successfully finishes rendering.
 * - analytics.logScreenView: ensures that the analytics service always knows exactly
 * what product name is being displayed to the user.
 *
 * Advantage: It guarantees that the external system is an exact "mirror" of the UI state.
 * Disadvantage: It executes on every recomposition. It should only be used for
 * lightweight, non-suspending tasks that don't directly modify the Compose state.
 */
@Composable
fun ProductInfoScreen() {
    val productCount = remember { mutableStateOf(1) } // This variable is used for triggering recompositions
    val productName = "Smartphone Model X-${productCount.value}"
    val analytics = remember { MockAnalytics() }

    SideEffect {
        analytics.logScreenView(productName)
        Log.d("Performance", "✅ Render finalized for: $productName")
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(AppSpacing.medium)
    ) {
        val (title, productInfo, infoCard) = createRefs()

        Text(
            text = AppScreen.ProductInfoScreen.title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = AppSpacing.large)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Column(
            modifier = Modifier.fillMaxSize()
                .constrainAs(productInfo) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(infoCard.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(AppSpacing.large),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = productName, style = MaterialTheme.typography.bodyLarge)

            Button(onClick = { productCount.value++ }) {
                Text("Switch to Next Product")
            }
        }

        InfoCard(
            modifier = Modifier.constrainAs(infoCard) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            title = "What is SideEffect used for?",
            message = "It is used to run code whenever the Composable function is re-composed, ensuring it runs after a successful rendering.",
            points = listOf(
                "Ideal for triggering logging or reporting analytics",
                "Useful for informing a non-Compose system that something changed",
                "Runs after every successful recomposition",
                "Does not support coroutines (non-suspending only)",
                "Not cancellable — it is a 'fire-and-forget' mechanism"
            ),
            highlight = "Use for lightweight UI-related actions; avoid heavy tasks or state modifications here."
        )
    }
}