package ar.edu.unlam.mobile.scaffolding.data.repositories.robot

import kotlinx.coroutines.flow.Flow

interface RobotRepository {
    suspend fun getRobots(): Flow<List<Robot>>
}
