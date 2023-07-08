package com.app.weatherapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherData{
    @SerializedName("cod")
    val cod: String? = null
    @SerializedName("message")
    var message: Long? = null
    @SerializedName("cnt")
    val cnt: Long = 7
    @SerializedName("list")
    val list: List<Forecast> = emptyList()
}

class Forecast{
    @SerializedName("dt")
    @Expose
    val dt: Float? = null

    @SerializedName("main")
    @Expose
    val main: Main? = null

    @SerializedName("weather")
    @Expose
    val weather: List<Weather>? = null

    @SerializedName("clouds")
    @Expose
    val clouds: Clouds? = null

    @SerializedName("wind")
    @Expose
    val wind: Wind? = null

    @SerializedName("visibility")
    @Expose
    val visibility: Float? = null

    @SerializedName("pop")
    @Expose
    val pop: Float? = null

    @SerializedName("dt_txt")
    @Expose
    val dtTxt: String? = null
}

class Main{
    @SerializedName("temp")
    @Expose
    val temp: Double? = null

    @SerializedName("feels_like")
    @Expose
    val feelsLike: Double? = null

    @SerializedName("temp_min")
    @Expose
    val tempMin: Double? = null

    @SerializedName("temp_max")
    @Expose
    val tempMax: Double? = null

    @SerializedName("pressure")
    @Expose
    val pressure: Float? = null

    @SerializedName("sea_level")
    @Expose
    val seaLevel: Float? = null

    @SerializedName("grnd_level")
    @Expose
    val grndLevel: Float? = null

    @SerializedName("humidity")
    @Expose
    val humidity: Float? = null
}

class Weather{
    @SerializedName("id")
    @Expose
    val id: Float? = null

    @SerializedName("main")
    @Expose
    val main: String? = null

    @SerializedName("description")
    @Expose
    val description: String? = null

    @SerializedName("icon")
    @Expose
    val icon: String? = null
}

class Clouds
{
    @SerializedName("all")
    @Expose
    val all: Float? = null
}
class Wind {
    @SerializedName("speed")
    @Expose
    val speed: Double? = null

    @SerializedName("deg")
    @Expose
    val deg: Float? = null

    @SerializedName("gust")
    @Expose
    val gust: Double? = null
}

 class City{
     @SerializedName("id")
     @Expose
     val id: Float? = null

     @SerializedName("name")
     @Expose
     val name: String? = null

     @SerializedName("country")
     @Expose
     val country: String? = null

     @SerializedName("population")
     @Expose
     val population: Float? = null

     @SerializedName("timezone")
     @Expose
     val timezone: Float? = null

     @SerializedName("sunrise")
     @Expose
     val sunrise: Float? = null

     @SerializedName("sunset")
     @Expose
      val sunset: Float? = null
}