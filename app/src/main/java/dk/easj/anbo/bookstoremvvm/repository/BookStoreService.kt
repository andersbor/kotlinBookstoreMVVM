package dk.easj.anbo.bookstoremvvm.repository

import dk.easj.anbo.bookstoremvvm.models.Book
import retrofit2.Call
import retrofit2.http.*

interface BookStoreService {
    @GET("books")
    fun getAllBooks(): Call<List<Book>>

    @GET("books/{bookId}")
    fun getBookById(@Path("bookId") bookId: Int): Call<Book>

    @POST("books")
    fun saveBook(@Body book: Book): Call<Book>

    @DELETE("books/{id}")
    fun deleteBook(@Path("id") id: Int): Call<Book>

    @PUT("books/{id}")
    fun updateBook(@Path("id") id: Int, @Body book: Book): Call<Book>
}