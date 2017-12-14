package app.example.com.kotlin

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * 网络接口数据的请求类
 *   http://japi.juhe.cn/comic/book?key=f54c4c57143b8fad9bf3193cab52a81c
 */
interface GetDataInterface {

    @GET("/comic/book?key=f54c4c57143b8fad9bf3193cab52a81c")
    fun getData() : Observable<ResponseBody>

}