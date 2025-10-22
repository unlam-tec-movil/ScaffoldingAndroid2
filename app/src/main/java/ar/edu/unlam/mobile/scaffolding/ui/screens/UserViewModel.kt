package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffolding.data.repositories.RobotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Immutable
sealed interface RobotListUIState {
    data class Success(
        val robots: List<Robot>,
    ) : RobotListUIState

    object Loading : RobotListUIState

    data class Error(
        val message: String,
    ) : RobotListUIState
}

data class UserViewUIState(
    val robotListState: RobotListUIState,
)

@HiltViewModel
class UserViewModel
    @Inject
    constructor(
        repo: RobotRepository,
    ) : ViewModel() {
        val robots = repo.getRobots().asLiveData()

        private val _userUiState = MutableStateFlow(UserViewUIState(RobotListUIState.Loading))
        val userUiState = _userUiState.asStateFlow()

        val robotFlow =
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

        init {
            viewModelScope.launch {
                robotFlow.collect {
                    _userUiState.value = UserViewUIState(RobotListUIState.Success(it))
                }
            }
        }
    }
