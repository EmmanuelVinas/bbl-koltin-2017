package io.monkeypatch.bbl.kotlin

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.content.resources
import io.ktor.content.static
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import java.util.*

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(StatusPages) {
        exception<Throwable> { cause ->
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
    install(Routing) {
        get("/") {
            println("Rendering index page")
            call.respondText(renderIndexPage(), ContentType.Text.Html)
        }
        get("/newProduct") {
            println("Rendering newProduct")
            call.respondText(serializeNewProducts(), ContentType.Application.Json)
        }
        static("static/js") {
            println("Rendering js")
            resources("js")
        }
        static("static/css") {
            println("Rendering css")
            resources("css")
        }
    }
    val port = environment.config.config("ktor").config("deployment").property("port").getString()
    println("Backend running on http://localhost:$port")
}

private fun renderIndexPage(): String {

    val products = generateRandomProducts(3)
    println("renderIndexPage")
    val result =  createHTML().html {
        head {
            script(src = "/static/js/frontend.bundle.js") {}
            styleLink("/static/css/style.css")
        }
        body {
            div(classes = "total"){
                text("Total : ")
                span(classes = "total-value"){
                    text("0.0")
                }
            }
            div("content") {

            }
        }
    }
    println("Rendering result: $result")
    return result
}

private fun serializeNewProducts(): String {
    val product = generateRandomProducts(1)
    return product[0].toJSON()
}

fun generateRandomProducts(count: Int): List<Product> {
    return List(count) {
        Product(generateRandomProducts(), generateRandomPrices(), Random().nextInt(10).coerceAtLeast(1))
    }
}

val prices = listOf(10.0, 20.0, 15.5, 30.20)

val products = listOf("Men's Curtys Penny Loafer", "Ray-Ban Sunglasses", "Telescopes", "Mug", "Coffee", "Beer", "House")

fun generateRandomProducts() =
        products[Random().nextInt(products.size)]

fun generateRandomPrices() =
        prices[Random().nextInt(prices.size)]
