package ar.edu.unlam.mobile.scaffolding.ui.screens.robotadmin

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.repositories.robot.Robot
import ar.edu.unlam.mobile.scaffolding.data.repositories.robot.RobotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

// El estado de la UI se define como una interfaz sellada (sealed interface) que representa
// los diferentes estados posibles de la lista de robots: éxito, carga y error.
// Esto permite manejar de manera clara y estructurada los estados de la UI.
@Immutable
sealed interface RobotListUIState {
    data class Success(
        val list: List<Robot>,
    ) : RobotListUIState

    data object Loading : RobotListUIState

    data class Error(
        val message: String,
    ) : RobotListUIState
}

// El estado general de la UI se define como una clase de datos que contiene todos los estados de
// este view model.
data class UIState(
    val robotListState: RobotListUIState,
)

@HiltViewModel
class RobotAdminViewModel
    @Inject
    constructor(
        val robotRepo: RobotRepository,
    ) : ViewModel() {
        private val _uiState =
            MutableStateFlow(
                UIState(
                    RobotListUIState.Loading,
                ),
            )
        val uiState = _uiState.asStateFlow()

        init {
            // Al iniciar el view model, se lanza una corrutina para obtener la lista de robots
            // desde el repositorio. De esta forma el bloque de codigo se ejecuta de forma
            // asincrónica y no bloquea el hilo principal de la aplicación.
            System.out.println("Obteniendo robots...")
            viewModelScope.launch {
                robotRepo
                    .getRobots() // Get robots nos devuelve un flow
                    .catch {
                        // Catch es el metodo del flow que se ejecuta en caso
                        // de que haya un error en el flujo de datos.
                        // En este caso, actualizamos el estado de la UI a "Error" con el mensaje correspondiente.
                        _uiState.value =
                            UIState(
                                RobotListUIState.Error(
                                    it.message ?: "Error desconocido",
                                ),
                            )
                    }.collect { robots ->
                        // Collect es el metodo del flow que se ejecuta cada vez que se emite
                        // un nuevo valor en el flujo de datos.
                        _uiState.value = UIState(RobotListUIState.Success(robots))
                        System.out.println("Robots obtenidos 😎😎")
                    }
            }
            System.out.println("Robots obtenidos?")
        }
    }
