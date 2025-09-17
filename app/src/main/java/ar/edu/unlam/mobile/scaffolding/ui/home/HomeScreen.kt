package ar.edu.unlam.mobile.scaffolding.ui.home

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.Greeting

const val HOME_SCREEN_ROUTE = "home"

@Composable
fun HomeScreen(
    launchSnackbar: (message: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    // La información que obtenemos desde el view model la consumimos a través de un estado de
    // "tres vías": Loading, Success y Error. Esto nos permite mostrar un estado de carga,
    // un estado de éxito y un mensaje de error.
    val uiState: HomeUIState by viewModel.uiState.collectAsState()

    Column {
        when (val androidState = uiState.androidUIState) {
            is AndroidUIState.Loading -> {
                Box(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    CircularProgressIndicator()
                }
            }

            is AndroidUIState.Error -> {
                launchSnackbar(androidState.message)
            }

            is AndroidUIState.Success -> {
                LazyColumn {
                    items(androidState.androidList.size) { index ->
                        val android = androidState.androidList[index]
                        Text("Androide: ${android.nombre} - Version: ${android.version}")
                    }
                }
            }
        }
        when (val helloState = uiState.helloMessageState) {
            is HelloMessageUIState.Loading -> {
                Box(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                    CircularProgressIndicator()
                }
            }

            is HelloMessageUIState.Success -> {
                Column {
                    Greeting(helloState.message, modifier)
                    Text("Prueba")
                }
            }

            is HelloMessageUIState.Error -> {
                launchSnackbar(helloState.message)
            }
        }
    }
}
