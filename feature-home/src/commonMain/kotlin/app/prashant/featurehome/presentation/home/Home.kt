package app.prashant.featurehome.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
) {
    val homeViewModel: HomeViewModel = koinViewModel()
    HomeScreen(
        modifier = modifier,
        onNavigate = onNavigate,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text("Home Screen")
    }
}
