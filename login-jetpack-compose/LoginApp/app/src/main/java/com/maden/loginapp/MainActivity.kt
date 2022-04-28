package com.maden.loginapp


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maden.loginapp.ui.theme.LoginAppTheme
import java.util.*
import kotlin.concurrent.timerTask

val REQUEST_CODE = 1000

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DefaultPreview()
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LoginAppTheme {
        Column(
            Modifier.fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            loginWithMadenButton()
        }
    }
}

@Composable
fun loginWithMadenButton() {
    var visible by remember { mutableStateOf(true) }
    var userModelRemember: User? by remember { mutableStateOf(null) }

    val startForResult =
        rememberLauncherForActivityResult(
            ActivityResultContracts
                .StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == REQUEST_CODE) {
                val intent = result.data
                val userString: String? = intent?.getStringExtra("USER_MODEL")

                val gson = Gson()
                val type = object : TypeToken<User?>() {}.type
                val userModel: User = gson.fromJson(userString, type)
                userModelRemember = userModel
                visible = false

            }
        }
    if (visible) {
        Button(
            onClick = {
                val intent = Intent("com.maden.baseapp.get_user_info")
                intent.putExtra("USER_REQUEST", true)
                startForResult.launch(intent)
            },
        ) {
            Icon(
                Icons.Default.Favorite,
                contentDescription = "LOGIN WITH MADEN",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("LOGIN WITH MADEN")
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            Text(
                text = returnUserModelString(userModelRemember)
            )
        }
        //...
        val timer = Timer()
        val activity = LocalContext.current
        timer.schedule(timerTask {
            SendBroadcast()
                .sendBroadcast(activity)
        }, 1000)
    }
}




