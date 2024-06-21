package com.example.apppractice.data

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.example.apppractice.R
import com.example.apppractice.model.MRoutesResponseModel
import com.example.apppractice.model.OriginDestinateModel
import com.google.android.gms.maps.model.LatLng
import java.util.Locale
import javax.inject.Inject

class MapsOrsRepository @Inject constructor(private val service: IApiService,
                                            private val context: Context)  {

    // Object
    var getResApi: MRoutesResponseModel? = null
    var getMessage: String? = null
    var setMessageIsNull: String = "Error de Api"

    var oriMarker: LatLng? = null
    var destMarker: LatLng? = null

    suspend fun setOriAndDestRoute(setOrigin: String, setDestinate: String): ApiResources<MutableList<OriginDestinateModel>>  {

        val addressRoute: MutableList<OriginDestinateModel> = mutableListOf()

        val geocoder = Geocoder(context, Locale.getDefault())
        val addressOrigin = geocoder.getFromLocationName(setOrigin, 1)
        val addressDestinate = geocoder.getFromLocationName(setDestinate, 1)

        if (addressOrigin?.isNotEmpty() == true && addressDestinate?.isNotEmpty() == true) {

            val startLatLng = LatLng(addressOrigin[0].latitude, addressOrigin[0].longitude)
            val endLatLng = LatLng(addressDestinate[0].latitude, addressDestinate[0].longitude)

            val originX = String().plus(startLatLng.longitude).plus(",").plus(startLatLng.latitude)
            val destinateX = String().plus(endLatLng.longitude).plus(",").plus(endLatLng.latitude)

            oriMarker = LatLng(startLatLng.latitude, startLatLng.longitude)
            destMarker = LatLng(endLatLng.latitude, endLatLng.longitude)

            if (originX.isNotEmpty() && destinateX.isNotEmpty()) {

                addressRoute.add(OriginDestinateModel(originX, destinateX, oriMarker!!, destMarker!!))

                Log.d("VM_LIST", addressRoute[0].toString())
            }

        }else{
            return ApiResources.Error("Error, no origen o destino")
        }
        return ApiResources.Success(addressRoute)
    }

    suspend fun getRoute(start: String, end: String): ApiResources<MRoutesResponseModel> {

        try {

            val response = service.getRoute(context.getString(R.string.gm_ors_api_key), start, end)
            val mResponse = response.body()

            if (response.isSuccessful && mResponse != null) {

                getResApi = mResponse
                println("RESPONSE_API - $getResApi")

            }else{
                getResApi = MRoutesResponseModel(listOf())
                return ApiResources.Error("Error de Api, ${response.errorBody()}")
            }

        }catch (error: Exception) {
            getMessage = error.message.toString()
            println("RESPONSE_API -> ${getMessage?: setMessageIsNull}")
            return ApiResources.Error("Error de Api, ${getMessage?: setMessageIsNull}")
        }
        return ApiResources.Success(getResApi)
    }
}