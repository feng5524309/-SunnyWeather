package com.tsn.sunnyweather.logic.network

import com.tsn.sunnyweather.SunnyWeatherApplication
import com.tsn.sunnyweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 第二步：编写⽹络层相关的代码
 * 定义⼀个⽤于访问彩云天⽓城市搜索API的Retrofit接⼝,
 * 要使用Retrofit，要创建一个Retrofit构建器：ServiceCreator单例类
 * val placeService = ServiceCreator.create<PlaceService>()
 */
interface PlaceService {
    /**
     *  当调⽤searchPlaces()⽅法的时候，Retrofit就会⾃动发起⼀条
     *  GET请求，去访问@GET注解中配置的地址
     */
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String):
            Call<PlaceResponse>
}