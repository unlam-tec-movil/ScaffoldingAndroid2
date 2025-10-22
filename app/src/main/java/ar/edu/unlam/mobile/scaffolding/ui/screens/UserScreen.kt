package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ar.edu.unlam.mobile.scaffolding.ui.components.Greeting
import kotlinx.coroutines.flow.flow

data class Robot(
    val id: String,
    val name: String,
    val avatarUrl: String,
)

@Composable
fun UserScreen(
    userId: String,
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = hiltViewModel<UserViewModel>(),
) {
    Greeting(userId, modifier)
    val uiState: UserViewUIState by viewModel.userUiState.collectAsStateWithLifecycle()
    when (val res = uiState.robotListState) {
        is RobotListUIState.Loading -> {
            Text("Loading...")
        }

        is RobotListUIState.Success -> {
            RobotList(robots = res.robots)
        }

        is RobotListUIState.Error -> {
            Text("Error: ${res.message}")
        }
    }
}

@Composable
fun RobotList(robots: List<Robot>) {
    LazyColumn {
        item {
            Text(text = "Robots: ${robots.size}")
        }
        items(robots.size) { index ->
            val robot = robots[index]
            Text(text = "Robot: ${robot.name} - id: ${robot.id}")
        }
        item {
            Text(text = "Robots: ${robots.size}")
        }
    }
}

@Composable
@Preview
fun RobotListPreview() {
    val robotsListFlow =
        flow {
            emit(
                listOf(
                    Robot(
                        id = "1",
                        name = "2b",
                        avatarUrl = "https://pbs.twimg.com/media/F5Tqs9-W8AAUTv0?format=jpg&name=small",
                    ),
                    Robot(
                        id = "2",
                        name = "Robot 2",
                        avatarUrl = "https://robohash.org/2",
                    ),
                    Robot(
                        id = "2",
                        name = "a2",
                        avatarUrl = "https://robohash.org/2",
                    ),
                ),
            )
        }
    val robotsList = robotsListFlow.collectAsStateWithLifecycle(listOf()).value

    RobotList(
        robots = robotsList,
    )
}
