package ar.edu.unlam.mobile.scaffolding.data.repositories

import kotlinx.coroutines.flow.Flow

interface RobotRepository {
    fun getRobots(): Flow<List<Robot>>
}
