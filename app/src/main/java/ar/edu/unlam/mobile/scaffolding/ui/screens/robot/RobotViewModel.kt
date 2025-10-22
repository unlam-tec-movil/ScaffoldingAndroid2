package ar.edu.unlam.mobile.scaffolding.ui.screens.robot

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.repositories.RobotRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class RobotViewModel
    @Inject
    constructor(
        roboRepo: RobotRepository,
    ) : ViewModel() {
        val robotList = roboRepo.getRobots()

        val sampleFlow =
            flow {
                emit("Hello, Flow!")
            }
    }
