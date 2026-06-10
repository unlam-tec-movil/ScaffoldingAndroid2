package ar.edu.unlam.mobile.scaffolding.ui.screens

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffolding.data.repositories.Book
import ar.edu.unlam.mobile.scaffolding.data.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@Immutable
sealed interface BookUIState {
    data class Success(
        val bookList: List<Book>,
    ) : BookUIState

    data object Loading : BookUIState

    data class Error(
        val message: String,
    ) : BookUIState
}

data class BookViewUIState(
    val bookState: BookUIState,
)

@HiltViewModel
class BookViewModel
    @Inject
    constructor(
        private val bookRepository: BookRepository,
    ) : ViewModel() {
        private val _uiState =
            MutableStateFlow(
                BookViewUIState(
                    BookUIState.Loading,
                ),
            )
        val uiState = _uiState.asStateFlow()

        init {
            loadBook()
        }

        fun loadBook() {
            val books = bookRepository.getBooks()
            _uiState.value =
                _uiState.value.copy(
                    bookState = BookUIState.Success(books),
                )
        }
    }
