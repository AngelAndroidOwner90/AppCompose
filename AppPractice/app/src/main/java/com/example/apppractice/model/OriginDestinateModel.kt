package com.example.apppractice.model

import com.google.android.gms.maps.model.LatLng

data class OriginDestinateModel(
    var origin: String,
    var destinate: String,
    var originMarker: LatLng,
    var destinateMarker: LatLng
)
