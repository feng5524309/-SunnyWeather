package com.tsn.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 第四步：定义⼀个统⼀的⽹络数据源访问⼊⼝，对所有⽹络请求的API进⾏封装
 */
object SunnyWeatherNetwork {
    //创建了⼀个PlaceService接⼝的动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>()
    //调⽤刚刚在PlaceService接⼝中定义的searchPlaces()⽅法，以发起搜索城市数据请求
    suspend fun searchPlaces(query: String) =
        placeService.searchPlaces(query).await()

    /**
     * 为了简洁，定义了⼀个await()函数，并将searchPlaces()函数也声明成挂起函数
     * await()函数会将解析出来的数据模型对象取出并返回，同时恢复当前协程的执⾏，
     * searchPlaces()函数在得到 await()函数的返回值后会将该数据再返回到上⼀层
     */
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response:
                Response<T>
                ) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}