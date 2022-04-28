package com.maden.loginapp

import android.content.Context
import android.content.Intent

class SendBroadcast {
    fun sendBroadcast(context: Context){
        //Broadcast
        val intent = Intent("maden.login")

        intent.putExtra("USER_DATA", "User login with MADEN")
        context.sendBroadcast(intent, "maden.login")
    }
}