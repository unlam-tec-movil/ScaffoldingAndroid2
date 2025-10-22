package ar.edu.unlam.mobile.scaffolding.ui.screens.robot

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun RobotScreen(robotViewModel: RobotViewModel = hiltViewModel<RobotViewModel>()) {
    val robotList = robotViewModel.robotList.collectAsState(initial = emptyList())
    Column {
        Text(text = "Robots: ${robotList.value.size}")
        Example(
            flow {
                var initValue = 5
                while (true) {
                    emit("Hello, Flow! every $initValue")
                    delay(1000L)
                    initValue = initValue + 5
                }
            },
        )
    }
}

@Composable
fun Example(exampleFlow: Flow<String>) {
    Text("otro text 😎")
    val stringDeEjemplo = exampleFlow.collectAsStateWithLifecycle("ejemplo").value
    Text(stringDeEjemplo)
}

@Composable
@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
fun ExamplePreview() {
    val exampleFlow =
        flow {
            emit("Hello, Flow!")
            delay(500L)
            emit("Hello, Flow! after 500ms")
        }

    Example(exampleFlow)
}
