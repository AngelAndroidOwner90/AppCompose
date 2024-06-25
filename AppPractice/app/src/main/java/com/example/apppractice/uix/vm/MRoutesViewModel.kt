package com.example.apppractice.uix.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppractice.data.ApiResources
import com.example.apppractice.data.MapsOrsRepository
import com.example.apppractice.model.MRoutesResponseModel
import com.example.apppractice.utils_code.IGetCordMark
import com.example.apppractice.utils_code.ObjUtilsCode
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MRoutesViewModel
    @Inject constructor(private val repository: MapsOrsRepository) : ViewModel(), IGetCordMark {

    val itemX: MutableList<LatLng> = mutableListOf()

    private val _vmListCord = MutableStateFlow(mutableListOf<LatLng>())
    val vmlistCord: StateFlow<MutableList<LatLng>> = _vmListCord.asStateFlow()

    //----------------------------------------------------------------------------------------------

    private val _vmOrigin = MutableStateFlow("")
    val vmOrigin: StateFlow<String> = _vmOrigin.asStateFlow()

    private val _vmDestinate = MutableStateFlow("")
    val vmDestinate: StateFlow<String> = _vmDestinate.asStateFlow()

    private val _vmOriMarker = MutableStateFlow(LatLng(0.0, 0.0))
    val oriMarker: StateFlow<LatLng> = _vmOriMarker.asStateFlow()

    private val _vmDestMarker = MutableStateFlow(LatLng(0.0, 0.0))
    val destMarker: StateFlow<LatLng> = _vmDestMarker.asStateFlow()

    var oriMarkerX: LatLng? = null
    var destMarkerX: LatLng? = null

    private val _getObjMapRoutes = MutableStateFlow(MRoutesResponseModel(listOf()))
    val mapRoutes: StateFlow<MRoutesResponseModel> get() = _getObjMapRoutes


    private val _shwPb = MutableStateFlow(false)
    val shwPb: StateFlow<Boolean> get() = _shwPb

    private val _msgApiVm = MutableStateFlow("")
    val msgApiVm: StateFlow<String> get() = _msgApiVm

    // Set orig and dest
    fun setOriAndDest(origin: String, destinate: String) {

        _shwPb.value = true

        viewModelScope.launch(Dispatchers.IO) {

            val response = repository.setOriAndDestRoute(origin, destinate)

            when(response) {

                is ApiResources.Success -> {
                    _shwPb.value = false
                    val result = response.data

                    _vmOrigin.value = result?.get(0)?.origin?: ""
                    _vmDestinate.value = result?.get(0)?.destinate?: ""

                    /*_vmOriMarker.value*/oriMarkerX = result?.get(0)?.originMarker?: LatLng(0.0, 0.0)
                    /*_vmDestMarker.value*/destMarkerX = result?.get(0)?.destinateMarker?: LatLng(0.0, 0.0)

                    ObjUtilsCode.latMarker = oriMarkerX
                    Log.d("VM_CORDI ->",  oriMarkerX.toString())
                    Log.d("VM_CORDI ->",  _vmDestMarker.value.toString())

                    setCordMap(vmOrigin.value, vmDestinate.value, oriMarker.value, destMarker.value)
                }

                is ApiResources.Error -> {
                    _shwPb.value = false
                    _getObjMapRoutes.value = MRoutesResponseModel(listOf())
                    val msg = response.message.toString()
                    //_messageApi.value = "error"
                }
                else -> {}
            }
        }
    }

    override fun getLatMark(): LatLng {
       return oriMarkerX?: LatLng(0.0, 0.0)
    }

    override fun getLonMark(): LatLng {
       return destMarkerX?: LatLng(0.0, 0.0)
    }

    fun setCordMap(lat: String, lon: String, originMark: LatLng, destinateMark: LatLng) {

        _shwPb.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRoute(lat, lon)

            when(response) {

                is ApiResources.Success -> {
                    _shwPb.value = false
                    val result = response.data
                    Log.d("VM_RES ->",  result.toString())


                    if (result?.features!!.isNotEmpty()) {
                        result.features.first()
                            .geometry.coordinates.forEach { item ->

                                if (item.isNotEmpty()) {
                                    Log.d("OBJ_ROUTES ->", item.toString())
                                    itemX.add(LatLng(item[1], item[0]))
                                    _vmListCord.value = itemX
                                }
                            }
                    }

                    _vmOriMarker.value = originMark
                    _vmDestMarker.value = destinateMark

                    _getObjMapRoutes.value = result?: MRoutesResponseModel(listOf())
                }
                is ApiResources.Error -> {
                    _shwPb.value = false
                    _getObjMapRoutes.value = MRoutesResponseModel(listOf())
                    val msg = response.message.toString()
                    //_messageApi.value = "error"
                }
                else -> {}
            }
        }
    }
}