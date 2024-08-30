package com.example.mdindiatask

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("getLocationList")
    fun getHospitalList(
        @Query("statename") stateName: String,
        @Query("district") district: String,
        @Query("cityname") cityName: String,
        @Query("MobileUniqId") mobileUniqueId: String
    ): Call<HospitalResponse>
}
object RetrofitInstance {
    private const val BASE_URL = "http://mdiserviceweb.mdindia.com:8060/CommonAPIServiceWeb/WCFCommonServices.svc/rest/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}