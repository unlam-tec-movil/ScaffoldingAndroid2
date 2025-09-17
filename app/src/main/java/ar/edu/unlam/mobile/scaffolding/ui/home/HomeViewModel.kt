package ar.edu.unlam.mobile.scaffolding.ui.home

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface HelloMessageUIState {
    data class Success(
        val message: String,
    ) : HelloMessageUIState

    data object Loading : HelloMessageUIState

    data class Error(
        val message: String,
    ) : HelloMessageUIState
}

@Immutable
sealed interface AndroidUIState {
    data class Success(
        val androidList: List<Androide>,
    ) : AndroidUIState

    data object Loading : AndroidUIState

    data class Error(
        val message: String,
    ) : AndroidUIState
}

// Definimos el estado de la pantalla Home
data class HomeUIState(
    val helloMessageState: HelloMessageUIState,
    val androidUIState: AndroidUIState,
)

data class Androide(
    val nombre: String,
    val version: String,
)

@HiltViewModel
class HomeViewModel
    @Inject
    constructor() : ViewModel() {
        private val androidList: List<Androide> =
            listOf(
                Androide("Cupcake", "1.5"),
                Androide("Donut", "1.6"),
                Androide("Eclair", "2.0 - 2.1"),
                Androide("Froyo", "2.2 - 2.2.3"),
                Androide("Gingerbread", "2.3 - 2.3.7"),
                Androide("Honeycomb", "3.0 - 3.2.6"),
                Androide("Ice Cream Sandwich", "4.0 - 4.0.4"),
                Androide("Jelly Bean", "4.1 - 4.3.1"),
                Androide("KitKat", "4.4 - 4.4.4"),
                Androide("Lollipop", "5.0 - 5.1.1"),
                Androide("Marshmallow", "6.0 - 6.0.1"),
                Androide("Nougat", "7.0 - 7.1.2"),
                Androide("Oreo", "8.0 - 8.1"),
                Androide("Pie", "9"),
                Androide("Android 10", "10"),
                Androide("Android 11", "11"),
                Androide("Android 12", "12"),
                Androide("Android 13", "13"),
            )

        // Mutable State Flow contiene un objeto de estado mutable. Simplifica la operación de
        // actualización de información y de manejo de estados de una aplicación: Cargando, Error, Éxito
        // (https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)
        // _helloMessage State es el estado del componente "HelloMessage" inicializado como "Cargando"
        private val helloMessage = MutableStateFlow(HelloMessageUIState.Loading)
        private val androidListState = MutableStateFlow(AndroidUIState.Loading)

        // _Ui State es el estado general del view model.
        private val _uiState =
            MutableStateFlow(
                HomeUIState(
                    helloMessageState = helloMessage.value,
                    androidUIState = androidListState.value,
                ),
            )

        // UIState expone el estado anterior como un Flujo de Estado de solo lectura.
        // Esto impide que se pueda modificar el estado desde fuera del ViewModel.
        val uiState = _uiState.asStateFlow()

        init {

            viewModelScope.launch {
                messageMock()
                    .catch { data ->
                        _uiState.update {
                            it.copy(
                                helloMessageState =
                                    HelloMessageUIState.Error(
                                        data.message ?: "Error desconocido",
                                    ),
                            )
                        }
                    }.collect { data ->
                        _uiState.update {
                            it.copy(helloMessageState = HelloMessageUIState.Success(data))
                        }
                    }
                apiMock()
                    .catch { data ->
                        _uiState.update {
                            it.copy(
                                androidUIState =
                                    AndroidUIState.Error(data.message ?: "Error desconocido"),
                            )
                        }
                    }.collect { data ->
                        _uiState.update {
                            it.copy(androidUIState = AndroidUIState.Success(data))
                        }
                    }
            }
        }

        fun messageMock(): Flow<String> =
            flow {
                throw Exception("Error forzado en el flujo")
                delay(1000) // Simulamos un retardo de 2 segundos
                emit("Hola desde el ViewModel")
            }

        fun apiMock(): Flow<List<Androide>> =
            flow {
                throw Exception("Error forzado en el flujo de androids")
                delay(3000) // Simulamos un retardo de 2 segundos
                emit(androidList)
            }
    }
