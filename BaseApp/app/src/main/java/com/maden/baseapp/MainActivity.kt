package com.maden.baseapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.Serializable
import java.util.*
import kotlin.concurrent.timerTask


class MainActivity : AppCompatActivity(), Serializable {

    private val REQUEST_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent.extras?.let { bundle ->
            try {
                val userRequest = bundle.get("USER_REQUEST").toString()
                val getUser: Boolean? = userRequest.toBoolean() ?: false
                getUser?.let {
                    if (it) {
                        //Await...
                        val timer = Timer()
                        timer.schedule(timerTask { getUser()}, 1000)
                    }
                }
            } catch (e: Exception) {
                // -> Handle Exception
            }
        } ?: run {  println("fail")}
    }

    private fun getUser() {
        val intent = Intent()
        with(GetUser()) {
            val userModel = Gson().toJson(getUser())
            intent.putExtra("USER_MODEL", userModel)
        }
        setResult(REQUEST_CODE, intent)
        finish()
    }
}

