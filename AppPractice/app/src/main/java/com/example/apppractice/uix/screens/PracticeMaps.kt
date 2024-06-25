package com.example.apppractice.uix.screens

import android.Manifest
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.apppractice.uix.vm.MRoutesViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PracticeMaps(modifier: Modifier = Modifier, nav: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = "Crear ruta",
                    color = Color.White,
                    fontWeight = FontWeight.Bold)
            },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.DarkGray
                ))
        },
        content = { paddingValues -> // important ->
            MapsContentPermissions(modifier, nav = nav, paddingValues = paddingValues)
        }
    )
}

@Composable
fun MapsContentPermissions(modifier: Modifier,
                           nav: NavController,
                           paddingValues: PaddingValues) {

    val context = LocalContext.current

    val permissionsX = if(Build.VERSION.SDK_INT >= 33){
        listOf(
            //Manifest.permission.READ_MEDIA_VIDEO,
            //Manifest.permission.READ_MEDIA_IMAGES,
            //Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.NEARBY_WIFI_DEVICES,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }else{
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            //Manifest.permission.WRITE_EXTERNAL_STORAGE,
            //Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    var permissionsGranted by remember {
        mutableStateOf(
            permissionsX.all {
                ContextCompat
                    .checkSelfPermission(context, it) == PermissionChecker.PERMISSION_GRANTED
            }
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissionsGranted = permissions.values.all { it }
    }

    if (permissionsGranted) {
        MapsContent(context, modifier, nav = nav, paddingValues = paddingValues)
    }else{
        LaunchedEffect(key1 = Unit) {
            launcher.launch(permissionsX.toTypedArray())
        }
    }
}

@Composable
fun MapsContent(context: Context,
                modifier: Modifier,
                nav: NavController,
                paddingValues: PaddingValues,
                vmRoutes: MRoutesViewModel = hiltViewModel()) {

    ConstraintLayout(
        modifier
            .fillMaxSize()
            .background(Color.DarkGray)) {

        val (idTxtHead, idFTextOrigin,
            idFTextDestinate, idBtnSearch,
            idGmaps) = createRefs()

        var txtUser by remember { mutableStateOf("") }
        var txtPassword by remember { mutableStateOf("") }

        val focusManager = LocalFocusManager.current
        val listLatLng by vmRoutes.vmlistCord.collectAsState()

        val origin by vmRoutes.oriMarker.collectAsState()
        val setInput = remember { mutableStateOf("") }

        Log.d("LIST_ROUTES", origin.toString())

        val oriMarker = vmRoutes.oriMarker.value
        val destMarker = vmRoutes.destMarker.value

        Log.d("VM_CORDI",  oriMarker.toString())

        Text(
            modifier = modifier
                .padding(paddingValues)
                .constrainAs(idTxtHead) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                },
            text = "Ingrese inicio y final de la ruta",
            textAlign = TextAlign.Center,
            color = Color.White,
            //fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(0.dp))

        OutlinedTextField(
            value = txtUser,
            onValueChange = { txtUser = it },
            label = { Text("Lugar de inicio", color = Color.White, fontSize = 12.sp) },
            maxLines = 1,
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(idFTextOrigin) {
                    top.linkTo(idTxtHead.bottom, margin = 0.dp)
                    start.linkTo(parent.start, margin = 5.dp)
                    end.linkTo(idFTextDestinate.start, margin = 0.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        )

        OutlinedTextField(
            value = txtPassword,
            onValueChange = { txtPassword = it },
            label = { Text("Lugar de destino", color = Color.White, fontSize = 12.sp) },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
            modifier = Modifier
                .padding(10.dp)
                .constrainAs(idFTextDestinate) {
                    top.linkTo(idTxtHead.bottom, margin = 0.dp)
                    start.linkTo(idFTextOrigin.end, margin = 0.dp)
                    end.linkTo(parent.end, margin = 5.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        )

        Button(
            onClick = {

                if (txtUser.isNotEmpty() && txtPassword.isNotEmpty()) {

                    vmRoutes.setOriAndDest(txtUser, txtPassword)

                    //txtUser = ""
                    //txtPassword = ""

                }else{
                    Toast
                        .makeText(
                            context,
                            "Debe ingresar inicio y destino",
                            Toast.LENGTH_LONG
                        ).show()
                }
            },
            modifier = Modifier
                .padding(vertical = 2.dp)
                .constrainAs(idBtnSearch) {
                    top.linkTo(idFTextDestinate.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.matchParent
                    height = Dimension.preferredValue(40.dp)
                },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
        ) {
            Text("Crear ruta ", color = Color.Black)
        }


        val cameraPositionState = rememberCameraPositionState {
            //position = CameraPosition.fromLatLngZoom(oriMarker, 15f)
        }

        val uiSettings by remember {
            mutableStateOf(MapUiSettings(zoomControlsEnabled = true))
        }
        val properties by remember {
            mutableStateOf(MapProperties(mapType = MapType.NORMAL,
                                         isMyLocationEnabled = true))
        }

        GoogleMap(
            modifier = modifier
                //.fillMaxSize()
                .constrainAs(idGmaps) {
                    top.linkTo(idBtnSearch.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 0.dp)
                    end.linkTo(parent.end, margin = 0.dp)
                    bottom.linkTo(parent.bottom, margin = 0.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                },
            properties = properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = oriMarker),
                title = "Origen",
                snippet = "Marker origen"
            )
            Marker(
                state = MarkerState(position = destMarker),
                title = "Destinate",
                snippet = "Marker destino"
            )

            val itemX: MutableList<LatLng> = mutableListOf()

            /*if (routes.value.features.isNotEmpty()) {
                routes.value.features.first()
                    .geometry.coordinates.forEach { item ->

                    if (item.isNotEmpty()) {
                        Log.d("OBJ_ROUTES ->", item.toString())
                        itemX.add(LatLng(item[1], item[0]))
                    }
                }
                Polyline(
                    points = itemX,
                    color = Color.Green
                )
            }*/
            Polyline(
                points = listLatLng,
                color = Color.Green
            )
        }
    }
}


