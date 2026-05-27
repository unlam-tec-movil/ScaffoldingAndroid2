package ar.edu.unlam.mobile.scaffolding.ui.screens.robotadmin

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.ui.components.RobotList

const val ROBOT_ADMIN_SCREEN_ROUTE = "robot_admin"

@Composable
fun RobotAdminScreen(
    modifier: Modifier = Modifier,
    viewModel: RobotAdminViewModel = hiltViewModel(),
) {
    // val uiState = viewModel.uiState.collectAsState().value
    // https://developer.android.com/topic/libraries/architecture/coroutines#lifecycle-aware-flow-collection
    // Para evitar fugas de memoria, es importante recolectar los Flows de forma consciente del ciclo de vida.
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    Box(modifier = modifier) {
        when (val robotList = uiState.robotListState) {
            is RobotListUIState.Loading -> {
                CircularProgressIndicator()
            }

            is RobotListUIState.Success -> {
                RobotList(robotList.list)
            }

            is RobotListUIState.Error -> {
                // Mostrar mensaje de error
            }
        }
    }
}
