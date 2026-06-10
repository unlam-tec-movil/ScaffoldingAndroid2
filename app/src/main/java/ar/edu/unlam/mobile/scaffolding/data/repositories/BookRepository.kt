package ar.edu.unlam.mobile.scaffolding.data.repositories

interface BookRepository {
    fun getBooks(): List<Book>
}
