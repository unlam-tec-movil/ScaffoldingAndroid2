package ar.edu.unlam.mobile.scaffolding.data.repositories.robot

import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RobotMockRepository
    @Inject
    constructor() : RobotRepository {
        override suspend fun getRobots(): Flow<List<Robot>> {
            delay(5000L)
            return flow {
                emit(
                    listOf(
                        Robot(
                            id = "1",
                            name = "2b",
                            model = "Modelo de Batalla",
                        ),
                        Robot(
                            id = "2",
                            name = "Robo2",
                            model = "Model B",
                        ),
                        Robot(
                            id = "3",
                            name = "Robo3",
                            model = "Model C",
                        ),
                    ),
                )
            }
        }
    }
