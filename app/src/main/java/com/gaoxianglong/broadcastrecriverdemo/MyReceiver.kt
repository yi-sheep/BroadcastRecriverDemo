package com.gaoxianglong.broadcastrecriverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"这是自己定义的广播",Toast.LENGTH_SHORT).show()
    }
}
