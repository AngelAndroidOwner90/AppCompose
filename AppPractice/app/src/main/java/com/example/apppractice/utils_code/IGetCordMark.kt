package com.example.apppractice.utils_code

import com.google.android.gms.maps.model.LatLng

interface IGetCordMark {
    fun getLatMark(): LatLng
    fun getLonMark(): LatLng
}