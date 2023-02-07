
package com.example.myapplication

class Tutorial(
    val name: String,
    val img: String,
    val category: String,
    val price:Double
) {
    override fun toString(): String {
        return "Category [name: ${this.name}, price: ${this.price}, category: ${this.category},img: ${this.img}]"
    }
}