package com.example.apppractice.ui.screens

import android.content.Context
import android.view.MenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.apppractice.app_navigation.AppScreens
import com.example.apppractice.model.OptionMenuModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuMain(modifier: Modifier, nav: NavController) {
    
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    modifier = modifier
                        .padding(10.dp),
                    text = "Menu",
                    fontWeight = FontWeight.Bold)
            })
        },
        content = { paddingValues ->
            MenuMainContent(modifier, paddingValues, nav)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuMainContent(modifier: Modifier,
                    paddingValues: PaddingValues,
                    nav: NavController?) {

    val context = LocalContext.current

    ConstraintLayout(
        modifier
            .fillMaxSize()
            .background(Color.DarkGray)) {

        val (idTxtHead, idColumnButtons) = createRefs()

        Text(
            modifier = modifier
                .padding(10.dp)
                .constrainAs(idTxtHead) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent
                },
            text = "Elije una opción",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        val listSucursales = arrayOf(

            OptionMenuModel(0, "Login solid"),
            OptionMenuModel(1, "Maps")
        )

        Column(
            modifier
                .padding(10.dp)
                .constrainAs(idColumnButtons) {
                    top.linkTo(idTxtHead.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    //height = Dimension.fillToConstraints
                }
        ) {
            LazyColumn() {
                itemsIndexed(listSucursales) { index, txtOption ->
                    MenuItem(txtBtn = txtOption, index, context, modifier, nav!!)
                }
            }
        }
    }
}

@Composable
fun MenuItem(txtBtn: OptionMenuModel, index: Int,
             context: Context, modifier: Modifier, nhc: NavController) {

    OutlinedButton(
        onClick = {
            setOptionNav(index, nhc)
        },
        modifier
            .fillMaxWidth()
            .padding(10.dp)) {

        Text(text = txtBtn.txtBtnMenu,
             color = Color.White)
    }
}

fun setOptionNav(index: Int, nav: NavController) {
    when(index) {
        0 -> {
            nav.navigate(AppScreens.LoginUserSolid.route)
        }
        1 -> {
            nav.navigate(AppScreens.PracticeMaps.route)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MenuPreview() {
    MenuMainContent(modifier = Modifier, PaddingValues(), null)
}


