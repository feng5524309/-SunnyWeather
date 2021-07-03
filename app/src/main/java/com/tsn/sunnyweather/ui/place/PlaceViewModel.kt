package com.tsn.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.tsn.sunnyweather.logic.Repository
import com.tsn.sunnyweather.logic.model.Place

/**
 * ViewModel相当于逻辑层和UI层之间的⼀个桥梁
 * 原则上与界⾯相关的数据都应该放到ViewModel中，
 * 这样可以保证它们在⼿机屏幕发⽣旋转的时候不会丢失
 */
class PlaceViewModel:ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    //⽤于对界⾯上显⽰的城市数据进⾏缓存
    val placeList = ArrayList<Place>()

    //使⽤Transformations的switchMap()⽅法来观察searchLiveData，否则仓库层返回的LiveData对象将⽆法进⾏观察
    val placeLiveData = Transformations.switchMap(searchLiveData) {
        //匹配值->执行逻辑
            query ->
        Repository.searchPlaces(query)
    }
    //定义方法searchPlaces，并将输入值query赋值给searchLiveData.value
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}