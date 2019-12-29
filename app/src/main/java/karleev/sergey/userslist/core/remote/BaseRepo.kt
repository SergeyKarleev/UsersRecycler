package karleev.sergey.userslist.core.remote

import karleev.sergey.userslist.core.remote.models.response.BaseResponseModel
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

/**
 * Created by Sergey Karleev on 29.05.2019.
 */
open class BaseRepo : CoroutineScope {
    private var job = Job()
        private set

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    protected fun <T : BaseResponseModel> sendRequest(
        restInterface: Deferred<Response<T>>,
        callback: (LiveResponse<T>) -> Unit
    ) = launch {
        val result = runCatching { restInterface.await() }
        withContext(Dispatchers.Main) { callback.invoke(LiveResponse(result)) }
    }

    fun cancel() {
        job.cancel()
        job = Job()
    }

    protected fun main(function: suspend CoroutineScope.() -> Unit) = MainScope().launch(block = function)
}