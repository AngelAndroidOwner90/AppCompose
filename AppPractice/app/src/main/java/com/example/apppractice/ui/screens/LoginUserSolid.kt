package com.example.apppractice.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.apppractice.model.LogUserEntyModel
import com.example.apppractice.model.OptionMenuModel
import com.example.apppractice.ui.LogUserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUserSolid(modifier: Modifier, nav: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    modifier = modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = "Login",
                    color = Color.White,
                    fontWeight = FontWeight.Bold)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.DarkGray
            ))
        },
        content = { paddingValues -> // important ->
            LoginContent(modifier, paddingValues = paddingValues)
        }
    )
}

@Composable
fun LoginContent(modifier: Modifier = Modifier,
                 paddingValues: PaddingValues,
                 vmLogin: LogUserViewModel= hiltViewModel()) {

    ConstraintLayout(
        modifier
            .fillMaxSize()
            .background(Color.DarkGray)) {

        val (idTxtHead, idFTextFUser,
            idFTextFPassword, idBtnLogin,
            idBtnRegister) = createRefs()

        val context = LocalContext.current
        vmLogin.vmGetAllUsers()

        val listUser = vmLogin.listUsers.collectAsState()

        var txtUser by remember { mutableStateOf("") }
        var txtPassword by remember { mutableStateOf("") }

        val focusManager = LocalFocusManager.current

        Text(
            modifier = modifier
                .padding(paddingValues)
                .constrainAs(idTxtHead) {
                    top.linkTo(parent.top, margin = 40.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.matchParent
                    height = Dimension.wrapContent

                },
            text = "Ingrese usuario y contraseña",
            textAlign = TextAlign.Center,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = txtUser,
            onValueChange = { txtUser = it },
            label = { Text("Usuario", color = Color.White,) },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
            modifier = Modifier
                .padding(vertical = 2.dp)
                .constrainAs(idFTextFUser) {
                    top.linkTo(idTxtHead.bottom, margin = 40.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.matchParent
                }
        )

        OutlinedTextField(
            value = txtPassword,
            onValueChange = { txtPassword = it },
            label = { Text("Contraseña", color = Color.White,) },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Text),
            modifier = Modifier
                .padding(vertical = 2.dp)
                .constrainAs(idFTextFPassword) {
                    top.linkTo(idFTextFUser.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.matchParent
                }
        )

        Button(
            onClick = {

                if (txtUser.isNotEmpty() && txtPassword.isNotEmpty()) {

                    vmLogin.vmAddNewUser(LogUserEntyModel(null, txtUser, txtPassword))
                    txtUser = ""
                    txtPassword = ""
                }else{
                    Toast
                        .makeText(
                            context,
                            "Debe ingresar una pregunta",
                            Toast.LENGTH_LONG
                        ).show()
                }
            },
            modifier = Modifier
                .padding(vertical = 2.dp)
                .constrainAs(idBtnLogin) {
                    top.linkTo(idFTextFPassword.bottom, margin = 20.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.matchParent
                },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
        ) {
            Text("Iniciar", color = Color.Black)
        }

        Button(
            onClick = {

                if (txtUser.isNotEmpty() && txtPassword.isNotEmpty()) {

                    vmLogin.vmLoginInUsers(txtUser, txtPassword)
                    txtUser = ""
                    txtPassword = ""

                    Toast
                        .makeText(
                            context,
                            "Acceso valido",
                            Toast.LENGTH_LONG
                        ).show()
                }else{
                    Toast
                        .makeText(
                            context,
                            "Usuario o contraseña incorrecto",
                            Toast.LENGTH_LONG
                        ).show()
                }
            },
            modifier = Modifier
                .padding(vertical = 2.dp)
                .constrainAs(idBtnRegister) {
                    top.linkTo(idBtnLogin.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.matchParent
                },
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF))
        ) {
            Text("Registrarse", color = Color.Black)
        }

        Log.d("LST_USER", listUser.toString())
    }
}
