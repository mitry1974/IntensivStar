package ru.androidschool.intensiv.data.api

import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import ru.androidschool.intensiv.data.api.responses.GenericResponse
import ru.androidschool.intensiv.util.Constants

open class BaseRemoteRepository<T> {
    open fun getResult(call: () -> Observable<Response<T>>): Observable<T> =
        call()
            .doOnError { e -> error(e.message ?: e.toString()) }
            .map { response ->
                if (response.isSuccessful) {
                    return@map response.body()
                } else if (response.errorBody() != null) {
                    val errorBody = getErrorBody(response.errorBody())
                    throw Exception(errorBody?.message ?: Constants.GENERIC_ERROR)
                }
                throw Exception(Constants.GENERIC_ERROR)
            }

    private fun getErrorBody(responseBody: ResponseBody?): GenericResponse? =
        try {
            Gson().fromJson(responseBody?.charStream(), GenericResponse::class.java)
        } catch (e: Exception) {
            null
        }
}
