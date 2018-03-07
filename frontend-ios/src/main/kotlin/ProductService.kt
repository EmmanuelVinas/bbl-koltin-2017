import io.monkeypatch.bbl.kotlin.Product
import kotlinx.cinterop.*
import platform.Foundation.*

class ProductService(val networkService: NetworkService) {
    fun getNewProduct(onSuccess: (Product) -> Unit, errorHandler: ErrorHandler) {
        val url = NSURL(URLString = "http://localhost:8080/newProduct")
        networkService.jsonRequest(NSURLRequest.requestWithURL(url), { obj ->
            onSuccess(
                    Product(obj.stringValueForKey("title")!!,
                            obj.doubleValueForKey("price")!!,
                            obj.intValueForKey("quantity")!!))
        }, errorHandler)
    }

}

private fun NSDictionary.stringValueForKey(key: String): String? =
        this.valueForKey(key)?.let { it.reinterpret<NSString>().toString() }

private fun NSDictionary.intValueForKey(key: String): Int? =
        this.valueForKey(key)?.let { it.reinterpret<NSNumber>().integerValue().toInt() }

private fun NSDictionary.doubleValueForKey(key: String): Double? =
        this.valueForKey(key)?.let { it.reinterpret<NSNumber>().doubleValue().toDouble() }