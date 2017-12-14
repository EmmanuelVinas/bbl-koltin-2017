package io.monkeypatch.bbl.kotlin

import kotlinx.serialization.Serializable

enum class ProductType {
    A, B, C
}

@Serializable
data class Country(val name:String)

@Serializable
class Product(val title: String, val price: Double, var quantity: Int? = 1,
              val productType: ProductType? = null,
              val country: Country? = null){

    companion object

    fun totalPrice() = quantity?.toDouble() * price


    fun printType():String {
       return when(productType){
            ProductType.A, ProductType.C -> "The product is A or C"
            ProductType.B -> when(price){
                in 0.0..20.0 -> "the product is B with price 0..20"
                else -> "the product is B"
            }

           else -> {
               "the product is Not defined"
           }
       }
    }

}