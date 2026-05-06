package ar.edu.unlam.mobile.scaffolding.di

import ar.edu.unlam.mobile.scaffolding.data.repositories.robot.RobotMockRepository
import ar.edu.unlam.mobile.scaffolding.data.repositories.robot.RobotRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RobotModule {
    @Binds
    abstract fun bindRobotRepository(robotRepositoryImpl: RobotMockRepository): RobotRepository
}
