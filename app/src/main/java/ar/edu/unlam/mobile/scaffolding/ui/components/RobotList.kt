package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ar.edu.unlam.mobile.scaffolding.data.repositories.robot.Robot

@Composable
fun RobotItem(robot: Robot) {
    // Aquí puedes implementar la UI para mostrar los detalles del robot
    // Por ejemplo, podrías mostrar el nombre y el modelo del robot
    Text(text = "Nombre: ${robot.name}")
    Text(text = "Modelo: ${robot.model}")
}

@Composable
fun RobotList(robots: List<Robot>) {
    LazyColumn {
        items(robots.size) { index ->
            RobotItem(robot = robots[index])
        }
    }
}
