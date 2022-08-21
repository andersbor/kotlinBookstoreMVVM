package dk.easj.anbo.bookstoremvvm.models

data class Book(val id: Int, val title: String, val price: Double)   {
    constructor(title: String, price: Double) : this(-1, title, price)

    override fun toString(): String {
        return "$id  $title,  $price"
    }
}