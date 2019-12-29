package karleev.sergey.userslist.core.remote

import retrofit2.Response

class LiveResponse<T>(result: Result<Response<T>>) {
    init {
        val onFailure: (Throwable) -> Unit = { error = it }

        val onSuccess: (Response<T>) -> Unit = {
            if (it.isSuccessful)
                model = it.body()
            else
                error = Throwable(it.errorBody()?.string() ?: "Undefined error")
        }

        result.fold(onSuccess, onFailure)
    }

    var model: T? = null
    var error: Throwable? = null

    val type: LiveResponseType
        get() = when {
            model != null -> LiveResponseType.SUCCESS
            error != null -> LiveResponseType.ERROR
            else -> LiveResponseType.EMPTY
        }

    fun clear() = kotlin.run { model = null; error = null }


    companion object {
        fun <T> fromSuccess(model: T): LiveResponse<T> {
            return LiveResponse(kotlin.runCatching { Response.success(model) })
        }
    }
}

fun <T> LiveResponse<T>?.isSuccess() = (this?.type == LiveResponseType.SUCCESS)
fun <T> LiveResponse<T>?.isError() = (this?.type == LiveResponseType.ERROR)
fun <T> LiveResponse<T>?.isEmpty() = this?.let { it.type == LiveResponseType.EMPTY } ?: true

enum class LiveResponseType {
    SUCCESS, ERROR, EMPTY;
}
