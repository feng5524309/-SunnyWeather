package com.tsn.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

/**
 * 第一步：定义数据模型，类似于实体类吧
 */

//status： 请求状态是否成功； place：查询的某一城市的相关地区
data class PlaceResponse(val status:String,val places:List<Place>)
//name：该地区的名字； location：表⽰该地区的经纬度； address：该地区的地址
data class Place(val id:String, val name: String,val location:Location,
                 @SerializedName("formatted_address")val address:String)
//lng和lat为经纬度
data class Location(val lng:String,val lat:String)