import kotlinx.cinterop.*
import platform.Foundation.*

typealias ErrorHandler = (NSError) -> Unit

private enum class NetworkServiceError {
    JSON_PARSING_ERROR, EMPTY_BODY, WRONG_STATUS_CODE;
    fun toNSError(): NSError = NSError("NetworkService", code = ordinal.toLong(), userInfo = null)
}

class NetworkService {
    val session = NSURLSession.sessionWithConfiguration(NSURLSessionConfiguration.defaultSessionConfiguration,
            delegate = null, delegateQueue = NSOperationQueue.mainQueue)

    fun jsonRequest(request: NSURLRequest, handler: (NSDictionary) -> Unit, errorHandler: ErrorHandler) {
        println("Sending JSON request: " + request.URL)

        session.dataTaskWithRequest(request) { data, _, error ->
            assert(NSThread.isMainThread)

            if (error != null) {
                errorHandler(error)
                return@dataTaskWithRequest
            }

            if (data == null) {
                errorHandler(NetworkServiceError.EMPTY_BODY.toNSError())
                return@dataTaskWithRequest
            }

            val deserializedData = NSJSONSerialization.JSONObjectWithData(data, options = 0, error = null)
            if (deserializedData != null) {
                handler(deserializedData.reinterpret<NSDictionary>())
            } else {
                errorHandler(NetworkServiceError.JSON_PARSING_ERROR.toNSError())
            }
        }.resume()
    }
}

