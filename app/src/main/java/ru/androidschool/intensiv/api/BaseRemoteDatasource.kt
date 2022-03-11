package ru.androidschool.intensiv.api

import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import ru.androidschool.intensiv.api.model.GenericResponse
import ru.androidschool.intensiv.util.Constants

abstract class BaseRemoteDataSource {
    fun <T> getResult(call: () -> Observable<Response<T>>): Observable<Result<T>> =
        call()
            .doOnError { e -> error(e.message ?: e.toString()) }
            .map { response ->
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) return@map Result.Success(body)
                } else if (response.errorBody() != null) {
                    val errorBody = getErrorBody(response.errorBody())
                    return@map error(errorBody?.message ?: Constants.GENERIC_ERROR)
                }

                return@map error(Constants.GENERIC_ERROR)
            }

    private fun error(message: String): Result.Error = Result.Error(message)

    private fun getErrorBody(responseBody: ResponseBody?): GenericResponse? =
        try {
            Gson().fromJson(responseBody?.charStream(), GenericResponse::class.java)
        } catch (e: Exception) {
            null
        }
}