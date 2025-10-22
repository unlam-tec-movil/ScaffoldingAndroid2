package ar.edu.unlam.mobile.scaffolding.data.repositories

import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockRepository
    @Inject
    constructor() : RobotRepository {
        override fun getRobots(): Flow<List<Robot>> =
            flow {
                emit(
                    listOf(
                        Robot(
                            id = "1",
                            name = "Robot 1",
                            avatarUrl = "https://robohash.org/1",
                        ),
                        Robot(
                            id = "2",
                            name = "Robot 2",
                            avatarUrl = "https://robohash.org/2",
                        ),
                        Robot(
                            id = "3",
                            name = "Robot 3",
                            avatarUrl = "https://robohash.org/3",
                        ),
                    ),
                )
            }
    }
