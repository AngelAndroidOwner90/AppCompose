package com.example.apppractice.utils_code

import com.google.android.gms.maps.model.LatLng

object ObjUtilsCode {

    var latMarker: LatLng? = null
        get() {
            println("Getter called")
            return field
        }
        set(value) {
            println("Setter called")
            field = value
        }
}