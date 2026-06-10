package ar.edu.unlam.mobile.scaffolding.data.datasources.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val publishedYear: Int,
    @Embedded(prefix = "author_") val author: UserEntity,
)
