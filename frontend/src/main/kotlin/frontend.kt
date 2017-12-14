import io.monkeypatch.bbl.kotlin.*
import kotlinx.html.div
import kotlinx.html.dom.append
import org.w3c.dom.HTMLElement
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.browser.window
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.EmptyCoroutineContext
import kotlin.coroutines.experimental.startCoroutine
import kotlin.coroutines.experimental.suspendCoroutine
import kotlin.js.Promise



val sale = Sale(timeStamp = TimeStamp())

suspend fun httpGet(url: String): String = suspendCoroutine { c ->
    val xhr = XMLHttpRequest()
    xhr.onreadystatechange = {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status / 100 == 2) {
                c.resume(xhr.response as String)
            }
            else {
                c.resumeWithException(RuntimeException("HTTP error: ${xhr.status}"))
            }
        }
        null
    }
    xhr.open("GET", url)
    xhr.send()
}

fun <T> async(x: suspend () -> T): Promise<T> {
    return Promise { resolve, reject ->
        x.startCoroutine(object : Continuation<T> {
            override val context = EmptyCoroutineContext

            override fun resume(value: T) {
                resolve(value)
            }

            override fun resumeWithException(exception: Throwable) {
                reject(exception)
            }
        })
    }
}

fun fetchNewProducts() {

    async {

        val data = httpGet("http://localhost:8080/newProduct")
        val prod = Product.fromJSON(data)
        console.log("Fetching new product : "+ prod)
        val contentDiv = document.getElementsByClassName("content").item(0) as HTMLElement
        contentDiv.append.div {
            renderProduct(prod)
        }
        val totalDiv = document.getElementsByClassName("total-value").item(0) as HTMLElement
        sale.products.add(prod)
        totalDiv.textContent = "${sale.totalAmount()} €"
    }
}

fun main(args: Array<String>) {
    console.log("Frontend loaded")
    fetchNewProducts()
    window.setInterval(::fetchNewProducts, 5000)
}
