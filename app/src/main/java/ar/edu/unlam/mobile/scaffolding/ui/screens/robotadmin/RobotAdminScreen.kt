package ar.edu.unlam.mobile.scaffolding.ui.screens.robotadmin

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.ui.components.RobotList

const val ROBOT_ADMIN_SCREEN_ROUTE = "robot_admin"

@Composable
fun RobotAdminScreen(
    modifier: Modifier = Modifier,
    viewModel: RobotAdminViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value

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
