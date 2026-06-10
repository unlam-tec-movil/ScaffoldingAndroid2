package ar.edu.unlam.mobile.scaffolding.data.datasources.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getUserById(id: Int): UserEntity

    @Query("SELECT * FROM UserEntity WHERE name = :name")
    fun getByName(name: String): UserEntity
}
