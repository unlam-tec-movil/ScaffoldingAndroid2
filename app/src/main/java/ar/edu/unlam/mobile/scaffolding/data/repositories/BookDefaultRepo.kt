package ar.edu.unlam.mobile.scaffolding.data.repositories

import ar.edu.unlam.mobile.scaffolding.data.datasources.local.UserBookDao
import javax.inject.Inject

class BookDefaultRepo
    @Inject
    constructor(
        private val bookDataSource: UserBookDao,
    ) : BookRepository {
        override fun getBooks(): List<Book> = bookDataSource.getAllBooks()
    }
