package com.maden.baseapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class LoginBroadcast: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val data = intent?.getStringExtra("USER_DATA")
        Toast.makeText(context, data ?: "NULL", Toast.LENGTH_LONG).show()
    }
}