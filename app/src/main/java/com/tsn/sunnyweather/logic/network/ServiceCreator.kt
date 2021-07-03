package com.tsn.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 第三步：创建Retrofit构建器
 * 获取AppService接⼝的动态代理对象：
 * val placeService = ServiceCreator.create<PlaceService>()
 */
object ServiceCreator { //用object使ServiceCreator成为一个单例类
    //⽤于指定Retrofit的根路径
    private const val BASE_URL = "https://api.caiyunapp.com/"
    //使⽤Retrofit.Builder构建⼀个Retrofit对象,
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    //⼀个外部可⻅的create()⽅法，并接收⼀个Class类型的参数
    fun <T> create(serviceClass: Class<T>): T =
        retrofit.create(serviceClass)
    //⼜定义了⼀个不带参数的create()⽅法
    inline fun <reified T> create(): T = create(T::class.java)

}