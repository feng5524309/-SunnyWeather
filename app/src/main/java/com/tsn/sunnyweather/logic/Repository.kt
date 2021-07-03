package com.tsn.sunnyweather.logic

import androidx.lifecycle.liveData
import com.tsn.sunnyweather.logic.model.Place
import com.tsn.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

/**
 * 仓库层的主要⼯作就是判断调⽤⽅请求的数据应该是从本地数据源中获取
 * 还是从⽹络数据源中获取，并将获得的数据返回给调⽤⽅。
 * 仓库层有点像是⼀个数据获取与缓存的中间层，在本地没有缓存数据的情况下
 * 就去⽹络层获取，如果本地已经有缓存了，就直接将缓存数据返回。
 * ps：Android是不允许在主线程中进⾏⽹络请求的
 */
object Repository {
    //liveData线程参数类型指定成了Dispatchers.IO，这样代码块中的所有代码就都运⾏在⼦线程中了
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            //调用网络数据源访问接口
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") { //服务器响应状态为ok
                val places = placeResponse.places //查询的某一城市的相关地区
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}