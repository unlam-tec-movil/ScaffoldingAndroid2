package ar.edu.unlam.mobile.scaffolding.data.datasources.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ar.edu.unlam.mobile.scaffolding.data.repositories.Book
import ar.edu.unlam.mobile.scaffolding.data.repositories.UserBook

@Dao
interface UserBookDao {
    @Query(
        "SELECT b.title as bookName, u.name as author FROM BookEntity AS b JOIN UserEntity as U " +
            "WHERE u.id = :userId AND b.id = :bookId",
    )
    suspend fun getUserBook(
        userId: Int,
        bookId: Int,
    ): UserBook

    @Transaction
    @Query(
        "Select id, title, author_name as pepito FROM BookEntity",
    )
    fun getAllBooks(): List<Book>
}
