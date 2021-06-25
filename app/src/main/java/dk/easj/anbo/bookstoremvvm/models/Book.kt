package dk.easj.anbo.bookstoremvvm.models

data class Book(val id: Int=-1, val author: String, val title: String, val publisher: String, val price: Double) {
    override fun toString(): String {
        return "$id $author: $title, $publisher, $price"
    }
}