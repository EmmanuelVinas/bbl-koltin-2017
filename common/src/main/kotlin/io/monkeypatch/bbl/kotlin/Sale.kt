package io.monkeypatch.bbl.kotlin

data class Sale(val products: MutableList<Product> = mutableListOf(),
                val timeStamp: TimeStamp){


    fun productWithPrice0(): List<Product>{
        val products0  = mutableListOf<Product>()
        for (product in products){
            if (product.price == 0.0){
                products.add(product)
            }
        }
        return products0
    }

    fun productWith0Functional() : List<Product> =
        products.filter { it.price == 0.0 }

    fun totalAmount(): Double = products.sumByDouble { it.totalPrice() }

    fun countriesWithProductB() : List<Country> {
        return products.filter { it.productType == ProductType.B }
                .map { it.country }
                .filterNotNull()
                .distinct()

    }
}