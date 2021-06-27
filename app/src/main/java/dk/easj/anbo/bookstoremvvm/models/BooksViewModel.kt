package dk.easj.anbo.bookstoremvvm.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dk.easj.anbo.bookstoremvvm.repository.BooksRepository

class BooksViewModel : ViewModel() {
    private val repository = BooksRepository()
    val booksLiveData: LiveData<List<Book>> = repository.booksLiveData
    val errorMessageLiveData: LiveData<String> = repository.errorMessageLiveData
    val updateMessageLiveData: LiveData<String> = repository.updateMessageLiveData

    init {
        reload()
    }

    fun reload() {
        repository.getPosts()
    }

    operator fun get(index: Int): Book? {
        return booksLiveData.value?.get(index)
    }

    fun add(book: Book) {
         repository.add(book)
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun update(book: Book) {
        repository.update(book)
    }
}