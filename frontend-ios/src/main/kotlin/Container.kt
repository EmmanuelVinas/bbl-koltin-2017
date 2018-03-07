
object Container {
    val networkService by lazy { NetworkService() }
    val productService by lazy { ProductService(networkService) }
}